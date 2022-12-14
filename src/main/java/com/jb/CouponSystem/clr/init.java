package com.jb.CouponSystem.clr;

import com.jb.CouponSystem.beans.Category;
import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Coupon;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.controlers.AdministratorController;
import com.jb.CouponSystem.services.AdministratorService;
import com.jb.CouponSystem.services.AdministratorServiceImpl;
import com.jb.CouponSystem.services.CompanyService;
import com.jb.CouponSystem.services.CustomerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.sql.Date;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

@Component
public class init implements CommandLineRunner {
    @Autowired
    CustomerService customerService;
    @Autowired
    CompanyService companyService;
    @Autowired
    AdministratorController administratorService;




    @Override
    public void run(String... args) throws Exception {
        Coupon coupon1 = Coupon.builder()
                .amount(10)
                .image("matriximageurl")
                .price(105)
                .title("free vacation")
                .category(Category.VACATION)
                .endDate(Date.valueOf(LocalDate.now().plusDays(200)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("Just for matrix empleyees")
                .build();
        Coupon coupon2 = Coupon.builder()
                .amount(20)
                .image("oracleimageurl")
                .price(10)
                .title("free Intelij")
                .category(Category.ELECTRICITY)
                .endDate(Date.valueOf(LocalDate.now().plusDays(350)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("Just for Oracle empleyees")
                .build();

        Company company = Company.builder()
                .email("Matrix@gmail.co")
                .name("Matrix")
                .password("1234")
                .coupons(List.of(coupon1,coupon2))
                .build();

        administratorService.addCompany(company);

        Coupon coupon3 = Coupon.builder()
                .amount(40)
                .image("john bryce course img")
                .price(105)
                .title("course discount")
                .category(Category.ELECTRICITY)
                .endDate(Date.valueOf(LocalDate.now().plusDays(100)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("john bryce course discount")
                .build();
        Coupon coupon4 = Coupon.builder()
                .amount(10)
                .image("rami")
                .price(30)
                .title("free Lecture")
                .category(Category.FOOD)
                .endDate(Date.valueOf(LocalDate.now().plusDays(120)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("free rami lecture at johnbryce")
                .build();

        Company company2 = Company.builder()
                .email("Bryce@gmail.com")
                .name("JohnBryce")
                .password("1234")
                .coupons(List.of(coupon3,coupon4))
                .build();

        administratorService.addCompany(company2);



        Coupon coupon5 = Coupon.builder()
                .amount(40)
                .image("breadpicture")
                .price(5)
                .title("bread discount")
                .category(Category.FOOD)
                .endDate(Date.valueOf(LocalDate.now().plusDays(20)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("supermarket bread discount")
                .build();
        Coupon coupon6 = Coupon.builder()
                .amount(2)
                .image("Tuna")
                .price(30)
                .title("fish discount")
                .category(Category.FOOD)
                .endDate(Date.valueOf(LocalDate.now().plusDays(15)))
                .startDate(Date.valueOf(LocalDate.now()))
                .description("fish discount for our customers")
                .build();

        Company company3 = Company.builder()
                .email("Shufersal@gmail.mor")
                .name("Shufersal")
                .password("1234")
                .coupons(List.of(coupon5,coupon6))
                .build();

        administratorService.addCompany(company3);

        Customer customer1 = Customer.builder()
                .password("ido123")
                .email("idofiene@gmail.com")
                .first_Name("ido")
                .last_Name("shal")
                .coupons(Arrays.asList(coupon2,coupon5))
                .build();

        administratorService.addCustomer(customer1);
        coupon3.setId(3);
        coupon1.setId(1);

        customerService.couponPurchase(1,1);

        Customer toUpdate = customerService.getCustomerDetails(1);
        toUpdate.setEmail("ido@gmail123.com");
        administratorService.updateCustomer(toUpdate,toUpdate.getId());

        Customer customer2 = Customer.builder()
                .password("Niv1234")
                .email("Niv@gmail.il")
                .first_Name("Niv")
                .last_Name("Banai")
                .coupons(Arrays.asList(coupon1,coupon3))
                .build();
        administratorService.addCustomer(customer2);


    }
}
