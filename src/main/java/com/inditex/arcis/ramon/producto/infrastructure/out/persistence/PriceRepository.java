package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.PriceEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface PriceRepository extends JpaRepository<PriceEntity, Long> {
    List<PriceEntity> findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
            Long brandId, LocalDateTime startDate, LocalDateTime endDate, Long productId);
}