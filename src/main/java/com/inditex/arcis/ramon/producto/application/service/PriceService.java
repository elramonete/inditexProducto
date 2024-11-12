package com.inditex.arcis.ramon.producto.application.service;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import com.inditex.arcis.ramon.producto.application.port.out.GetPricePort;
import com.inditex.arcis.ramon.producto.application.port.in.PriceServicePort;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;

@AllArgsConstructor
@Service
public class PriceService implements PriceServicePort {

        private GetPricePort port;

        public PriceOutDto getPrice(PriceInDto priceInDto) {
            return port.getPrice(priceInDto);
        }

}
