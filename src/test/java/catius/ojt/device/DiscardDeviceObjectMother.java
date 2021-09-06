package catius.ojt.device;

import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.service.dto.DiscardDeviceDto;

import java.util.Arrays;
import java.util.List;

public class DiscardDeviceObjectMother {

    public static List<DiscardDeviceDto>  defaultDiscardedDeviceList(){
        return Arrays.asList(
                DiscardDeviceDto.builder()
                        .discardedDeviceId(1L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                DiscardDeviceDto.builder()
                        .discardedDeviceId(2L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                DiscardDeviceDto.builder()
                        .discardedDeviceId(3L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build()
        );
    }

}
