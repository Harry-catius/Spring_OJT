package catius.ojt.device.controller.dto.response;

import catius.ojt.device.service.dto.DiscardDeviceDto;
import catius.ojt.device.domain.DiscardStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscardDeviceResponse {
    private Long discardedDeviceId;
    private DiscardStatus discardStatus;

    public static DiscardDeviceResponse fromDto(DiscardDeviceDto discardDevice) {
        return DiscardDeviceResponse.builder()
                .discardedDeviceId(discardDevice.getDiscardedDeviceId())
                .discardStatus(discardDevice.getDiscardStatus())
                .build();
    }

}
