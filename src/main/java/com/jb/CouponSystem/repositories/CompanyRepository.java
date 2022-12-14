package com.jb.CouponSystem.repositories;

import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Coupon;
import org.springframework.data.domain.Example;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyRepository extends JpaRepository<Company,Integer> {
    boolean existsByEmailOrName(String email,String name);

    boolean existsByNameAndId(String name,int id);

    boolean existsByEmailAndPassword(String email, String password);

}
