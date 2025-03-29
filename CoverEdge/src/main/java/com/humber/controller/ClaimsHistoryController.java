package com.humber.controller;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import com.humber.model.Claim;
import com.humber.repository.ClaimRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ClaimsHistoryController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private ClaimRepository claimRepo = new ClaimRepository();

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int policyId = Integer.parseInt(request.getParameter("policyId"));
        String statusFilter = request.getParameter("status");

        List<Claim> claims = claimRepo.getAllClaims().stream()
            .filter(c -> c.getPolicy().getPolicyId() == policyId)
            .filter(c -> statusFilter == null || c.getStatus().equals(statusFilter))
            .collect(Collectors.toList());

        request.setAttribute("claims", claims);
        request.getRequestDispatcher("/claimsHistory.jsp").forward(request, response);
    }
}