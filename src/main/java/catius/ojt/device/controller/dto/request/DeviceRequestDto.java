package catius.ojt.device.controller.dto.request;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequestDto {

    @NotEmpty(message = "시리얼 넘버는 필수입니다.")
    private String serialNumber;
    @NotEmpty(message = "mac 주소는 필수입니다.")
    private String macAddress;
    @NotEmpty(message = "QR코드는 필수 입니다.")
    private String qrCode;

}
