package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface AdministratorService {
    void addCompany(Company company) throws CouponSystemException;
    void updateCompany(Company company,int id) throws CouponSystemException;
    void deleteCompany(int id) throws CouponSystemException;
    List<Company> getAllCompanies();
    Company getSingleCompany(int id) throws CouponSystemException;
    void addCustomer(Customer customer) throws CouponSystemException;
    void updateCustomer(Customer customer,int id) throws CouponSystemException;
    void deleteCustomer(int id) throws CouponSystemException;
    List<Customer> getAllCustomers();
    Customer getSingleCustomer(int id) throws CouponSystemException;
}
