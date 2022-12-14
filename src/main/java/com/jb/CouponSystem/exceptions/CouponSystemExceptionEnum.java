package com.jb.CouponSystem.exceptions;

public enum CouponSystemExceptionEnum {
    NAME_OR_EMAIL_USED("Company name or email are already in use!"),
    CANNOT_UPDATE_ID("Id is not updatable!"),
    CANNOT_UPDATE_NAME("Cannot update name!"),
    COMPANY_DOESNT_EXIST("Company of this id doesn't exist!"),
    EMAIL_IN_USE("Email already in use!"),
    CUSTOMER_DOESNT_EXIST("Customer Doesn't exist!"),
    TITLE_IN_USE("Cannot use same title twice!"),
    CANNOT_UPDATE_COMPANY_ID("Company id is not updatable!"),
    COUPON_DOESNT_EXIST("Coupon doesn't exist!"),
    COUPON_OUT_OF_STOCK("Coupon is out of stock"),
    ALREADY_OWNS_COUPON("Customer already owns coupon!"),
    COMPANY_COUPON_DOESNT_EXIST("Company coupon doesn't exist!");
    private final String message;


    CouponSystemExceptionEnum(String message) {
        this.message = message;
    }

    public String getMessage() {
        return message;
    }
}
