package com.humber.controller;

import com.humber.model.Customer;
import com.humber.service.CustomerService;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Date;
import java.text.SimpleDateFormat;

@WebServlet(name = "CustomerController", urlPatterns = {"/customers"})
public class CustomerController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private CustomerService customerService;
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() {
        this.customerService = new CustomerService();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            switch (action == null ? "list" : action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "edit":
                    showEditForm(request, response);
                    break;
                case "delete":
                    deleteCustomer(request, response);
                    break;
                default:
                    listCustomers(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) 
            throws ServletException, IOException {
        String action = request.getParameter("action");
        
        try {
            if ("save".equals(action)) {
                saveCustomer(request, response);
            } else if ("update".equals(action)) {
                updateCustomer(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listCustomers(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("customers", customerService.getAllCustomers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/manageCustomers.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Customer customer = customerService.getCustomer(id);
        request.setAttribute("customer", customer);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/updateCustomer.jsp");
        dispatcher.forward(request, response);
    }

    private void saveCustomer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Customer newCustomer = new Customer();
        populateCustomerFromRequest(newCustomer, request);
        customerService.addCustomer(newCustomer);
        response.sendRedirect("customers");
    }

    private void updateCustomer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = customerService.getCustomer(id);
        populateCustomerFromRequest(customer, request);
        customerService.updateCustomer(customer);
        response.sendRedirect("customers");
    }

    private void deleteCustomer(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        customerService.deleteCustomer(id);
        response.sendRedirect("customers");
    }

    private void populateCustomerFromRequest(Customer customer, HttpServletRequest request)
            throws Exception {
        customer.setFirstName(request.getParameter("firstName"));
        customer.setLastName(request.getParameter("lastName"));
        customer.setEmail(request.getParameter("email"));
        customer.setPhoneNumber(request.getParameter("phoneNumber"));
        customer.setAddress(request.getParameter("address"));
        customer.setDateOfBirth(dateFormatter.parse(request.getParameter("dateOfBirth")));
        customer.setIdentificationNumber(request.getParameter("identificationNumber"));
        customer.setRegistrationDate(new Date());
    }
}