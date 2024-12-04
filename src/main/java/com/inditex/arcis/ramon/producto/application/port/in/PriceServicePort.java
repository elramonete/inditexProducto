package com.inditex.arcis.ramon.producto.application.port.in;

import com.inditex.arcis.ramon.producto.domain.model.PriceRequest;

public interface PriceServicePort {
    PriceOutDto getPrice(PriceRequest priceRequest);
}
