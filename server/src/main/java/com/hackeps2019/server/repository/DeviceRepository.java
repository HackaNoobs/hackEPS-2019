package com.hackeps2019.server.repository;

import com.hackeps2019.server.domain.Device;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {
}
