package com.jb.CouponSystem.repositories;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.sql.Date;
import java.util.List;

@Repository
public interface CouponRepository extends JpaRepository<Coupon, Integer> {

    @Query(value = "SELECT case when exists(select * from coupons where company_id=? and title =?) then \"true\" else \"false\" end",nativeQuery = true)

    boolean existsByCompanyAndTitle(int companyId, String title);

    @Query(value = "SELECT case when exists(select * from coupons where company_id=? and title =? and id !=? ) then \"true\" else \"false\" end",nativeQuery = true)
    boolean existsByCompanyIdAndTitleAndIdNot(int CompanyId, String title, int id);


    @Query(value = "SELECT * FROM couponsystem.coupons where company_id = ? and category=?",nativeQuery = true)
    List<Coupon> findAllByCategoryAndCompany(int companyId,String category);

    @Query(value = "SELECT * FROM couponsystem.coupons where price<? and company_id = ?",nativeQuery = true)
    List<Coupon> findAllByPriceLessThanAndCompany(double price,int companyId);



    @Query( value = "SELECT count(*) FROM coupons_customers WHERE customer_id = :cusId and coupon_id = :couId",nativeQuery = true)
    int countCouponsByIdAndCustomerId(@Param("couId") int coupon_id, @Param("cusId") int customer_id);


    @Query(value = "SELECT couponsystem.coupons.* from  couponsystem.coupons_customers right join coupons on coupon_id=id where customer_id=?", nativeQuery = true)
    List<Coupon> findAllCouponsByCustomerId( int id);

    @Query(value = "SELECT couponsystem.coupons.* from  couponsystem.coupons_customers right join coupons on coupon_id=id where customer_id=? and category=?", nativeQuery = true)
List<Coupon> findAllCouponsByCustomerIdAndCategory(int id, String    category);

    @Query(value = "SELECT couponsystem.coupons.* from  couponsystem.coupons_customers right join coupons on coupon_id=id where customer_id=? and price<?", nativeQuery = true)
    List<Coupon> findAllCouponsByCustomerIdAndMaxPrice(int id, double maxPrice);
    void deleteAllByEndDateAfter(Date date);
    @Query(value = "SELECT * FROM couponsystem.coupons where company_id =?" ,nativeQuery = true)
    List<Coupon> findCouponsAllByCompany(int companyId);

    @Query(value = "SELECT case when exists (select * FROM couponsystem.coupons where id= ? and company_id=?) then \"true\" else \"false\" end",nativeQuery = true)
    boolean existsByIdAndCompanyId(int couponId,int companyId);


}
