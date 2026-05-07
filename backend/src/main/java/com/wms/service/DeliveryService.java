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
public class DeliveryService {

    @Autowired
    private DeliveryRepository deliveryRepository;

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private PurchaseRepository purchaseRepository;

    @Autowired
    private SaleRepository saleRepository;

    public Delivery createDelivery(Long warehouseId, Delivery.DeliveryType type, String deliveryNumber,
                                  String partyName, String contactNumber, Double quantity, LocalDate expectedDate,
                                  String transporterName, String trackingNumber, String vehicleNumber,
                                  String driverName, String driverPhone, String notes, Long purchaseId, Long saleId) {
        Warehouse warehouse = warehouseRepository.findById(warehouseId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        Purchase purchase = purchaseId != null ? purchaseRepository.findById(purchaseId).orElse(null) : null;
        Sale sale = saleId != null ? saleRepository.findById(saleId).orElse(null) : null;

        Delivery delivery = Delivery.builder()
                .warehouse(warehouse)
                .purchase(purchase)
                .sale(sale)
                .deliveryType(type)
                .deliveryNumber(deliveryNumber)
                .partyName(partyName)
                .contactNumber(contactNumber)
                .quantity(quantity)
                .expectedDate(expectedDate)
                .status(Delivery.DeliveryStatus.PENDING)
                .transporterName(transporterName)
                .trackingNumber(trackingNumber)
                .vehicleNumber(vehicleNumber)
                .driverName(driverName)
                .driverPhone(driverPhone)
                .notes(notes)
                .isDelayed(false)
                .build();

        return deliveryRepository.save(delivery);
    }

    public List<Delivery> getDeliveriesByWarehouse(Long warehouseId) {
        return deliveryRepository.findByWarehouseId(warehouseId);
    }

    public Page<Delivery> getDeliveriesByWarehouseAndType(Long warehouseId, Delivery.DeliveryType type, Pageable pageable) {
        return deliveryRepository.findByWarehouseAndType(warehouseId, type, pageable);
    }

    public Optional<Delivery> getDeliveryById(Long id) {
        return deliveryRepository.findById(id);
    }

    public Optional<Delivery> getDeliveryByNumber(String deliveryNumber) {
        return deliveryRepository.findByDeliveryNumber(deliveryNumber);
    }

    public Delivery updateDeliveryStatus(Long id, Delivery.DeliveryStatus status, LocalDate actualDate) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setStatus(status);
        delivery.setActualDate(actualDate);
        return deliveryRepository.save(delivery);
    }

    public Delivery markAsDelayed(Long id) {
        Delivery delivery = deliveryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Delivery not found"));
        delivery.setIsDelayed(true);
        return deliveryRepository.save(delivery);
    }
}
