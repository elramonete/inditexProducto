package arcis.ramon.inditexProducto.application.service;

import arcis.ramon.inditexProducto.application.port.in.PriceInDto;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import arcis.ramon.inditexProducto.application.port.out.GetPricePort;
import arcis.ramon.inditexProducto.application.port.in.PriceServicePort;
import arcis.ramon.inditexProducto.application.port.in.PriceOutDto;

@AllArgsConstructor
@Service
public class PriceService implements PriceServicePort {

        private GetPricePort port;

        public PriceOutDto getPrice(PriceInDto priceInDto) {
            return port.getPrice(priceInDto);
        }

}
