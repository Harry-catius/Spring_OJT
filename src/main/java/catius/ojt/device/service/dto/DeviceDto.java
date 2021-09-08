package catius.ojt.device.service.dto;

import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class DeviceDto {
    private Long deviceId;
    private String serialNumber;
    private String macAddress;
    private String qrCode;
    private DeviceStatus status;

}
