package com.wms.service;

import com.wms.entity.*;
import com.wms.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class InventoryTransactionService {

    @Autowired
    private InventoryTransactionRepository transactionRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    public InventoryTransaction createTransaction(Long warehouseId, Long productId, InventoryTransaction.TransactionType type,
                                                  Double quantity, InventoryTransaction.ReferenceType refType,
                                                  Long referenceId, Double pricePerUnit, String notes, Long createdBy) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        // Update product stock
        product.setCurrentStock(product.getCurrentStock() + quantity);
        productRepository.save(product);

        InventoryTransaction transaction = InventoryTransaction.builder()
                .warehouse(warehouse)
                .product(product)
                .transactionType(type)
                .quantity(quantity)
                .unitType(product.getUnitType())
                .referenceType(refType)
                .referenceId(referenceId)
                .pricePerUnit(pricePerUnit)
                .totalValue(quantity * (pricePerUnit != null ? pricePerUnit : 0))
                .notes(notes)
                .transactionDate(LocalDateTime.now())
                .createdBy(createdBy)
                .build();

        return transactionRepository.save(transaction);
    }

    public Page<InventoryTransaction> getTransactionsByWarehouse(Long warehouseId, Pageable pageable) {
        return transactionRepository.findByWarehouseIdOrderByDateDesc(warehouseId, pageable);
    }

    public List<InventoryTransaction> getTransactionsByProduct(Long productId) {
        return transactionRepository.findByProductIdOrderByDateDesc(productId);
    }

    public Optional<InventoryTransaction> getTransactionById(Long id) {
        return transactionRepository.findById(id);
    }
}
