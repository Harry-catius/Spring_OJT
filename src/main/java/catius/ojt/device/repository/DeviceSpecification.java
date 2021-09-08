package catius.ojt.device.repository;

import catius.ojt.device.domain.Device;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DeviceSpecification {

    public static Specification<Device> likeSerialNumber(String serialNumber) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // serialNumber like = ?
                return criteriaBuilder.like(root.get("serialNumber"), "%" + serialNumber + "%");
            }
        };
    }

    public static Specification<Device> likeMacAddress(String macAddress) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // macAddress like = ?
                return criteriaBuilder.like(root.get("macAddress"), "%" + macAddress + "%");
            }
        };
    }

    public static Specification<Device> likeQrCode(String qrCode) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(Root<Device> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                // qrCode like = ?
                return criteriaBuilder.like(root.get("qrCode"), "%" + qrCode + "%");
            }
        };
    }
}
