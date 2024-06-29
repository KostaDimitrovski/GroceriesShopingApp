package com.example.groceriesShoping.controller;

import com.example.groceriesShoping.dto.CompanyDto;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.service.CompanyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
public class CompanyController {
    private final CompanyService companyService;

    public CompanyController(CompanyService companyService) {
        this.companyService = companyService;
    }

    @GetMapping("/list")
    public List<Company> findAll(){
       return companyService.findAllCompanies();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Company> findById(@PathVariable Long id){
        Company company= companyService.findCompanyById(id);
        if(company==null){
            return ResponseEntity.notFound().build();
        }else {
            return ResponseEntity.ok(company);
        }
    }

    @PostMapping("/add")
    public ResponseEntity<Company> addCompany(@RequestBody CompanyDto companyDto){
        Company company= companyService.addCompany(companyDto);
        if(company==null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(company);
    }

    @PutMapping("/edit/{id}")
    public ResponseEntity<Company> editCompany(@PathVariable Long id, @RequestBody CompanyDto companyDto){
        Company company= companyService.updateCompany(id, companyDto);
        if(company==null){return ResponseEntity.notFound().build();}
        return ResponseEntity.ok(company);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<Company> deleteCompany(@PathVariable Long id){
        Company company=companyService.findCompanyById(id);
        if(company==null){return ResponseEntity.notFound().build();}
        else {
            companyService.deleteCompany(id);
            return ResponseEntity.ok(company);
        }
    }
}
