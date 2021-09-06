package catius.ojt.device;

import catius.ojt.device.controller.dto.request.DeviceRequest;
import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.domain.DiscardDevice;
import catius.ojt.device.service.dto.DeviceDto;

import java.util.Arrays;
import java.util.List;


public class DeviceObjectMother {

    public static Device defaultDevice() {
        return Device.builder()
                .deviceId(1L)
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.ACTIVE)
                .discardStatus(DiscardStatus.MAINTAIN)
                .build();
    }

    public static List<Device> defaultDeviceList() {
        return Arrays.asList(
                Device.builder()
                    .deviceId(1L)
                    .serialNumber("serialNumber1")
                    .macAddress("macAddress1")
                    .qrCode("qrCode1")
                    .status(DeviceStatus.ACTIVE)
                    .discardStatus(DiscardStatus.MAINTAIN)
                    .build(),
                Device.builder()
                    .deviceId(2L)
                    .serialNumber("serialNumber2")
                    .macAddress("macAddress2")
                    .qrCode("qrCode2")
                    .status(DeviceStatus.ACTIVE)
                    .discardStatus(DiscardStatus.MAINTAIN)
                    .build());
    }

    public static List<DeviceDto> defaultDeviceListDto() {
        return Arrays.asList(
                DeviceDto.builder()
                        .deviceId(1L)
                        .serialNumber("serialNumber1")
                        .macAddress("macAddress1")
                        .qrCode("qrCode1")
                        .status(DeviceStatus.ACTIVE)
                        .build(),
                DeviceDto.builder()
                        .deviceId(2L)
                        .serialNumber("serialNumber2")
                        .macAddress("macAddress2")
                        .qrCode("qrCode2")
                        .status(DeviceStatus.ACTIVE)
                        .build());
    }

    public static List<DiscardDevice> defaultDiscardDeviceList() {
        return Arrays.asList(
                DiscardDevice.builder()
                        .discardedDeviceId(1L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                DiscardDevice.builder()
                        .discardedDeviceId(2L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build());
    }

    public static DeviceRequest registerRequestDto() {
        return DeviceRequest.builder()
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .build();
    }

    public static DeviceDto registerResponseDto() {
        return DeviceDto.builder()
                .deviceId(1L)
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.ACTIVE)
                .build();
    }

    public static DeviceDto registerRequest() {
        return DeviceDto.builder()
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.ACTIVE)
                .build();
    }

    public static DeviceDto updateRequestDto() {
        return DeviceDto.builder()
                .serialNumber("updatedSerialNumber")
                .macAddress("updatedMacAddress")
                .qrCode("updatedQrCode")
                .build();
    }

    public static Device deleteRequest() {
        return Device.builder()
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.INACTIVE)
                .discardStatus(DiscardStatus.MAINTAIN)
                .build();
    }
}
