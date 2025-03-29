package com.humber.controller;

import com.humber.model.Broker;
import com.humber.model.Customer;
import com.humber.model.Policy;
import com.humber.service.BrokerService;
import com.humber.service.CustomerService;
import com.humber.service.PolicyService;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import jakarta.servlet.annotation.*;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import org.hibernate.Hibernate;

@WebServlet(name = "PolicyController", urlPatterns = {"/policies"})
public class PolicyController extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	private PolicyService policyService;
	private final BrokerService brokerService = new BrokerService();
    private final CustomerService customerService = new CustomerService();
    private final SimpleDateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd");

    @Override
    public void init() {
        this.policyService = new PolicyService();
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
                    deletePolicy(request, response);
                    break;
                default:
                    listPolicies(request, response);
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
                savePolicy(request, response);
            } else if ("update".equals(action)) {
                updatePolicy(request, response);
            }
        } catch (Exception ex) {
            throw new ServletException(ex);
        }
    }

    private void listPolicies(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        request.setAttribute("policies", policyService.getAllPolicies());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/managePolicies.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    	request.setAttribute("customers", customerService.getAllCustomers());
        request.setAttribute("brokers", brokerService.getAllBrokers());
        RequestDispatcher dispatcher = request.getRequestDispatcher("/addPolicy.jsp");
        dispatcher.forward(request, response);
    }

    private void showEditForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Policy policy = policyService.getPolicy(id);
        if(policy.getBroker() != null) {
            Hibernate.initialize(policy.getBroker().getId()); // Force proxy initialization
        }
        List<Broker> brokers = new BrokerService().getAllBrokers();
        request.setAttribute("policy", policy);
        request.setAttribute("brokers", brokers);
        request.setAttribute("customers", customerService.getAllCustomers());
        request.getRequestDispatcher("/updatePolicy.jsp").forward(request, response);
    }

    private void savePolicy(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        Policy newPolicy = new Policy();
        populatePolicyFromRequest(newPolicy, request);
        policyService.addPolicy(newPolicy);
        response.sendRedirect("policies");
    }

    private void updatePolicy(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("policyId"));
        Policy policy = policyService.getPolicy(id);
        populatePolicyFromRequest(policy, request);
        policyService.updatePolicy(policy);
        response.sendRedirect("policies");
    }

    private void deletePolicy(HttpServletRequest request, HttpServletResponse response)
            throws Exception {
        int id = Integer.parseInt(request.getParameter("id"));
        policyService.deletePolicy(id);
        response.sendRedirect("policies");
    }

    private void populatePolicyFromRequest(Policy policy, HttpServletRequest request)
            throws Exception {
        policy.setPolicyNumber(request.getParameter("policyNumber"));
        policy.setPolicyType(Policy.PolicyType.valueOf(request.getParameter("policyType")));
        policy.setCoverageAmount(Double.parseDouble(request.getParameter("coverageAmount")));
        policy.setPremiumAmount(Double.parseDouble(request.getParameter("premiumAmount")));
        policy.setStartDate(dateFormatter.parse(request.getParameter("startDate")));
        policy.setEndDate(dateFormatter.parse(request.getParameter("endDate")));
        policy.setPolicyStatus(Policy.PolicyStatus.valueOf(request.getParameter("policyStatus")));
        int brokerId = Integer.parseInt(request.getParameter("brokerId"));
        Broker broker = brokerService.getAllBrokers().stream()
                .filter(b -> b.getId() == brokerId)
                .findFirst()
                .orElseThrow(() -> new ServletException("Broker not found"));
        policy.setBroker(broker);
        int customerId = Integer.parseInt(request.getParameter("customerId"));
        Customer customer = customerService.getCustomer(customerId);
        policy.setCustomer(customer);
    }
}