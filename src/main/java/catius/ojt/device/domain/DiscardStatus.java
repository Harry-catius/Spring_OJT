package catius.ojt.device.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DiscardStatus {
    DISCARD("폐기"),
    MAINTAIN("지속");

    private final String description;
}
