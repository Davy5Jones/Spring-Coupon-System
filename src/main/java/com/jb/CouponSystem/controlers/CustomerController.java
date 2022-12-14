package com.jb.CouponSystem.controlers;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("api/customers")
public class CustomerController {
    @Autowired
    private CustomerService customerService;

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("{customerId}")
    public void couponPurchase(@PathVariable int customerId,@RequestParam int couponId) throws CouponSystemException{
        customerService.couponPurchase(customerId,couponId);
    }
    @GetMapping("{customerId}/coupons")
    public List<Coupon> getAllCustomerCoupons(@PathVariable int customerId){
        return customerService.getAllCustomerCoupons(customerId);
    }

    @GetMapping("{customerId}/coupons/category")
    public List<Coupon> getAllCustomerCoupons(@PathVariable int customerId,@RequestParam String category) {
        return customerService.getAllCustomerCoupons(customerId, Category.valueOf(category));
    }

    @GetMapping("{customerId}/coupons/price/less")
    public List<Coupon> getAllCustomerCoupons(@PathVariable int customerId, @RequestParam double less){
        return customerService.getAllCustomerCoupons(customerId,less);
    }

    @GetMapping("{customerId}")
    public Customer getCustomerDetails(@PathVariable int customerId) throws CouponSystemException {
        return customerService.getCustomerDetails(customerId);
    }


}
