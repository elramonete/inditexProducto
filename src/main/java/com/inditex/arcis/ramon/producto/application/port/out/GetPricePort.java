package com.inditex.arcis.ramon.producto.application.port.out;


import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;

public interface GetPricePort {
    PriceOutDto getPrice(PriceInDto priceInDto);
}
