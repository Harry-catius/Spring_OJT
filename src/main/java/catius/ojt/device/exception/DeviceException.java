package catius.ojt.device.exception;

import lombok.Getter;

@Getter
public class DeviceException extends  RuntimeException{
    private DeviceErrorCode deviceErrorCode;
    private String errorMessage;

    public DeviceException(DeviceErrorCode errorCode) {
        super(errorCode.getMessage());
        this.deviceErrorCode = errorCode;
        this.errorMessage = errorCode.getMessage();
    }

    public DeviceException(DeviceErrorCode errorCode, String errorMessage) {
        super(errorMessage);
        this.deviceErrorCode = errorCode;
        this.errorMessage = errorMessage;
    }
}
