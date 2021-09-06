package catius.ojt.device.service.dto;

import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.domain.DiscardDevice;
import catius.ojt.device.domain.DiscardStatus;

public class DeviceFactory {

    public static Device getDevice(DeviceDto deviceDto) {
        return Device.builder()
                .deviceId(deviceDto.getDeviceId())
                .serialNumber(deviceDto.getSerialNumber())
                .macAddress(deviceDto.getMacAddress())
                .qrCode(deviceDto.getQrCode())
                .status(DeviceStatus.ACTIVE)
                .discardStatus(DiscardStatus.MAINTAIN)
                .build();
    }

    public static DeviceDto getDeviceDto(Device device) {
        return DeviceDto.builder()
                .deviceId(device.getDeviceId())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .status(device.getStatus())
                .build();
    }

    public static DiscardDevice getDiscardDevice(Device device) {
        return DiscardDevice.builder()
                .discardedDeviceId(device.getDeviceId())
                .discardStatus(DiscardStatus.DISCARD)
                .build();
    }

    public static DiscardDeviceDto getDiscardDeviceDto(DiscardDevice discardDevice) {
        return DiscardDeviceDto.builder()
                .discardedDeviceId(discardDevice.getDiscardedDeviceId())
                .discardStatus(discardDevice.getDiscardStatus())
                .build();
    }

}
