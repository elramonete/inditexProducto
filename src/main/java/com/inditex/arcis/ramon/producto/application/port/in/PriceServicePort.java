package com.inditex.arcis.ramon.producto.application.port.in;

public interface PriceServicePort {
    PriceOutDto getPrice(PriceInDto priceInDto);
}
