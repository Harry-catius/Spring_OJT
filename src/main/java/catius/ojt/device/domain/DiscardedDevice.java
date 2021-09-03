package catius.ojt.device.domain;


import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor()
@AllArgsConstructor
@Builder
public class DiscardedDevice {
    @Id
    @GeneratedValue(strategy = GenerationType.TABLE)
    private Long id;

    private Long discardedDeviceId;

    @Enumerated(EnumType.STRING)
    private DiscardStatus discardStatus; // ACTIVE, INACTIVE

    public static DiscardedDevice toEntity(Device device) {
        return DiscardedDevice.builder()
                .discardedDeviceId(device.getDeviceId())
                .discardStatus(DiscardStatus.DISCARD)
                .build();
    }



}
