package com.humber.service;

import com.humber.model.Customer;
import com.humber.repository.CustomerRepository;
import java.util.List;

public class CustomerService {
    private final CustomerRepository customerRepository;
    
    public CustomerService() {
        this.customerRepository = new CustomerRepository();
    }

    public synchronized void addCustomer(Customer customer) {
        customerRepository.saveCustomer(customer);
    }

    public synchronized List<Customer> getAllCustomers() {
        return customerRepository.getAllCustomers();
    }

    public synchronized Customer getCustomer(int id) {
        return customerRepository.getCustomerById(id);
    }

    public synchronized void updateCustomer(Customer customer) {
        customerRepository.updateCustomer(customer);
    }

    public synchronized void deleteCustomer(int id) {
        customerRepository.deleteCustomer(id);
    }
}