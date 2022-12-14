package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.exceptions.CouponSystemException;

import java.util.List;

public interface CompanyService {
    void addCoupon(Coupon coupon,int companyId) throws CouponSystemException;
    void updateCoupon(int couponId, Coupon coupon, int companyId) throws CouponSystemException;

    void deleteCoupon(int couponId,int companyId) throws CouponSystemException;
    List<Coupon> getCompanyCoupons(int companyId);
    List<Coupon> getCompanyCoupons(int companyId,Category category);
    List<Coupon> getCompanyCoupons(int companyId,double maxPrice);
    Company getCompanyDetails(int id) throws CouponSystemException;

}
