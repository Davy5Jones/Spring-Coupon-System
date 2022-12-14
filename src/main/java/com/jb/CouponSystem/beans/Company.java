package com.jb.CouponSystem.beans;

import lombok.*;

import javax.persistence.*;
import javax.validation.Valid;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "companies")
@Data
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class Company {
    @Id@GeneratedValue(strategy = GenerationType.IDENTITY)
    int id;
    String name;
    @Email(regexp="[A-Za-z0-9._%-+]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}",
            flags = Pattern.Flag.CASE_INSENSITIVE, message = "Invalid email address")
    String email;
    @NotBlank(message = "Password is required")
    String password;

    @OneToMany(mappedBy = "company", cascade = {CascadeType.REMOVE,CascadeType.PERSIST})
    @Singular
    @Valid
    List<Coupon> coupons = new ArrayList<>();


}
