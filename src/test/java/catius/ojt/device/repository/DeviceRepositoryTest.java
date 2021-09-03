package catius.ojt.device.repository;

import catius.ojt.device.DeviceObjectMother;
import catius.ojt.device.domain.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;

    @Test
    public void save() {
        Device savedDevice = deviceRepository.save(DeviceObjectMother.defaultDevice());

        assertEquals("serialNumber",savedDevice.getSerialNumber());
        assertEquals("macAddress",savedDevice.getMacAddress());
        assertEquals("qrCode",savedDevice.getQrCode());

    }

    @Test
    public void findById() {
        Device findDevice = deviceRepository
                .findById(deviceRepository.save(DeviceObjectMother.defaultDevice())
                        .getDeviceId()).get();

        assertEquals("serialNumber",findDevice.getSerialNumber());
        assertEquals("macAddress",findDevice.getMacAddress());
        assertEquals("qrCode",findDevice.getQrCode());
    }

    @Test
    public void findBySerialNumber() {
        Device findDevice = deviceRepository
                .findBySerialNumber(deviceRepository.save(DeviceObjectMother.defaultDevice())
                        .getSerialNumber()).get();

        assertEquals("serialNumber",findDevice.getSerialNumber());
        assertEquals("macAddress",findDevice.getMacAddress());
        assertEquals("qrCode",findDevice.getQrCode());
    }

    @Test
    public void findByMacAddress() {
        Device findDevice = deviceRepository
                .findByMacAddress(deviceRepository.save(DeviceObjectMother.defaultDevice())
                        .getMacAddress()).get();

        assertEquals("serialNumber",findDevice.getSerialNumber());
        assertEquals("macAddress",findDevice.getMacAddress());
        assertEquals("qrCode",findDevice.getQrCode());
    }

    @Test
    public void findByQrCode() {
        Device findDevice = deviceRepository
                .findByQrCode(deviceRepository.save(DeviceObjectMother.defaultDevice())
                        .getQrCode()).get();

        assertEquals("serialNumber",findDevice.getSerialNumber());
        assertEquals("macAddress",findDevice.getMacAddress());
        assertEquals("qrCode",findDevice.getQrCode());
    }

    @Test
    public void findBySerialNumberContaining() {

        deviceRepository.save(DeviceObjectMother.defaultDevice());

        List<Device> deviceList = deviceRepository
                .findBySerialNumberContaining("serialNumber");

        assertEquals(1,deviceList.size());
    }

    @Test
    public void findByMacAddressContaining() {

        deviceRepository.save(DeviceObjectMother.defaultDevice());

        List<Device> deviceList = deviceRepository
                .findByMacAddressContaining("macAddress");

        assertEquals(1,deviceList.size());

    }

    @Test
    public void findByQrCodeContaining() {

        deviceRepository.save(DeviceObjectMother.defaultDevice());

        List<Device> deviceList = deviceRepository
                .findByQrCodeContaining("qrCode");

        assertEquals(1,deviceList.size());

    }
}