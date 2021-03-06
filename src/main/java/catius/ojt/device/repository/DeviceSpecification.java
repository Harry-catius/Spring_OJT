package catius.ojt.device.repository;

import catius.ojt.device.domain.Device;
import org.jetbrains.annotations.NotNull;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

public class DeviceSpecification {

    public static Specification<Device> likeSerialNumber(String serialNumber) {
        return (new Specification<>() {
            @Override
            public Predicate toPredicate(
                    @NotNull Root<Device> root,
                    @NotNull CriteriaQuery<?> query,
                    @NotNull CriteriaBuilder criteriaBuilder) {
                // serialNumber like = ?
                return criteriaBuilder.like(root.get("serialNumber"), "%" + serialNumber + "%");
            }
        });
    }

    public static Specification<Device> likeMacAddress(String macAddress) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(
                    @NotNull Root<Device> root,
                    @NotNull CriteriaQuery<?> query,
                    @NotNull CriteriaBuilder criteriaBuilder)
            {
                // macAddress like = ?
                return criteriaBuilder.like(root.get("macAddress"), "%" + macAddress + "%");
            }
        };
    }

    public static Specification<Device> likeQrCode(String qrCode) {
        return new Specification<Device>() {
            @Override
            public Predicate toPredicate(
                    @NotNull Root<Device> root,
                    @NotNull CriteriaQuery<?> query,
                    @NotNull CriteriaBuilder criteriaBuilder)
            {
                // qrCode like = ?
                return criteriaBuilder.like(root.get("qrCode"), "%" + qrCode + "%");
            }
        };
    }
}
