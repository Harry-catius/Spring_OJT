package catius.ojt.device.repository;

import catius.ojt.device.DeviceObjectMother;
import catius.ojt.device.domain.Device;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class DeviceRepositoryTest {

    @Autowired
    private DeviceRepository deviceRepository;


    @Test
    public void updateDevice() {

        Device device = deviceRepository.save(DeviceObjectMother.defaultDevice());

        device.update(DeviceObjectMother.updateRequest());

        Device findDevice = deviceRepository.findById(1L).get();

        assertEquals("updatedSerialNumber",findDevice.getSerialNumber());
        assertEquals("updatedMacAddress",findDevice.getMacAddress());
        assertEquals("updatedQrCode",findDevice.getQrCode());

    }

    @Test
    public void changeDeviceStatus() {

    }



}