package com.humber.controller;

import com.humber.model.Claim;
import com.humber.model.Policy;
import com.humber.service.ClaimService;
import com.humber.service.PolicyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

@WebServlet(name = "ClaimController", urlPatterns = {"/claims"})
public class ClaimController extends HttpServlet {
    private ClaimService claimService;
    private final PolicyService policyService = new PolicyService();

    @Override
    public void init() {
        this.claimService = new ClaimService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        if("new".equals(action)) {
            showNewForm(request, response);
        } else {
            listClaims(request, response);
        }
    }

    private void listClaims(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Claim> claims = claimService.getAllClaims();
        request.setAttribute("claims", claims);
        request.setAttribute("claimStatusValues", Arrays.asList(Claim.ClaimStatus.values()));
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageClaims.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<Policy> policies = policyService.getAllPolicies();
        request.setAttribute("policies", policies);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addClaim.jsp");
        dispatcher.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("submit".equals(action)) {
                submitClaim(request, response);
            } else if ("updateStatus".equals(action)) {
                updateClaimStatus(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void submitClaim(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Claim claim = new Claim();
        claim.setDescription(request.getParameter("description"));
        claim.setStatus(Claim.ClaimStatus.PENDING);
        claim.setDateSubmitted(new Date());
        
        int policyId = Integer.parseInt(request.getParameter("policyId"));
        Policy policy = policyService.getPolicy(policyId);
        claim.setPolicy(policy);
        
        claimService.addClaim(claim);
        response.sendRedirect("claims");
    }

    private void updateClaimStatus(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int claimId = Integer.parseInt(request.getParameter("claimId"));
        String newStatus = request.getParameter("status");
        claimService.updateClaimStatus(claimId, newStatus);
        response.sendRedirect("claims");
    }
}