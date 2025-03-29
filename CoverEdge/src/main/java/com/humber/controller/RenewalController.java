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

@WebServlet("/renewalController")
public class RenewalController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PolicyRepository policyRepo = new PolicyRepository();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        int policyId = Integer.parseInt(request.getParameter("policyId"));
        int newTerm = Integer.parseInt(request.getParameter("term"));

        List<Policy> policies = policyRepo.getAllPolicies();
        Policy policy = policies.stream()
            .filter(p -> p.getPolicyId() == policyId)
            .findFirst()
            .orElseThrow();

        policy.setTerm(newTerm);
        policy.setPremium(policy.getBaseRate() * policy.getCoverageAmount() * policy.getTermFactor());

        policyRepo.savePolicies(policies);
        response.sendRedirect("policies");
    }
}