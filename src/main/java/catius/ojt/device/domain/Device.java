package catius.ojt.device.domain;


import catius.ojt.device.service.dto.DeviceDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@NoArgsConstructor()
@AllArgsConstructor
@Builder
public class Device {
    @Id
    @GeneratedValue
    private Long deviceId;

    @NotBlank(message = "시리얼 넘버는 필수입니다.")
    private String serialNumber;

    @NotBlank(message = "mac 주소는 필수입니다.")
    private String macAddress;

    @NotBlank(message = "QR코드는 필수 입니다.")
    private String qrCode;

    @Enumerated(EnumType.STRING)
    private DeviceStatus status; // ACTIVE, INACTIVE

    @Enumerated(EnumType.STRING)
    private DiscardStatus discardStatus;

    public static Device toEntity(DeviceDto deviceDto) {
        return Device.builder()
                .serialNumber(deviceDto.getSerialNumber())
                .macAddress(deviceDto.getMacAddress())
                .qrCode(deviceDto.getQrCode())
                .status(DeviceStatus.ACTIVE)
                .discardStatus(DiscardStatus.MAINTAIN)
                .build();
    }

    public void discardDevice() {
        this.status = DeviceStatus.INACTIVE;
        this.discardStatus = DiscardStatus.DISCARD;
    }

    public Boolean isActive() {
        return this.getStatus() == DeviceStatus.ACTIVE;
    }


    public void changeDeviceStatus() {
        if(this.getStatus().equals(DeviceStatus.ACTIVE)) this.status = DeviceStatus.INACTIVE;
        else this.status = DeviceStatus.ACTIVE;
    }


    public void update(Device device) {
        if(! "".equals(device.getSerialNumber()) && device.getSerialNumber() != null) {
            this.serialNumber = device.getSerialNumber();
        }
        if(! "".equals(device.getMacAddress()) && device.getMacAddress() != null) {
            this.macAddress = device.getMacAddress();
        }
        if(! "".equals(device.getQrCode()) && device.getQrCode() != null) {
            this.qrCode = device.getQrCode();
        }
    }
}
