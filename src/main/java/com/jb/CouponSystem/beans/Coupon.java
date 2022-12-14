package com.jb.CouponSystem.beans;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import javax.validation.constraints.Min;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "coupons")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class Coupon {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String title;
    String description;
    Date startDate;
    Date endDate;
    int amount;
    @Min(value = 0,message = "Price cannot be negative...")
    double price;
    String image;
    @Enumerated(EnumType.STRING)
    Category category;
    @ManyToOne
            @JsonIgnore
            @ToString.Exclude
    Company company;

    @JsonIgnore
    @ManyToMany
    @ToString.Exclude
    @JoinTable(
            name = "coupons_customers",
            joinColumns = @JoinColumn(name ="coupon_id" ),
            inverseJoinColumns = @JoinColumn(name = "customer_id")
    )
    private List<Customer> customers = new ArrayList<>();

}
