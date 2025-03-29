package com.humber.controller;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;

import com.humber.exception.AppException;

@WebServlet("/errorHandler")
public class ErrorHandlerController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        handleError(request, response);
    }

    private void handleError(HttpServletRequest request, HttpServletResponse response) 
            throws IOException, ServletException {
    	
        Throwable throwable = (Throwable) request.getAttribute("jakarta.servlet.error.exception");
        Integer statusCode = (Integer) request.getAttribute("jakarta.servlet.error.status_code");
        String servletName = (String) request.getAttribute("jakarta.servlet.error.servlet_name");
        String requestUri = (String) request.getAttribute("jakarta.servlet.error.request_uri");

        String errorMessage;
        if (throwable instanceof AppException) {
            AppException appEx = (AppException) throwable;
            statusCode = appEx.getStatusCode();
            errorMessage = appEx.getMessage();
        } else {
            errorMessage = "An unexpected error occurred.";
            statusCode = statusCode != null ? statusCode : HttpServletResponse.SC_INTERNAL_SERVER_ERROR;
        }

        logErrorDetails(throwable, statusCode, servletName, requestUri);

        request.setAttribute("errorCode", statusCode);
        request.setAttribute("errorMessage", errorMessage);
        request.setAttribute("requestUri", requestUri);
        response.setStatus(statusCode);
        request.getRequestDispatcher("/error.jsp").forward(request, response);
    }

    private void logErrorDetails(Throwable throwable, int statusCode, String servletName, String requestUri) {
        System.err.println("Error in " + servletName + " while processing " + requestUri);
        System.err.println("Status code: " + statusCode);
        if (throwable != null) {
            throwable.printStackTrace();
        }
    }
}