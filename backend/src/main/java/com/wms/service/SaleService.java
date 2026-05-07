package com.wms.service;

import com.wms.entity.*;
import com.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class SaleService {

    @Autowired
    private SaleRepository saleRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Sale createSale(Long warehouseId, Long customerId, String invoiceNumber, Double totalQuantity,
                          Double totalAmount, LocalDate saleDate, LocalDate deliveryDate, String notes, Long createdBy) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Company customer = companyRepository.findById(customerId)
                .orElseThrow(() -> new RuntimeException("Customer not found"));

        Sale sale = Sale.builder()
                .warehouse(warehouse)
                .customer(customer)
                .invoiceNumber(invoiceNumber)
                .totalQuantity(totalQuantity)
                .totalAmountINR(totalAmount)
                .status(Sale.SaleStatus.PENDING)
                .saleDate(saleDate)
                .deliveryDate(deliveryDate)
                .shippedQuantity(0.0)
                .notes(notes)
                .createdBy(createdBy)
                .build();

        return saleRepository.save(sale);
    }

    public List<Sale> getSalesByWarehouse(Long warehouseId) {
        return saleRepository.findByWarehouseId(warehouseId);
    }

    public Page<Sale> getSalesByWarehousePaged(Long warehouseId, Pageable pageable) {
        return saleRepository.findByWarehouseIdOrderByDateDesc(warehouseId, pageable);
    }

    public Optional<Sale> getSaleById(Long id) {
        return saleRepository.findById(id);
    }

    public Optional<Sale> getSaleByInvoiceNumber(String invoiceNumber) {
        return saleRepository.findByInvoiceNumber(invoiceNumber);
    }

    public Sale updateSaleStatus(Long id, Sale.SaleStatus status) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
        sale.setStatus(status);
        return saleRepository.save(sale);
    }
}
