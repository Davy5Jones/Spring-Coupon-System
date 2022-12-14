package com.jb.CouponSystem.controlers;

import com.jb.CouponSystem.beans.Company;
import com.jb.CouponSystem.beans.Customer;
import com.jb.CouponSystem.exceptions.CouponSystemException;
import com.jb.CouponSystem.services.AdministratorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("api/admin")
public class AdministratorController {
    @Autowired
    private AdministratorService administratorService;

    @PostMapping("companies")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCompany(@Valid @RequestBody  Company company) throws CouponSystemException {
        administratorService.addCompany(company);
    }

    @PutMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void updateCompany(@Valid @RequestBody Company company, @PathVariable int companyId) throws CouponSystemException {
        administratorService.updateCompany(company,companyId);
    }

    @DeleteMapping("companies/{companyId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeCompany(@PathVariable int companyId) throws CouponSystemException{
        administratorService.deleteCompany(companyId);
    }

    @GetMapping("companies")
    public List<Company> getAllCompanies(){
        return administratorService.getAllCompanies();
    }


    @GetMapping("companies/{companyId}")
    public Company getSingleCompany(@PathVariable int companyId) throws CouponSystemException{
        return administratorService.getSingleCompany(companyId);
    }
    @PostMapping("customers")
    @ResponseStatus(HttpStatus.CREATED)
    public void addCustomer(@Valid @RequestBody Customer customer) throws CouponSystemException{
        administratorService.addCustomer(customer);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @PutMapping("customers/{customerId}")
    public void updateCustomer(@Valid @RequestBody Customer customer,@PathVariable int customerId) throws CouponSystemException{
        administratorService.updateCustomer(customer,customerId);
    }

    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("customers/{customerId}")
    public void deleteCustomer(@PathVariable int customerId) throws CouponSystemException{
        administratorService.deleteCustomer(customerId);
    }

    @GetMapping("customers")
    public List<Customer> getAllCustomers(){
        return administratorService.getAllCustomers();
    }

    @GetMapping("customers/{customerId}")
    Customer getSingleCustomer(@PathVariable int customerId) throws CouponSystemException{
        return administratorService.getSingleCustomer(customerId);
    }

}
