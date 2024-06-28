package com.example.groceriesShoping.service.impl;

import com.example.groceriesShoping.dto.CompanyDto;
import com.example.groceriesShoping.model.Company;
import com.example.groceriesShoping.repository.CompanyRepository;
import com.example.groceriesShoping.service.CompanyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CompanyServiceImpl implements CompanyService {
    private final CompanyRepository companyRepository;

    public CompanyServiceImpl(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    @Override
    public Company findCompanyById(Long id) {
        // TODO: exceptions
        return companyRepository.findById(id).orElseThrow();
    }

    @Override
    public List<Company> findAllCompanies() {
        return companyRepository.findAll();
    }

    @Override
    public Company addCompany(CompanyDto company) {
        Company company1 = new Company();
        company1.setAddress(company.getCompanyAddress());
        company1.setName(company.getCompanyName());
        company1.setPhone(company.getCompanyPhone());
        company1.setEmail(company.getCompanyEmail());

        return companyRepository.save(company1);
    }

    @Override
    public Company updateCompany(Long id,CompanyDto company) {
        Company company1 = companyRepository.findById(id).orElseThrow();
        company1.setAddress(company.getCompanyAddress());
        company1.setName(company.getCompanyName());
        company1.setPhone(company.getCompanyPhone());
        company1.setEmail(company.getCompanyEmail());
        return companyRepository.save(company1);
    }

    @Override
    public void deleteCompany(Long id) {
        companyRepository.deleteById(id);

    }
}
