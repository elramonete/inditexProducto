package com.inditex.arcis.ramon.producto.domain.model;

import lombok.*;

import java.time.LocalDateTime;

@Getter
@Setter
@NoArgsConstructor
@Builder
@AllArgsConstructor
public class PriceRequest {
    private Long brandId;
    private LocalDateTime date;
    private Long productId;
}

