package com.wms.service;

import com.wms.entity.Product;
import com.wms.entity.Warehouse;
import com.wms.entity.Company;
import com.wms.repository.ProductRepository;
import com.wms.repository.WarehouseRepository;
import com.wms.repository.CompanyRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private CompanyRepository companyRepository;

    public Product createProduct(Long warehouseId, Long companyId, String name, String category, Double price,
                                Product.UnitType unitType, Double minimumStockLevel, Double maximumStockLevel,
                                Double reorderQuantity, String sku, String barcode, String description) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        Company company = companyRepository.findById(companyId)
                .orElseThrow(() -> new RuntimeException("Company not found"));

        Product product = Product.builder()
                .warehouse(warehouse)
                .company(company)
                .name(name)
                .category(category)
                .priceInINR(price)
                .unitType(unitType)
                .currentStock(0.0)
                .minimumStockLevel(minimumStockLevel)
                .maximumStockLevel(maximumStockLevel)
                .reorderQuantity(reorderQuantity)
                .sku(sku)
                .barcode(barcode)
                .description(description)
                .isActive(true)
                .build();

        return productRepository.save(product);
    }

    public List<Product> getProductsByWarehouse(Long warehouseId) {
        return productRepository.findActiveByWarehouseId(warehouseId);
    }

    public Optional<Product> getProductById(Long id, Long warehouseId) {
        return productRepository.findByIdAndWarehouseId(id, warehouseId);
    }

    public List<Product> getLowStockProducts(Long warehouseId) {
        return productRepository.findLowStockByWarehouseId(warehouseId);
    }

    public Product updateProduct(Long id, Long warehouseId, String name, String category, Double price,
                                Product.UnitType unitType, Double minimumStockLevel, Double maximumStockLevel,
                                Double reorderQuantity, String sku, String barcode, String description) {
        Product product = productRepository.findByIdAndWarehouseId(id, warehouseId)
                .orElseThrow(() -> new RuntimeException("Product not found"));

        if (name != null) product.setName(name);
        if (category != null) product.setCategory(category);
        if (price != null) product.setPriceInINR(price);
        if (unitType != null) product.setUnitType(unitType);
        if (minimumStockLevel != null) product.setMinimumStockLevel(minimumStockLevel);
        if (maximumStockLevel != null) product.setMaximumStockLevel(maximumStockLevel);
        if (reorderQuantity != null) product.setReorderQuantity(reorderQuantity);
        if (sku != null) product.setSku(sku);
        if (barcode != null) product.setBarcode(barcode);
        if (description != null) product.setDescription(description);

        return productRepository.save(product);
    }

    public void deleteProduct(Long id, Long warehouseId) {
        Product product = productRepository.findByIdAndWarehouseId(id, warehouseId)
                .orElseThrow(() -> new RuntimeException("Product not found"));
        product.setIsActive(false);
        productRepository.save(product);
    }
}
