package catius.ojt.device.controller;

import catius.ojt.device.controller.dto.request.DeviceRequest;
import catius.ojt.device.controller.dto.response.DeviceResponse;
import catius.ojt.device.controller.dto.response.DiscardDeviceResponse;
import catius.ojt.device.exception.DeviceException;
import catius.ojt.device.service.DeviceService;
import catius.ojt.device.service.dto.*;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
public class DeviceController {
    private final DeviceService deviceService;

    @ApiOperation("registerUser")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
    })
    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping(value = "/devices/register",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeviceResponse registerDevice(@RequestBody @Valid DeviceRequest deviceRequest) {
        DeviceDto findDevice = deviceService.register(DeviceRequest.toDto(deviceRequest));

        return DeviceResponse.fromDto(findDevice);
    }



    @ApiOperation("findAllDeviceOrFindByElement")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
            , @ApiResponse(code=404, message = "Not Found", response = DeviceException.class, reference = "404.json")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/devices",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DeviceResponse> findAllDevice(
            @RequestParam(name = "serialnumber", required = false ) String serialNumber,
            @RequestParam(name = "macaddress",required = false) String macAddress,
            @RequestParam(name = "qrcode",required = false) String qrCode) {

        List<DeviceDto> deviceList = deviceService.findDevices(serialNumber, macAddress, qrCode);

        return deviceList.stream()
                .map(DeviceResponse::fromDto)
                .collect(Collectors.toList());
    }



    @ApiOperation("findById")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
            , @ApiResponse(code=404, message = "Not Found", response = DeviceException.class, reference = "404.json")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/devices/{deviceId}",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public DeviceResponse findOne(@PathVariable("deviceId") Long deviceId) {
        DeviceDto device = deviceService.findOne(deviceId);

        return DeviceResponse.fromDto(device);
    }

    // 폐기된 기기 전체 조회

    @ApiOperation("findAllDiscardDevice")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
            , @ApiResponse(code=404, message = "Not Found", response = DeviceException.class, reference = "404.json")
    })
    @ResponseStatus(HttpStatus.OK)
    @GetMapping(value = "/devices/discard",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public List<DiscardDeviceResponse> findAllDiscardedDevice() {
        List<DiscardDeviceDto> discardedDevices = deviceService.findAllDiscardedDevice();

        return discardedDevices.stream()
                .map(DiscardDeviceResponse::fromDto)
                .collect(Collectors.toList());
    }


    @ApiOperation("updateDevice")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/devices/{deviceId}",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceResponse updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody @Valid DeviceRequest deviceRequest) {
        DeviceDto device = deviceService.updateDevice(deviceId, DeviceRequest.toDto(deviceRequest));

        return DeviceResponse.fromDto(device);
    }



    @ApiOperation("updateDeviceStatus")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
    })
    @ResponseStatus(HttpStatus.OK)
    @PutMapping(value = "/devices/{deviceId}/status",
            consumes= MediaType.APPLICATION_JSON_VALUE,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public DeviceResponse changeDeviceStatus(@PathVariable("deviceId") Long deviceId) {
        DeviceDto device = deviceService.changeDeviceStatus(deviceId);

        return DeviceResponse.fromDto(device);
    }


    @ApiOperation("deleteDevice")
    @ApiResponses({
            @ApiResponse(code=400, message = "bad request", response = DeviceException.class, reference = "400.json")
    })
    @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping(value = "/devices/{deviceId}",
            consumes= MediaType.APPLICATION_JSON_VALUE)
    public void deleteDevice(@PathVariable("deviceId") Long deviceId) {
        deviceService.deleteDevice(deviceId);
    }
}