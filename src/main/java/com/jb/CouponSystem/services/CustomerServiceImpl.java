package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.exceptions.CouponSystemExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CustomerServiceImpl extends ClientService implements CustomerService {
    @Override
    protected boolean login(String email, String password) {
        return customerRepository.existsByEmailAndPassword(email, password);
    }

    @Override
    public void couponPurchase(int customerId,int couponId) throws CouponSystemException {
        Coupon coupon= couponRepository.findById(couponId).orElseThrow(()->new CouponSystemException(CouponSystemExceptionEnum.COUPON_DOESNT_EXIST.getMessage()));

        if (coupon.getAmount() < 1) {
            throw new CouponSystemException(CouponSystemExceptionEnum.COUPON_OUT_OF_STOCK.getMessage());
        }
        if (couponRepository.countCouponsByIdAndCustomerId(couponId, customerId)>0) {
            throw new CouponSystemException(CouponSystemExceptionEnum.ALREADY_OWNS_COUPON.getMessage());
        }
        Customer customer=customerRepository.findById(customerId).orElseThrow(()->new CouponSystemException(CouponSystemExceptionEnum.CUSTOMER_DOESNT_EXIST.getMessage()));
        customer.getCoupons().add(coupon);
        customerRepository.saveAndFlush(customer);
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int id) {
        return couponRepository.findAllCouponsByCustomerId(id);
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int id, Category category) {
        return couponRepository.findAllCouponsByCustomerIdAndCategory(id,category.name());
    }

    @Override
    public List<Coupon> getAllCustomerCoupons(int id, double maxPrice) {
        return couponRepository.findAllCouponsByCustomerIdAndMaxPrice(id,maxPrice);
    }

    @Override
    public Customer getCustomerDetails(int id) throws CouponSystemException {
        return customerRepository.findById(id).orElseThrow(()-> new CouponSystemException(CouponSystemExceptionEnum.CUSTOMER_DOESNT_EXIST.getMessage()));
    }


}
