package catius.ojt.device.service.dto;

import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.domain.Device;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectDeviceDto {
    private Long deviceId;
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private DeviceStatus status;

    public static SelectDeviceDto fromEntity(Device device) {
        return SelectDeviceDto.builder()
                .deviceId(device.getDeviceId())
                .serialNumber(device.getSerialNumber())
                .macAddress(device.getMacAddress())
                .qrCode(device.getQrCode())
                .status(device.getStatus())
                .build();
    }

}