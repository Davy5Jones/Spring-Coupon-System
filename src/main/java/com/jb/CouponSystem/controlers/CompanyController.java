package com.jb.CouponSystem.controlers;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.services.CompanyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/companies")
public class CompanyController {
    @Autowired
    private CompanyService companyService;

    @PostMapping("{companyId}/coupons")// TODO: 11/19/2022 check for path variable
    @ResponseStatus(HttpStatus.CREATED)
    public void addCoupon(@RequestBody @Valid Coupon coupon,@PathVariable int companyId) throws CouponSystemException{
        companyService.addCoupon(coupon,companyId);
    }

    @PutMapping("{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCoupon(@PathVariable int companyId, @Valid @RequestBody Coupon coupon, @PathVariable int couponId) throws CouponSystemException{
        companyService.updateCoupon(couponId,coupon, companyId);
    }
    @DeleteMapping("{companyId}/coupons/{couponId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteCoupon(@PathVariable int couponId, @PathVariable int companyId) throws CouponSystemException{
        companyService.deleteCoupon(couponId,companyId);
    }
    @GetMapping("{companyId}/coupons")
    public List<Coupon> getCompanyCoupons(@PathVariable int companyId){
        return companyService.getCompanyCoupons(companyId);
    }
    @GetMapping("{companyId}/coupons/category")
    List<Coupon> getCompanyCoupons(@RequestParam String category,@PathVariable int companyId){
        return companyService.getCompanyCoupons(companyId,Category.valueOf(category.toUpperCase()));
    }

    @GetMapping("{companyId}/coupons/price/less")
    List<Coupon> getCompanyCoupons(@RequestParam double less, @PathVariable int companyId){
        return companyService.getCompanyCoupons(companyId, less);
    }
@GetMapping("{companyId}")
    Company getCompanyDetails(@PathVariable int companyId) throws CouponSystemException{
        return companyService.getCompanyDetails(companyId);
    }


}
