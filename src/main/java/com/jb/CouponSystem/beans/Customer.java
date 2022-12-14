package com.jb.CouponSystem.beans;

import lombok.*;
import org.springframework.context.annotation.PropertySource;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "customers")
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Data
public class Customer {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    private String first_Name;
    private String last_Name;
    @Email(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid email address")
    private String email;
    @NotBlank(message = "Password is required")
    private String password;

    @ManyToMany
    @Singular("coupon")
    @JoinTable(
            name = "coupons_customers",
            joinColumns = @JoinColumn(name = "customer_id", referencedColumnName = "id"),
            inverseJoinColumns = @JoinColumn(name = "coupon_id",referencedColumnName = "id")
    )
    private List<Coupon> coupons = new ArrayList<>();
}
