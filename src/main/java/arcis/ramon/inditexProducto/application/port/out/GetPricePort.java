package arcis.ramon.inditexProducto.application.port.out;


import arcis.ramon.inditexProducto.application.port.in.PriceInDto;
import arcis.ramon.inditexProducto.application.port.in.PriceOutDto;

public interface GetPricePort {
    PriceOutDto getPrice(PriceInDto priceInDto);
}
