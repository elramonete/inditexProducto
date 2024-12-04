package com.inditex.arcis.ramon.producto.application.port.out;


import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.model.PriceRequest;

public interface GetPricePort {
    PriceOutDto getPrice(PriceRequest priceRequest);
}
