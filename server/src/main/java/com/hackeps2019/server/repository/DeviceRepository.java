package com.hackeps2019.server.repository;

import com.hackeps2019.server.domain.Device;
import com.hackeps2019.server.domain.ReportStatus;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DeviceRepository extends MongoRepository<Device, String> {

    List<Device> findAllByUserAndStatus(String user, ReportStatus status);

    Device findDeviceByName(String name);
}
