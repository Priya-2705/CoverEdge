package com.humber.controller;

import java.io.IOException;
import java.util.List;

import com.humber.model.Policy;
import com.humber.repository.PolicyRepository;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/cancellationController")
public class CancellationController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PolicyRepository policyRepo = new PolicyRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int policyId = Integer.parseInt(request.getParameter("policyId"));
        String reason = request.getParameter("reason");

        List<Policy> policies = policyRepo.getAllPolicies();
        policies.stream()
            .filter(p -> p.getPolicyId() == policyId)
            .forEach(p -> {
                p.setStatus("CANCELED");
                p.setCancellationReason(reason);
            });

        policyRepo.savePolicies(policies);
        response.sendRedirect("policies");
    }
}