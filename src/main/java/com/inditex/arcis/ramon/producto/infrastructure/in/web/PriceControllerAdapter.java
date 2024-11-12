package com.inditex.arcis.ramon.producto.infrastructure.in.web;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
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
        PriceInDto priceInDto = PriceInDto.builder().brandId(brandId).date(date).productId(productId).build();
        return ResponseEntity.ok(port.getPrice(priceInDto));
    }
}
