package catius.ojt.device.service;

import catius.ojt.device.DeviceObjectMother;
import catius.ojt.device.domain.Device;
import catius.ojt.device.domain.DeviceStatus;
import catius.ojt.device.exception.DeviceException;
import catius.ojt.device.repository.DeviceRepository;
import catius.ojt.device.repository.DiscardDeviceRepository;
import catius.ojt.device.service.dto.DeviceDto;
import catius.ojt.device.service.dto.DiscardDeviceDto;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static catius.ojt.device.exception.DeviceErrorCode.*;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class DeviceServiceTest {

    @Mock
    private DeviceRepository deviceRepository;

    @Mock
    private DiscardDeviceRepository discardDeviceRepository;

    @InjectMocks
    private DeviceServiceImpl deviceService;


    @Test
    public void shouldRegisterDevice() {
        ArgumentCaptor<Device> captor = ArgumentCaptor.forClass(Device.class);

        deviceService.register(DeviceObjectMother.registerRequest());

        verify(deviceRepository, times(1))
                .save(captor.capture());

        Device savedDevice = captor.getValue();

        assertEquals("serialNumber",savedDevice.getSerialNumber());
        assertEquals("macAddress",savedDevice.getMacAddress());
        assertEquals("qrCode",savedDevice.getQrCode());
    }

    @Test
    public void shouldFailIfSerialNumberEqual() {
        given(deviceRepository.findBySerialNumber(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceException deviceException = assertThrows(DeviceException.class,
                () -> deviceService.register(DeviceObjectMother.registerRequest()));

        assertEquals(SERIALNUMBER_DUPLICATE_ERROR, deviceException.getDeviceErrorCode());
    }

    @Test
    public void shouldFailIfMacAddressEqual() {
        given(deviceRepository.findByMacAddress(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceException deviceException = assertThrows(DeviceException.class,
                () -> deviceService.register(DeviceObjectMother.registerRequest()));

        assertEquals(MACADDRESS_DUPLICATE_ERROR, deviceException.getDeviceErrorCode());
    }

    @Test
    public void shouldFailIfQrCodeEqual() {
        given(deviceRepository.findByQrCode(anyString()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceException deviceException = assertThrows(DeviceException.class,
                () -> deviceService.register(DeviceObjectMother.registerRequest()));

        assertEquals(QRCODE_DUPLICATE_ERROR, deviceException.getDeviceErrorCode());
    }

    @Test
    public void shouldFindOne() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        Device device = Device.toEntity(deviceService.findOne(1L));

        assertEquals("serialNumber",device.getSerialNumber());
        assertEquals("macAddress",device.getMacAddress());
        assertEquals("qrCode",device.getQrCode());
    }

    @Test
    public void shouldFindAllDevice() {
        given(deviceRepository.findAll())
                .willReturn(DeviceObjectMother.defaultDeviceList());

        List<DeviceDto> deviceList = deviceService.findDevices(null,null,null);

        assertEquals(2,deviceList.size());
    }

    @Test
    public void shouldFindAllDiscardedDevice() {
        given(discardDeviceRepository.findAll())
                .willReturn(DeviceObjectMother.defaultDiscardDeviceList());

        List<DiscardDeviceDto> discardedDeviceList = deviceService.findAllDiscardedDevice();

        assertEquals(2,discardedDeviceList.size());
    }


    @Test
    public void shouldUpdateDevice() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceDto updatedDevice = deviceService.updateDevice(1L, DeviceObjectMother.updateRequestDto());

        assertEquals("updatedSerialNumber",updatedDevice.getSerialNumber());
        assertEquals("updatedMacAddress",updatedDevice.getMacAddress());
        assertEquals("updatedQrCode",updatedDevice.getQrCode());
    }

    @Test
    public void shouldChangeDeviceStatus() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceDto updateDeviceDto = deviceService.changeDeviceStatus(1L);

        assertEquals(DeviceStatus.INACTIVE, updateDeviceDto.getStatus());
    }

    @Test
    public void shouldDeleteDevice() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.deleteRequest()));

        deviceService.deleteDevice(1L);

        List<DeviceDto> deviceList = deviceService.findDevices(null, null, null);

        assertEquals(0, deviceList.size());
    }

    //????????? ????????? ???????????? ????????? exception ??????
    @Test
    public void shouldFailIfActive() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        DeviceException deviceException = assertThrows(DeviceException.class,
                () -> deviceService.deleteDevice(1L));

        assertEquals(DEVICE_NOT_INACTIVATE, deviceException.getDeviceErrorCode());
    }

    //  ???????????? ?????????

    @Test
    @Transactional
    public void rollbackTransactionTest() {
        given(deviceRepository.findById(anyLong()))
                .willReturn(Optional.of(DeviceObjectMother.defaultDevice()));

        


    }

    // ?????? ???????????? ???????????? ????????? ??????????????? ??????
    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void callNewTransaction() {

    }


}