package catius.ojt.device.exception;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class DeviceErrorResponse {
    private DeviceErrorCode errorCode;
    private String errorMessage;
}
