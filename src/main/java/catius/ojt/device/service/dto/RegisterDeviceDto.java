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
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class RegisterDeviceDto {
    private Long deviceId;
    @NotBlank(message = "시리얼 넘버는 필수입니다.")
    private String serialNumber;
    @NotBlank(message = "mac 주소는 필수입니다.")
    private String macAddress;
    @NotBlank(message = "QR코드는 필수 입니다.")
    private String qrCode;
    private DeviceStatus status;

    public static RegisterDeviceDto toDto(DeviceRequestDto deviceRequestDto){
        return RegisterDeviceDto.builder()
                .serialNumber(deviceRequestDto.getSerialNumber())
                .macAddress(deviceRequestDto.getMacAddress())
                .qrCode(deviceRequestDto.getQrCode())
                .build();
    }

    public static RegisterDeviceDto fromEntity(Device device) {
        return RegisterDeviceDto.builder()
                .deviceId(device.getDeviceId())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .status(device.getStatus())
                .build();
    }


}
