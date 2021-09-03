package catius.ojt.device;

import catius.ojt.device.controller.dto.request.DeviceRequestDto;
import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.domain.DiscardedDevice;
import catius.ojt.device.service.dto.RegisterDeviceDto;
import catius.ojt.device.service.dto.SelectDeviceDto;
import catius.ojt.device.service.dto.UpdateDeviceDto;

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

    public static Device defaultTestDevice() {
        return Device.builder()
                .deviceId(5L)
                .serialNumber("testSerialNumber")
                .macAddress("testMacAddress")
                .qrCode("testQrCode")
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

    public static List<SelectDeviceDto> defaultSelectDeviceList() {
        return Arrays.asList(
                SelectDeviceDto.builder()
                        .deviceId(1L)
                        .serialNumber("serialNumber1")
                        .macAddress("macAddress1")
                        .qrCode("qrCode1")
                        .status(DeviceStatus.ACTIVE)
                        .build(),
                SelectDeviceDto.builder()
                        .deviceId(2L)
                        .serialNumber("serialNumber2")
                        .macAddress("macAddress2")
                        .qrCode("qrCode2")
                        .status(DeviceStatus.ACTIVE)
                        .build());
    }

    public static List<DiscardedDevice> defaultDiscardedDeviceList() {
        return Arrays.asList(
                DiscardedDevice.builder()
                        .discardedDeviceId(1L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                DiscardedDevice.builder()
                        .discardedDeviceId(2L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build());
    }

    public static DeviceRequestDto registerRequestDto() {
        return DeviceRequestDto.builder()
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .build();
    }

    public static RegisterDeviceDto registerResponseDto() {
        return RegisterDeviceDto.builder()
                .deviceId(1L)
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.ACTIVE)
                .build();
    }

    public static RegisterDeviceDto registerRequest() {
        return RegisterDeviceDto.builder()
                .serialNumber("serialNumber")
                .macAddress("macAddress")
                .qrCode("qrCode")
                .status(DeviceStatus.ACTIVE)
                .build();
    }


    public static Device updateRequest() {
        return Device.builder()
                .serialNumber("updatedSerialNumber")
                .macAddress("updatedMacAddress")
                .qrCode("updatedQrCode")
                .build();
    }
    public static UpdateDeviceDto updateRequestDto() {
        return UpdateDeviceDto.builder()
                .serialNumber("updatedSerialNumber")
                .macAddress("updatedMacAddress")
                .qrCode("updatedQrCode")
                .build();
    }

    public static Device deleteRequest() {
        return Device.builder()
                .serialNumber("updatedSerialNumber")
                .macAddress("updatedMacAddress")
                .qrCode("updatedQrCode")
                .status(DeviceStatus.INACTIVE)
                .discardStatus(DiscardStatus.MAINTAIN)
                .build();
    }
}
