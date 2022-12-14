package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.exceptions.CouponSystemExceptionEnum;
import org.springframework.stereotype.Service;

import java.util.List;
@Service

public class CompanyServiceImpl extends ClientService implements CompanyService{

    @Override
    protected boolean login(String email, String password) {
        return companyRepository.existsByEmailAndPassword(email,password);
    }

    @Override
    public void addCoupon(Coupon coupon,int companyId) throws CouponSystemException {
        if (couponRepository.existsByCompanyAndTitle(companyId,coupon.getTitle())){
            throw new CouponSystemException(CouponSystemExceptionEnum.TITLE_IN_USE.getMessage());
        }
        if (coupon.getCompany()!=null&&coupon.getCompany().getId()!=companyId){
            throw new CouponSystemException(CouponSystemExceptionEnum.CANNOT_UPDATE_COMPANY_ID.getMessage());
        }
        coupon.setCompany(getCompanyDetails(companyId));
        couponRepository.save(coupon);

    }

    @Override
    public void updateCoupon(int couponId, Coupon coupon, int companyId) throws CouponSystemException {
        coupon.setCompany(companyRepository.findById(companyId).orElseThrow(() -> new CouponSystemException(CouponSystemExceptionEnum.COMPANY_DOESNT_EXIST.getMessage())));
        if (couponId!=coupon.getId()&&coupon.getId()!=0){ // TODO: 11/22/2022 ask kobi about id
            throw new CouponSystemException(CouponSystemExceptionEnum.CANNOT_UPDATE_ID.getMessage());
        }
        if (!couponRepository.existsByIdAndCompanyId(couponId,companyId)){
            throw new CouponSystemException(CouponSystemExceptionEnum.COMPANY_COUPON_DOESNT_EXIST.getMessage());
        }
        if (couponRepository.existsByCompanyIdAndTitleAndIdNot(companyId,coupon.getTitle(),couponId)){
            throw new CouponSystemException(CouponSystemExceptionEnum.TITLE_IN_USE.getMessage());
        }
        coupon.setId(couponId);
        couponRepository.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId, int companyId) throws CouponSystemException {
        if (!couponRepository.existsByIdAndCompanyId(couponId,companyId)){
            throw new CouponSystemException(CouponSystemExceptionEnum.COMPANY_COUPON_DOESNT_EXIST.getMessage());
        }
        couponRepository.deleteById(couponId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId) {
        return couponRepository.findCouponsAllByCompany(companyId);
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,Category category) {
        return couponRepository.findAllByCategoryAndCompany(companyId,category.name());
    }

    @Override
    public List<Coupon> getCompanyCoupons(int companyId,double maxPrice) {
        return couponRepository.findAllByPriceLessThanAndCompany(maxPrice,companyId);
    }

    @Override
    public Company getCompanyDetails(int id) throws CouponSystemException {
        return companyRepository.findById(id).orElseThrow(() -> new CouponSystemException(CouponSystemExceptionEnum.COMPANY_DOESNT_EXIST.getMessage()));
    }


}
