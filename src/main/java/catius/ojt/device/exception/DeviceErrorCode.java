package catius.ojt.device.exception;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum DeviceErrorCode {
    SERIALNUMBER_DUPLICATE_ERROR("동일한 시리얼 넘버가 존재합니다."),
    MACADDRESS_DUPLICATE_ERROR("동일한 맥 주소가 존재합니다."),
    QRCODE_DUPLICATE_ERROR("동일한 QR코드가 존재합니다."),
    INTERNAL_SERVER_ERROR("서버에 오류가 발생했습니다."),
    INVALID_REQUEST("잘못된 요청입니다"),
    NO_DEVICE("기기가 없습니다."),
    DEVICE_NOT_INACTIVATE("기기가 활성화 상태입니다. 비활성화 시켜주세요")
    ;
    private final String message;
}
