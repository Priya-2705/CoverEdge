package com.humber.controller;

import com.humber.model.Claim;
import com.humber.service.ClaimService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@WebServlet("/policyClaims")
public class PolicyClaimsController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private final ClaimService claimService = new ClaimService();

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            int policyId = Integer.parseInt(request.getParameter("policyId"));
            String policyNumber = request.getParameter("policyNumber");
            
            // Get filter parameters
            Date startDate = parseDate(request.getParameter("startDate"));
            Date endDate = parseDate(request.getParameter("endDate"));
            String status = request.getParameter("status");

            // Get filtered claims
            List<Claim> claims = claimService.getClaimsByPolicy(
                policyId, 
                startDate, 
                endDate, 
                status
            );

            // Set attributes for JSP
            request.setAttribute("claims", claims);
            request.setAttribute("policyNumber", policyNumber);
            request.setAttribute("startDate", request.getParameter("startDate"));
            request.setAttribute("endDate", request.getParameter("endDate"));
            request.setAttribute("selectedStatus", status);
            
            request.getRequestDispatcher("/policyClaims.jsp").forward(request, response);
            
        } catch (Exception e) {
            throw new ServletException("Error loading claims history", e);
        }
    }

    private Date parseDate(String dateString) throws ParseException {
        if(dateString == null || dateString.isEmpty()) return null;
        return new SimpleDateFormat("yyyy-MM-dd").parse(dateString);
    }
}