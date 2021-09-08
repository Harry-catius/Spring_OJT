package catius.ojt.device.service.dto;

import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.domain.DiscardDevice;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DiscardDeviceDto {
    private Long discardedDeviceId;
    private DiscardStatus discardStatus;

//    public static DiscardDeviceDto fromEntity(DiscardDevice discardDevice) {
//        return DiscardDeviceDto.builder()
//                .discardedDeviceId(discardDevice.getDiscardedDeviceId())
//                .discardStatus(discardDevice.getDiscardStatus())
//                .build();
//    }

}
