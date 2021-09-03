package catius.ojt.device.controller.dto.response;

import catius.ojt.device.service.dto.RegisterDiscardedDeviceDto;
import catius.ojt.device.service.dto.SelectDiscardedDeviceDto;
import catius.ojt.device.domain.DiscardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscardedDeviceResponseDto {
    private Long discardedDeviceId;
    private DiscardStatus discardStatus;

    public static DiscardedDeviceResponseDto fromDto(RegisterDiscardedDeviceDto discardedDevice) {
        return DiscardedDeviceResponseDto.builder()
                .discardedDeviceId(discardedDevice.getDiscardedDeviceId())
                .discardStatus(discardedDevice.getDiscardStatus())
                .build();
    }

    public static DiscardedDeviceResponseDto fromDto(SelectDiscardedDeviceDto discardedDevice) {
        return DiscardedDeviceResponseDto.builder()
                .discardedDeviceId(discardedDevice.getDiscardedDeviceId())
                .discardStatus(discardedDevice.getDiscardStatus())
                .build();
    }
}
