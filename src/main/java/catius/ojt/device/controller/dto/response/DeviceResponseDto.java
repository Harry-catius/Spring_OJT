package catius.ojt.device.controller.dto.response;

import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.service.dto.RegisterDeviceDto;
import catius.ojt.device.service.dto.SelectDeviceDto;
import catius.ojt.device.service.dto.UpdateDeviceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponseDto {
    private Long deviceId;
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private DeviceStatus status;

    public static DeviceResponseDto fromDto(RegisterDeviceDto registerDeviceDto) {
        return DeviceResponseDto.builder()
                .deviceId(registerDeviceDto.getDeviceId())
                .serialNumber(registerDeviceDto.getSerialNumber())
                .macAddress(registerDeviceDto.getMacAddress())
                .qrCode(registerDeviceDto.getQrCode())
                .status(registerDeviceDto.getStatus())
                .build();
    }

    public static DeviceResponseDto fromDto(SelectDeviceDto selectDeviceDto) {
        return DeviceResponseDto.builder()
                .deviceId(selectDeviceDto.getDeviceId())
                .serialNumber(selectDeviceDto.getSerialNumber())
                .macAddress(selectDeviceDto.getMacAddress())
                .qrCode(selectDeviceDto.getQrCode())
                .status(selectDeviceDto.getStatus())
                .build();
    }


    public static DeviceResponseDto fromDto(UpdateDeviceDto updateDeviceDto) {
        return DeviceResponseDto.builder()
                .deviceId(updateDeviceDto.getDeviceId())
                .serialNumber(updateDeviceDto.getSerialNumber())
                .macAddress(updateDeviceDto.getMacAddress())
                .qrCode(updateDeviceDto.getQrCode())
                .status(updateDeviceDto.getStatus())
                .build();
    }
}
