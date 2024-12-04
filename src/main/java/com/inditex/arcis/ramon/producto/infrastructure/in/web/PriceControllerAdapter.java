package com.inditex.arcis.ramon.producto.infrastructure.in.web;


import com.inditex.arcis.ramon.producto.domain.model.PriceRequest;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import com.inditex.arcis.ramon.producto.application.port.in.PriceServicePort;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;

import java.time.LocalDateTime;

@AllArgsConstructor
@RestController
public class PriceControllerAdapter implements PriceApi{
    private PriceServicePort port;
    @Override
    public ResponseEntity<PriceOutDto> getProductPrice(Long brandId, LocalDateTime date, Long productId) {
        // Convertir DTO a modelo de dominio
        PriceRequest priceRequest = PriceRequest.builder().brandId(brandId).date(date).productId(productId).build();

        return ResponseEntity.ok(port.getPrice(priceRequest));
    }
}
