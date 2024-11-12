package com.inditex.arcis.ramon.producto.application.port.in;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PriceInDto {

        private Long brandId;
        private LocalDateTime date;
        private Long productId;
}
