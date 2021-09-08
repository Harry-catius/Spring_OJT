package catius.ojt.device.repository;

import catius.ojt.device.domain.Device;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long>, JpaSpecificationExecutor<Device> {

    Optional<Device> findBySerialNumber(String serialNumber);

    Optional<Device> findByMacAddress(String macAddress);

    Optional<Device> findByQrCode(String qrCode);

    List<Device> findBySerialNumberContaining(String serialNumber);

    List<Device> findByMacAddressContaining(String macAddress);

    List<Device> findByQrCodeContaining(String qrCode);

}
