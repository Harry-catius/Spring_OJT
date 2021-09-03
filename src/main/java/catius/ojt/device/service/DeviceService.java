package catius.ojt.device.service;

import catius.ojt.device.service.dto.*;

import java.util.List;

public interface DeviceService {
    RegisterDeviceDto register(RegisterDeviceDto registerDeviceDto);

    SelectDeviceDto findOne(Long deviceId);

    List<SelectDeviceDto> findDevices(String serialNumber, String macAddress, String qrCode);

    List<SelectDiscardedDeviceDto> findAllDiscardedDevice();

    UpdateDeviceDto updateDevice(Long deviceId, UpdateDeviceDto updateDeviceDto);

    RegisterDiscardedDeviceDto deleteDevice(Long deviceId);

    UpdateDeviceDto changeDeviceStatus(Long deviceId);

    void validateDeviceAttribute(String serialNumber, String macAddress, String qrCode);


}
