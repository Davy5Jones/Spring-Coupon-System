package com.jb.CouponSystem.repositories;

import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.beans.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository

public interface CustomerRepository extends JpaRepository<Customer,Integer> {
    boolean existsByEmail(String email);
    boolean existsByEmailAndPassword(String email,String password);

}
