package catius.ojt.device.controller.dto.response;

import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.service.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceResponse {
    private Long deviceId;
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private DeviceStatus status;

    public static DeviceResponse fromDto(DeviceDto deviceDto) {
        return DeviceResponse.builder()
                .deviceId(deviceDto.getDeviceId())
                .serialNumber(deviceDto.getSerialNumber())
                .macAddress(deviceDto.getMacAddress())
                .qrCode(deviceDto.getQrCode())
                .status(deviceDto.getStatus())
                .build();
    }
}
