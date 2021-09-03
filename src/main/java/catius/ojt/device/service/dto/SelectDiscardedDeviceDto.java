package catius.ojt.device.service.dto;

import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.domain.DiscardedDevice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class SelectDiscardedDeviceDto {
    private Long discardedDeviceId;
    private DiscardStatus discardStatus;

    public static SelectDiscardedDeviceDto fromEntity(DiscardedDevice discardedDevice) {
        return SelectDiscardedDeviceDto.builder()
                .discardedDeviceId(discardedDevice.getDiscardedDeviceId())
                .discardStatus(discardedDevice.getDiscardStatus())
                .build();
    }
}