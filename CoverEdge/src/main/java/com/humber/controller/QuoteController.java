package com.humber.controller;

import java.io.IOException;
import java.util.Map;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/quoteController")
public class QuoteController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private static final Map<String, Double> BASE_RATES = Map.of(
        "HEALTH", 50.0,
        "AUTO", 40.0,
        "HOME", 60.0
    );

    private static final Map<Integer, Double> TERM_FACTORS = Map.of(
        1, 1.0,
        5, 0.95,
        10, 0.90
    );

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String policyType = request.getParameter("policyType");
        double coverage = Double.parseDouble(request.getParameter("coverage"));
        int term = Integer.parseInt(request.getParameter("term"));

        double baseRate = BASE_RATES.getOrDefault(policyType, 50.0);
        double termFactor = TERM_FACTORS.getOrDefault(term, 1.0);
        double premium = baseRate * coverage * termFactor;

        request.setAttribute("premium", premium);
        request.getRequestDispatcher("/quoteResult.jsp").forward(request, response);
    }
}