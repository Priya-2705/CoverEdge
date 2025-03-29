package com.humber.controller;

import java.io.IOException;

import com.humber.model.Policy;
import com.humber.service.PolicyQuoteService;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/quote")
public class QuoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PolicyQuoteService quoteService = new PolicyQuoteService();

	 protected void doGet(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException {
	     request.getRequestDispatcher("/quoteForm.jsp").forward(request, response);
	 }

	 protected void doPost(HttpServletRequest request, HttpServletResponse response) 
	         throws ServletException, IOException {
	     try {
	         Policy.PolicyType type = Policy.PolicyType.valueOf(
	             request.getParameter("policyType"));
	         int age = Integer.parseInt(request.getParameter("age"));
	         double coverage = Double.parseDouble(request.getParameter("coverage"));
	         int term = Integer.parseInt(request.getParameter("term"));

	         double premium = quoteService.calculateQuote(type, age, coverage, term);
	         
	         request.setAttribute("premium", premium);
	         request.getRequestDispatcher("/quoteResult.jsp").forward(request, response);
	         
	     } catch (Exception e) {
	         request.setAttribute("error", "Error calculating quote: " + e.getMessage());
	         request.getRequestDispatcher("/quoteForm.jsp").forward(request, response);
	     }
	 }
}