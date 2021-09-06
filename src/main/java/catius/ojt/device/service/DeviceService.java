package catius.ojt.device.service;

import catius.ojt.device.service.dto.*;

import java.util.List;

public interface DeviceService {
    DeviceDto register(DeviceDto deviceDto);

    DeviceDto findOne(Long deviceId);

    List<DeviceDto> findDevices(String serialNumber, String macAddress, String qrCode);

    List<DiscardDeviceDto> findAllDiscardedDevice();

    DeviceDto updateDevice(Long deviceId, DeviceDto updateDeviceDto);

    DiscardDeviceDto deleteDevice(Long deviceId);

    DeviceDto changeDeviceStatus(Long deviceId);

    void validateDeviceAttribute(String serialNumber, String macAddress, String qrCode);


}
