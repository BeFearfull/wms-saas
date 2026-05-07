package com.wms.service;

import com.wms.entity.Warehouse;
import com.wms.entity.User;
import com.wms.repository.WarehouseRepository;
import com.wms.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import jakarta.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class WarehouseService {

    @Autowired
    private WarehouseRepository warehouseRepository;

    @Autowired
    private UserRepository userRepository;

    public Warehouse createWarehouse(Long userId, String name, String location, Warehouse.WarehouseType type, 
                                     String city, String state, String postalCode, String contactPersonName, 
                                     String contactPhone, String contactEmail, Double totalCapacity) {
        User user = userRepository.findById(userId).orElseThrow(() -> new RuntimeException("User not found"));

        Warehouse warehouse = Warehouse.builder()
                .owner(user)
                .name(name)
                .location(location)
                .warehouseType(type)
                .city(city)
                .state(state)
                .postalCode(postalCode)
                .contactPersonName(contactPersonName)
                .contactPhone(contactPhone)
                .contactEmail(contactEmail)
                .totalCapacity(totalCapacity)
                .isActive(true)
                .build();

        return warehouseRepository.save(warehouse);
    }

    public List<Warehouse> getWarehousesByUserId(Long userId) {
        return warehouseRepository.findByOwnerId(userId);
    }

    public List<Warehouse> getActiveWarehousesByUserId(Long userId) {
        return warehouseRepository.findActiveByOwnerId(userId);
    }

    public Optional<Warehouse> getWarehouseById(Long id, Long userId) {
        return warehouseRepository.findByIdAndOwnerId(id, userId);
    }

    public Warehouse updateWarehouse(Long id, Long userId, String name, String location, 
                                     String city, String state, String postalCode, String contactPersonName, 
                                     String contactPhone, String contactEmail) {
        Warehouse warehouse = warehouseRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));

        if (name != null) warehouse.setName(name);
        if (location != null) warehouse.setLocation(location);
        if (city != null) warehouse.setCity(city);
        if (state != null) warehouse.setState(state);
        if (postalCode != null) warehouse.setPostalCode(postalCode);
        if (contactPersonName != null) warehouse.setContactPersonName(contactPersonName);
        if (contactPhone != null) warehouse.setContactPhone(contactPhone);
        if (contactEmail != null) warehouse.setContactEmail(contactEmail);

        return warehouseRepository.save(warehouse);
    }

    public void deleteWarehouse(Long id, Long userId) {
        Warehouse warehouse = warehouseRepository.findByIdAndOwnerId(id, userId)
                .orElseThrow(() -> new RuntimeException("Warehouse not found"));
        warehouse.setIsActive(false);
        warehouseRepository.save(warehouse);
    }
}
