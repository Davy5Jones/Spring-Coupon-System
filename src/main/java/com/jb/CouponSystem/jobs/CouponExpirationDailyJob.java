package com.jb.CouponSystem.jobs;

import com.jb.CouponSystem.repositories.CouponRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;

@Component
public class CouponExpirationDailyJob {
    @Autowired
    private CouponRepository couponRepository;
    @Scheduled(fixedRate = 1000*60*60*24)
    public void deleteExpiredCoupons(){
        couponRepository.deleteAllByEndDateAfter(Date.valueOf(LocalDate.now()));

    }
}
