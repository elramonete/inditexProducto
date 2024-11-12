package arcis.ramon.inditexProducto.infraestructure.in.web;

import arcis.ramon.inditexProducto.application.port.in.PriceInDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import arcis.ramon.inditexProducto.application.port.in.PriceServicePort;
import arcis.ramon.inditexProducto.application.port.in.PriceOutDto;

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
