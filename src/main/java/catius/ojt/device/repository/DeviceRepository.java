package catius.ojt.device.repository;

import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device,Long> {

    Optional<Device> findBySerialNumber(String serialNumber);

    Optional<Device> findByMacAddress(String macAddress);

    Optional<Device> findByQrCode(String qrCode);

    List<Device> findBySerialNumberContaining(String serialNumber);

    List<Device> findByMacAddressContaining(String macAddress);

    List<Device> findByQrCodeContaining(String qrCode);
//
//    @Modifying(clearAutomatically = true)
//    @Query("update Device d set d.serialNumber = :serialNumber, d.macAddress= :macAddress, d.qrCode=:qrCode where d.deviceId= :deviceId")
//    void updateDevice(
//            @Param(value = "serialNumber") String serialNumber,
//            @Param(value = "macAddress") String macAddress,
//            @Param(value = "qrCode") String qrCode,
//            @Param(value = "deviceId") Long deviceId);
//
//
//    @Modifying(clearAutomatically = true)
//    @Query("update Device d set d.status = :status where d.deviceId= :deviceId")
//    void changeDeviceStatus(
//            @Param(value = "status") DeviceStatus status,
//            @Param(value = "deviceId") Long deviceId);

}
