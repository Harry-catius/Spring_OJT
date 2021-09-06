package catius.ojt.device.repository;

import catius.ojt.device.domain.DiscardDevice;
import catius.ojt.device.service.dto.DiscardDeviceDto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscardDeviceRepository extends JpaRepository<DiscardDevice, Long> {

    List<DiscardDevice> findAll();

}
