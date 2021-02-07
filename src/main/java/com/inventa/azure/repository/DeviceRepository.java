package com.inventa.azure.repository;

import com.inventa.azure.domain.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String>, CustomDeviceRepository {


}