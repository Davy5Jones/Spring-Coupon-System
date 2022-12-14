package com.jb.CouponSystem.services;

import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.exceptions.CouponSystemExceptionEnum;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Service;
import java.util.List;
@Service
@PropertySource("classpath:admin.properties")
@Data
public class AdministratorServiceImpl extends ClientService implements AdministratorService {
    @Value("${string.password}")
    private String password;
    @Value("${string.email}")
    private String email;



    @Override
    protected boolean login(String email, String password) {
        return (email.equals("admin@gmail") && password.equals("1234"));
    }

    @Override
    public void addCompany(Company company) throws CouponSystemException {
        if (companyRepository.existsByEmailOrName(company.getEmail(),company.getName())){
            throw new CouponSystemException(CouponSystemExceptionEnum.NAME_OR_EMAIL_USED.getMessage());
        }
        company.getCoupons().forEach(coupon -> coupon.setCompany(company));
        companyRepository.save(company);
    }

    @Override
    public void updateCompany(Company company, int id) throws CouponSystemException {

        if (company.getId()!=0&&(company.getId()!=id)){
            throw new CouponSystemException(CouponSystemExceptionEnum.CANNOT_UPDATE_ID.getMessage());
        }
        if (!companyRepository.existsById(id)){
            throw new CouponSystemException(CouponSystemExceptionEnum.COMPANY_DOESNT_EXIST.getMessage());
        }
        if (!companyRepository.existsByNameAndId(company.getName(),id)){
            throw new CouponSystemException(CouponSystemExceptionEnum.CANNOT_UPDATE_NAME.getMessage());
        }
        company.setId(id);
        companyRepository.saveAndFlush(company);


    }

    @Override
    public void deleteCompany(int id) throws CouponSystemException {
        if (!companyRepository.existsById(id)){
            throw new CouponSystemException(CouponSystemExceptionEnum.COMPANY_DOESNT_EXIST.getMessage());
        }
        companyRepository.deleteById(id);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company getSingleCompany(int id) throws CouponSystemException {
        return companyRepository.findById(id).orElseThrow(() -> new CouponSystemException(CouponSystemExceptionEnum.COMPANY_DOESNT_EXIST.getMessage()));
    }

    @Override
    public void addCustomer(Customer customer) throws CouponSystemException {
        if (customerRepository.existsByEmail(customer.getEmail())){
            throw new CouponSystemException(CouponSystemExceptionEnum.EMAIL_IN_USE.getMessage());
        }
        customerRepository.save(customer);
    }

    @Override
    public void updateCustomer(Customer customer, int id) throws CouponSystemException {
        if (customer.getId()!=0&&customer.getId()!=id){
            throw new CouponSystemException(CouponSystemExceptionEnum.CANNOT_UPDATE_ID.getMessage());
        }
        customer.setId(id);
        customerRepository.save(customer);

    }

    @Override
    public void deleteCustomer(int id) throws CouponSystemException {
        if (!customerRepository.existsById(id)){
            throw new CouponSystemException(CouponSystemExceptionEnum.CUSTOMER_DOESNT_EXIST.getMessage());
        }
        customerRepository.deleteById(id);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepository.findAll();
    }

    @Override
    public Customer getSingleCustomer(int id) throws CouponSystemException {
        return customerRepository.findById(id).orElseThrow(() -> new CouponSystemException(CouponSystemExceptionEnum.CUSTOMER_DOESNT_EXIST.getMessage()));
    }

}
