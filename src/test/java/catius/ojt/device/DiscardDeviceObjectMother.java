package catius.ojt.device;

import catius.ojt.device.domain.DiscardStatus;
import catius.ojt.device.domain.DiscardedDevice;
import catius.ojt.device.service.dto.RegisterDiscardedDeviceDto;
import catius.ojt.device.service.dto.SelectDiscardedDeviceDto;

import java.util.Arrays;
import java.util.List;

public class DiscardDeviceObjectMother {

    public static RegisterDiscardedDeviceDto defaultDiscardedDevice() {
        return RegisterDiscardedDeviceDto.builder()
                .discardedDeviceId(1L)
                .discardStatus(DiscardStatus.DISCARD)
                .build();
    }

    public static List<SelectDiscardedDeviceDto>  defaultDiscardedDeviceList(){
        return Arrays.asList(
                SelectDiscardedDeviceDto.builder()
                        .discardedDeviceId(1L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                SelectDiscardedDeviceDto.builder()
                        .discardedDeviceId(2L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build(),
                SelectDiscardedDeviceDto.builder()
                        .discardedDeviceId(3L)
                        .discardStatus(DiscardStatus.DISCARD)
                        .build()
        );
    }

}
