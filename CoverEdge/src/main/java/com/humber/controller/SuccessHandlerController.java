package com.humber.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/successHandler")
public class SuccessHandlerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleSuccess(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleSuccess(request, response);
    }

 // In SuccessHandlerController
    private void handleSuccess(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
        String successMessage = (String) request.getAttribute("successMessage");
        String redirectUrl = (String) request.getAttribute("redirectUrl");

        if (successMessage == null) {
            successMessage = "Operation completed successfully.";
        }

        request.setAttribute("successMessage", successMessage);
        
        if (redirectUrl != null) {
            request.setAttribute("redirectUrl", redirectUrl);
        }
        
        request.getRequestDispatcher("/success.jsp").forward(request, response);
    }
}