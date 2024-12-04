package com.inditex.arcis.ramon.producto.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter // Lombok genera los getters automáticamente
@Setter // Lombok genera los setters automáticamente
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceOutDto {
    private Long productId;
    private String brandName;
    private String price;
    private Integer priceList;
    private LocalDateTime startDate;
    private LocalDateTime endDate;
}