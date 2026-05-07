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
public class PurchaseService {

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Purchase createPurchase(Long warehouseId, Long supplierId, String poNumber, Double totalQuantity,
                                  Double totalAmount, LocalDate expectedDeliveryDate, LocalDate purchaseDate,
                                  String notes, Long createdBy) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Company supplier = companyRepository.findById(supplierId)
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        Purchase purchase = Purchase.builder()
                .warehouse(warehouse)
                .supplier(supplier)
                .poNumber(poNumber)
                .totalQuantity(totalQuantity)
                .totalAmountINR(totalAmount)
                .status(Purchase.PurchaseStatus.PENDING)
                .expectedDeliveryDate(expectedDeliveryDate)
                .purchaseDate(purchaseDate)
                .receivedQuantity(0.0)
                .notes(notes)
                .createdBy(createdBy)
                .build();

        return purchaseRepository.save(purchase);
    }

    public List<Purchase> getPurchasesByWarehouse(Long warehouseId) {
        return purchaseRepository.findByWarehouseId(warehouseId);
    }

    public Page<Purchase> getPurchasesByWarehousePaged(Long warehouseId, Pageable pageable) {
        return purchaseRepository.findByWarehouseIdOrderByDateDesc(warehouseId, pageable);
    }

    public Optional<Purchase> getPurchaseById(Long id) {
        return purchaseRepository.findById(id);
    }

    public Optional<Purchase> getPurchaseByPoNumber(String poNumber) {
        return purchaseRepository.findByPoNumber(poNumber);
    }

    public Purchase updatePurchaseStatus(Long id, Purchase.PurchaseStatus status) {
        Purchase purchase = purchaseRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Purchase not found"));
        purchase.setStatus(status);
        return purchaseRepository.save(purchase);
    }
}
