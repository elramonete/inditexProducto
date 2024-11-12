package arcis.ramon.inditexProducto.application.port.in;

import java.time.LocalDateTime;
public interface PriceServicePort {
    PriceOutDto getPrice(PriceInDto priceInDto);
}
