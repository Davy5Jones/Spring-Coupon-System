package com.jb.CouponSystem.services;

import com.jb.CouponSystem.repositories.CompanyRepository;
import com.jb.CouponSystem.repositories.CouponRepository;
import com.jb.CouponSystem.repositories.CustomerRepository;
import org.springframework.beans.factory.annotation.Autowired;

public abstract class ClientService {
    protected abstract boolean login(String email,String password);
    @Autowired
    public CompanyRepository companyRepository;
    @Autowired
    public CouponRepository couponRepository;
    @Autowired
    public CustomerRepository customerRepository;
}
