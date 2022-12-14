package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface CustomerService {
    void couponPurchase(int customerId,int couponId) throws CouponSystemException;
    List<Coupon> getAllCustomerCoupons(int id);
    List<Coupon> getAllCustomerCoupons(int id, Category category);
    List<Coupon> getAllCustomerCoupons(int id,double maxPrice);
    Customer getCustomerDetails(int id) throws CouponSystemException;
}
