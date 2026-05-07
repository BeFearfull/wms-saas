package com.wms.controller;

import com.wms.dto.CompanyDTO;
import com.wms.entity.Company;
import com.wms.service.CompanyService;
import com.wms.security.CurrentUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import jakarta.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/v1/warehouses/{warehouseId}/companies")
@CrossOrigin(origins = "*", maxAge = 3600)
public class CompanyController {

    @Autowired
    private CompanyService companyService;

    @PostMapping
    public ResponseEntity<?> createCompany(@CurrentUser Long userId, @PathVariable Long warehouseId,
                                          @Valid @RequestBody CompanyDTO companyDTO) {
        try {
            Company company = companyService.createCompany(
                    warehouseId,
                    companyDTO.getName(),
                    Company.CompanyType.valueOf(companyDTO.getCompanyType()),
                    companyDTO.getSourceLocation(),
                    companyDTO.getCity(),
                    companyDTO.getState(),
                    companyDTO.getPostalCode(),
                    companyDTO.getContactPersonName(),
                    companyDTO.getContactPhone(),
                    companyDTO.getContactEmail(),
                    companyDTO.getGstin(),
                    companyDTO.getPan()
            );
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToDTO(company));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to create company: " + e.getMessage()));
        }
    }

    @GetMapping
    public ResponseEntity<?> getCompanies(@PathVariable Long warehouseId) {
        try {
            List<CompanyDTO> companies = companyService.getCompaniesByWarehouse(warehouseId)
                    .stream()
                    .map(this::convertToDTO)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(companies);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to fetch companies: " + e.getMessage()));
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCompany(@PathVariable Long warehouseId, @PathVariable Long id) {
        try {
            Company company = companyService.getCompanyById(id, warehouseId)
                    .orElseThrow(() -> new RuntimeException("Company not found"));
            return ResponseEntity.ok(convertToDTO(company));
        } catch (Exception e) {
            return ResponseEntity.notFound().build();
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCompany(@PathVariable Long warehouseId, @PathVariable Long id,
                                          @Valid @RequestBody CompanyDTO companyDTO) {
        try {
            Company company = companyService.updateCompany(
                    id, warehouseId,
                    companyDTO.getName(),
                    Company.CompanyType.valueOf(companyDTO.getCompanyType()),
                    companyDTO.getSourceLocation(),
                    companyDTO.getCity(),
                    companyDTO.getState(),
                    companyDTO.getPostalCode(),
                    companyDTO.getContactPersonName(),
                    companyDTO.getContactPhone(),
                    companyDTO.getContactEmail(),
                    companyDTO.getGstin(),
                    companyDTO.getPan()
            );
            return ResponseEntity.ok(convertToDTO(company));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to update company: " + e.getMessage()));
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCompany(@PathVariable Long warehouseId, @PathVariable Long id) {
        try {
            companyService.deleteCompany(id, warehouseId);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ErrorResponseDTO("Failed to delete company: " + e.getMessage()));
        }
    }

    private CompanyDTO convertToDTO(Company company) {
        return CompanyDTO.builder()
                .id(company.getId())
                .name(company.getName())
                .companyType(company.getCompanyType().toString())
                .sourceLocation(company.getSourceLocation())
                .city(company.getCity())
                .state(company.getState())
                .postalCode(company.getPostalCode())
                .country(company.getCountry())
                .contactPersonName(company.getContactPersonName())
                .contactPhone(company.getContactPhone())
                .contactEmail(company.getContactEmail())
                .gstin(company.getGstin())
                .pan(company.getPan())
                .isActive(company.getIsActive())
                .createdAt(company.getCreatedAt())
                .updatedAt(company.getUpdatedAt())
                .build();
    }
}
