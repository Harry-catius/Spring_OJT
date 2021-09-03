package catius.ojt.device.controller;

import catius.ojt.device.controller.dto.request.DeviceRequestDto;
import catius.ojt.device.controller.dto.response.DeviceResponseDto;
import catius.ojt.device.controller.dto.response.DiscardedDeviceResponseDto;
import catius.ojt.device.service.DeviceService;
import catius.ojt.device.service.dto.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.stream.Collectors;


@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class DeviceController {
    private final DeviceService deviceService;

    @ResponseStatus(HttpStatus.CREATED)
    @PostMapping("/devices/register")
    public DeviceResponseDto registerDevice(@RequestBody @Valid DeviceRequestDto deviceRequestDto) {
        RegisterDeviceDto registerDeviceDto = RegisterDeviceDto.toDto(deviceRequestDto);

        RegisterDeviceDto findDevice = deviceService.register(registerDeviceDto);
        return DeviceResponseDto.fromDto(findDevice);
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/devices")
    public List<DeviceResponseDto> findAllDevice(
            @RequestParam(name = "serialnumber", required = false ) String serialNumber,
            @RequestParam(name = "macaddress",required = false) String macAddress,
            @RequestParam(name = "qrcode",required = false) String qrCode) {

        List<SelectDeviceDto> deviceList = deviceService.findDevices(serialNumber, macAddress, qrCode);

        return deviceList.stream()
                .map(DeviceResponseDto::fromDto)
                .collect(Collectors.toList());
    }

    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/devices/{deviceId}")
    public DeviceResponseDto findOne(@PathVariable("deviceId") Long deviceId) {
        SelectDeviceDto device = deviceService.findOne(deviceId);

        return DeviceResponseDto.fromDto(device);
    }

    // 폐기된 기기 전체 조회
    @ResponseStatus(HttpStatus.OK)
    @GetMapping("/devices/discard")
    public List<DiscardedDeviceResponseDto> findAllDiscardedDevice() {
        List<SelectDiscardedDeviceDto> discardedDevices = deviceService.findAllDiscardedDevice();
        List<DiscardedDeviceResponseDto> discardedDeviceList = discardedDevices.stream()
                .map(DiscardedDeviceResponseDto::fromDto)
                .collect(Collectors.toList());

        return discardedDeviceList;
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/devices/{deviceId}")
    public DeviceResponseDto updateDevice(@PathVariable("deviceId") Long deviceId, @RequestBody @Valid DeviceRequestDto deviceRequestDto) {
        UpdateDeviceDto updateDeviceDto = UpdateDeviceDto.toEntity(deviceRequestDto);

        UpdateDeviceDto device = deviceService.updateDevice(deviceId, updateDeviceDto);

        return DeviceResponseDto.fromDto(device);
    }

    @ResponseStatus(HttpStatus.OK)
    @PutMapping("/devices/{deviceId}/status")
    public DeviceResponseDto changeDeviceStatus(@PathVariable("deviceId") Long deviceId) {
        UpdateDeviceDto device = deviceService.changeDeviceStatus(deviceId);

        return DeviceResponseDto.fromDto(device);
    }

    // @ResponseStatus(HttpStatus.OK)
     @ResponseStatus(HttpStatus.NO_CONTENT)
    @DeleteMapping("/devices/{deviceId}")
    public void deleteDevice(@PathVariable("deviceId") Long deviceId) {
        RegisterDiscardedDeviceDto device = deviceService.deleteDevice(deviceId);

       // return DiscardedDeviceResponseDto.fromDto(device);
    }
}
