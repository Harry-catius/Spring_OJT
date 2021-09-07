package catius.ojt.device.controller.dto.request;

import catius.ojt.device.domain.Device;
import catius.ojt.device.service.dto.DeviceDto;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceRequest {

    @NotEmpty(message = "시리얼 넘버는 필수입니다.")
    @ApiModelProperty(example = "serialNumber", required = true,notes = "등록, 수정시에 사용")
    private String serialNumber;
    @NotEmpty(message = "mac 주소는 필수입니다.")
    @ApiModelProperty(example = "macAddress",required = true, notes = "등록, 수정시에 사용")
    private String macAddress;
    @NotEmpty(message = "QR코드는 필수 입니다.")
    @ApiModelProperty(example = "qrCode", required = true, notes = "등록, 수정시에 사용")
    private String qrCode;


    public static DeviceDto toDto(DeviceRequest deviceRequest) {
        return DeviceDto.builder()
                .serialNumber(deviceRequest.getSerialNumber())
                .macAddress(deviceRequest.getMacAddress())
                .qrCode(deviceRequest.getQrCode())
                .build();
    }

}
