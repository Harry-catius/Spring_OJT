package catius.ojt.device.service;

import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.domain.DiscardedDevice;
import catius.ojt.device.exception.DeviceException;
import catius.ojt.device.repository.DeviceRepository;
import catius.ojt.device.repository.DiscardDeviceRepository;
import catius.ojt.device.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import static catius.ojt.device.exception.DeviceErrorCode.*;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class DeviceServiceImpl implements DeviceService{
    private final DeviceRepository deviceRepository;
    private final DiscardDeviceRepository discardDeviceRepository;
    // 인터페이스로 구현 , 빌더 구현, 여기도 reque
    // 등록
    @Transactional
    @Override
    public RegisterDeviceDto register(RegisterDeviceDto registerDeviceDto) {
        validateDeviceAttribute(
                registerDeviceDto.getSerialNumber(),
                registerDeviceDto.getMacAddress(),
                registerDeviceDto.getQrCode());

        Device device = Device.toEntity(registerDeviceDto);

        deviceRepository.save(device);

        return RegisterDeviceDto.fromEntity(device);
    }

    // 조회
    @Override
    public SelectDeviceDto findOne(Long deviceId) {
        return deviceRepository.findById(deviceId)
                .map(SelectDeviceDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

    }

    @Override
    public List<SelectDeviceDto> findDevices(String serialNumber, String macAddress, String qrCode) {
        List<Device> deviceList;
        if(serialNumber != null) deviceList = deviceRepository.findBySerialNumberContaining(serialNumber);
        else if(macAddress != null) deviceList = deviceRepository.findByMacAddressContaining(macAddress);
        else if(qrCode != null) deviceList = deviceRepository.findByQrCodeContaining(qrCode);
        else deviceList = deviceRepository.findAll();

        return deviceList.stream()
                .map(SelectDeviceDto::fromEntity)
                .collect(Collectors.toList());
    }
    // 폐기된 기기 전체 조회
    @Override
    public List<SelectDiscardedDeviceDto> findAllDiscardedDevice() {
        List<DiscardedDevice> deviceList = discardDeviceRepository.findAll();

        return deviceList.stream()
                .map(SelectDiscardedDeviceDto::fromEntity)
                .collect(Collectors.toList());
    }

    //수정
    @Transactional
    @Override
    public UpdateDeviceDto updateDevice(Long deviceId, UpdateDeviceDto updateDeviceDto) {

        validateDeviceAttribute(
                updateDeviceDto.getSerialNumber(),
                updateDeviceDto.getMacAddress(),
                updateDeviceDto.getQrCode());

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        device.update(UpdateDeviceDto.toEntity(updateDeviceDto));

        deviceRepository.save(device);


        return UpdateDeviceDto.fromEntity(device);

    }

    // 삭제
    // device테이블에서 삭제하되 deviceId와 discardStatus는 남겨둔채로 삭제
    // deviceId와 discardStatus는 별도의 테이블 구축
    @Transactional
    @Override
    public RegisterDiscardedDeviceDto deleteDevice(Long deviceId) {
        SelectDeviceDto selectDeviceDto = deviceRepository.findById(deviceId)
                .map(SelectDeviceDto::fromEntity)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        Device device = Device.toEntity(selectDeviceDto);

        // 기기가 ACTIVE 상태이면 exception 날리기
        if(device.isActive()) throw new DeviceException(DEVICE_NOT_INACTIVATE);
        device.discardDevice();

        // device 도메인을 discardedDevice로 변환하여 discardedDeviceRepository에 저장
        // id 값과, discardStatus를 컬럼으로 가짐
        DiscardedDevice discardedDevice = DiscardedDevice.toEntity(device);

        discardDeviceRepository.save(discardedDevice);

        deviceRepository.deleteById(deviceId);

        return RegisterDiscardedDeviceDto.fromEntity(discardedDevice);
    }

    // 상태변경
    @Transactional
    @Override
    public UpdateDeviceDto changeDeviceStatus(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        device.changeDeviceStatus();

        deviceRepository.save(device);

        return UpdateDeviceDto.fromEntity(device);

    }


    @Transactional
    @Override
    public void validateDeviceAttribute(String serialNumber, String macAddress, String qrCode) {
        deviceRepository.findBySerialNumber(serialNumber)
                .ifPresent((Device -> {
                    throw new DeviceException(SERIALNUMBER_DUPLICATE_ERROR);
                }));
        deviceRepository.findByMacAddress(macAddress)
                .ifPresent((Device -> {
                    throw new DeviceException(MACADDRESS_DUPLICATE_ERROR);
                }));
        deviceRepository.findByQrCode(qrCode)
                .ifPresent((Device -> {
                    throw new DeviceException(QRCODE_DUPLICATE_ERROR);
                }));
    }
}
