package catius.ojt.device.exception;


import lombok.extern.slf4j.Slf4j;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

// 각 컨트롤러에 영향을 주는 어노테이션
@Slf4j
@RestControllerAdvice
public class DeviceExceptionHandler {

    //예외 전역 처리
    @ExceptionHandler
    public DeviceErrorResponse handleException(DeviceException e, HttpServletRequest request) {
        log.error("errorCode : {} , url : {} , message : {}"
                , e.getDeviceErrorCode()
                , request.getRequestURI()
                , e.getErrorMessage());

        return DeviceErrorResponse.builder()
                .errorCode(e.getDeviceErrorCode())
                .errorMessage(e.getErrorMessage())
                .build();
    }

    @ExceptionHandler(value = {
            HttpRequestMethodNotSupportedException.class,
            MethodArgumentNotValidException.class
    })
    public DeviceErrorResponse handleBadRequest(
            Exception e, HttpServletRequest request
    ) {
        log.error("url : {} , message : {}"
                , request.getRequestURI()
                , e.getMessage());

        return DeviceErrorResponse.builder()
                .errorCode(DeviceErrorCode.INVALID_REQUEST)
                .errorMessage(DeviceErrorCode.INVALID_REQUEST.getMessage())
                .build();
    }


    @ExceptionHandler(Exception.class)
    public DeviceErrorResponse handleException(
            Exception e, HttpServletRequest request
    ) {
        log.error("url : {} , message : {}"
                , request.getRequestURI()
                , e.getMessage());

        return DeviceErrorResponse.builder()
                .errorCode(DeviceErrorCode.INTERNAL_SERVER_ERROR)
                .errorMessage(DeviceErrorCode.INTERNAL_SERVER_ERROR.getMessage())
                .build();
    }
}
