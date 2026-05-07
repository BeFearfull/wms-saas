package com.wms.service;

import com.wms.entity.Company;
import com.wms.entity.Warehouse;
import com.wms.repository.CompanyRepository;
import com.wms.repository.WarehouseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class CompanyService {

    @Autowired
    private CompanyRepository companyRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public Company createCompany(Long warehouseId, String name, Company.CompanyType type, String sourceLocation,
                                String city, String state, String postalCode, String contactPersonName,
                                String contactPhone, String contactEmail, String gstin, String pan) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Company company = Company.builder()
                .warehouse(warehouse)
                .name(name)
                .companyType(type)
                .sourceLocation(sourceLocation)
                .city(city)
                .state(state)
                .postalCode(postalCode)
                .contactPersonName(contactPersonName)
                .contactPhone(contactPhone)
                .contactEmail(contactEmail)
                .gstin(gstin)
                .pan(pan)
                .isActive(true)
                .build();

        return companyRepository.save(company);
    }

    public List<Company> getCompaniesByWarehouse(Long warehouseId) {
        return companyRepository.findActiveByWarehouseId(warehouseId);
    }

    public Optional<Company> getCompanyById(Long id, Long warehouseId) {
        return companyRepository.findByIdAndWarehouseId(id, warehouseId);
    }

    public Company updateCompany(Long id, Long warehouseId, String name, Company.CompanyType type,
                                String sourceLocation, String city, String state, String postalCode,
                                String contactPersonName, String contactPhone, String contactEmail,
                                String gstin, String pan) {
        Company company = companyRepository.findByIdAndWarehouseId(id, warehouseId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        if (name != null) company.setName(name);
        if (type != null) company.setCompanyType(type);
        if (sourceLocation != null) company.setSourceLocation(sourceLocation);
        if (city != null) company.setCity(city);
        if (state != null) company.setState(state);
        if (postalCode != null) company.setPostalCode(postalCode);
        if (contactPersonName != null) company.setContactPersonName(contactPersonName);
        if (contactPhone != null) company.setContactPhone(contactPhone);
        if (contactEmail != null) company.setContactEmail(contactEmail);
        if (gstin != null) company.setGstin(gstin);
        if (pan != null) company.setPan(pan);

        return companyRepository.save(company);
    }

    public void deleteCompany(Long id, Long warehouseId) {
        Company company = companyRepository.findByIdAndWarehouseId(id, warehouseId)
                .orElseThrow(() -> new RuntimeException("Company not found"));
        company.setIsActive(false);
        companyRepository.save(company);
    }
}
