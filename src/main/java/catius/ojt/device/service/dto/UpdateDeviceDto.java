package catius.ojt.device.service.dto;

import catius.ojt.device.controller.dto.request.DeviceRequestDto;
import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UpdateDeviceDto {
    private Long deviceId;
    @NotBlank(message = "시리얼 넘버는 필수입니다.")
    private String serialNumber;
    @NotBlank(message = "mac 주소는 필수입니다.")
    private String macAddress;
    @NotBlank(message = "QR코드는 필수 입니다.")
    private String qrCode;
    private DeviceStatus status;

    public static UpdateDeviceDto toEntity(DeviceRequestDto deviceRequestDto) {
        return UpdateDeviceDto.builder()
                .serialNumber(deviceRequestDto.getSerialNumber())
                .macAddress(deviceRequestDto.getMacAddress())
                .qrCode(deviceRequestDto.getQrCode())
                .build();
    }


    public static UpdateDeviceDto fromEntity(Device device) {
        return UpdateDeviceDto.builder()
                .deviceId(device.getDeviceId())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .status(device.getStatus())
                .build();
    }

    public static Device toEntity(UpdateDeviceDto device) {
        return Device.builder()
                .deviceId(device.getDeviceId())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .status(device.getStatus())
                .build();
    }

}