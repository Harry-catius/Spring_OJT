package catius.ojt.device.service;

import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DiscardDevice;
import catius.ojt.device.exception.DeviceException;
import catius.ojt.device.repository.DeviceRepository;
import catius.ojt.device.repository.DeviceSpecification;
import catius.ojt.device.repository.DiscardDeviceRepository;
import catius.ojt.device.service.dto.*;
import lombok.RequiredArgsConstructor;
import org.springframework.data.jpa.domain.Specification;
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

    // 등록
    @Transactional
    @Override
    public DeviceDto register(DeviceDto deviceDto) {
        validateDeviceAttribute(
                deviceDto.getSerialNumber(),
                deviceDto.getMacAddress(),
                deviceDto.getQrCode());

        return DeviceFactory.getDeviceDto(deviceRepository.save(DeviceFactory.getDevice(deviceDto)));
    }

    // 조회
    @Override
    public DeviceDto findOne(Long deviceId) {
        return DeviceFactory.getDeviceDto(deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE)));
    }

    @Override
    public List<DeviceDto> findDevices(String serialNumber, String macAddress, String qrCode) {

        List<Device> deviceList;
        if(serialNumber != null) deviceList = deviceRepository.findAll(DeviceSpecification.likeSerialNumber(serialNumber));
        else if(macAddress != null) deviceList = deviceRepository.findAll(DeviceSpecification.likeMacAddress(macAddress));
        else if(qrCode != null) deviceList = deviceRepository.findAll(DeviceSpecification.likeQrCode(qrCode));
        else deviceList = deviceRepository.findAll();

        if(deviceList.isEmpty()) throw new DeviceException(NO_DEVICE);

        return deviceList.stream()
                .map(DeviceFactory::getDeviceDto)
                .collect(Collectors.toList());
    }
    // 폐기된 기기 전체 조회
    @Override
    public List<DiscardDeviceDto> findAllDiscardedDevice() {
        List<DiscardDevice> deviceList = discardDeviceRepository.findAll();

        return deviceList.stream()
                .map(DeviceFactory::getDiscardDeviceDto)
                .collect(Collectors.toList());
    }

    //수정
    @Transactional
    @Override
    public DeviceDto updateDevice(Long deviceId, DeviceDto deviceDto) {

        validateDeviceAttribute(
                deviceDto.getSerialNumber(),
                deviceDto.getMacAddress(),
                deviceDto.getQrCode());

        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        device.update(DeviceFactory.getDevice(deviceDto));

        deviceRepository.save(device);

        return DeviceFactory.getDeviceDto(device);

    }

    // 삭제
    // device테이블에서 삭제하되 deviceId와 discardStatus는 남겨둔채로 삭제
    // deviceId와 discardStatus는 별도의 테이블 구축
    @Transactional
    @Override
    public DiscardDeviceDto deleteDevice(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        // 기기가 ACTIVE 상태이면 exception 날리기
        if(device.isActive()) throw new DeviceException(DEVICE_NOT_INACTIVATE);
        device.discardDevice();

        // device 도메인을 discardedDevice로 변환하여 discardedDeviceRepository에 저장
        // id 값과, discardStatus를 컬럼으로 가짐
        DiscardDevice discardDevice = DeviceFactory.getDiscardDevice(device);

        discardDeviceRepository.save(discardDevice);

        deviceRepository.deleteById(deviceId);

        return DeviceFactory.getDiscardDeviceDto(discardDevice);
    }

    // 상태변경
    @Transactional
    @Override
    public DeviceDto changeDeviceStatus(Long deviceId) {
        Device device = deviceRepository.findById(deviceId)
                .orElseThrow(() -> new DeviceException(NO_DEVICE));

        device.changeDeviceStatus();

        deviceRepository.save(device);

        return DeviceFactory.getDeviceDto(device);
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
