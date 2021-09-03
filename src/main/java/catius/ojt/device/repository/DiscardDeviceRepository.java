package catius.ojt.device.repository;

import catius.ojt.device.domain.DiscardedDevice;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DiscardDeviceRepository extends JpaRepository<DiscardedDevice, Long> {

    List<DiscardedDevice> findAll();

}
