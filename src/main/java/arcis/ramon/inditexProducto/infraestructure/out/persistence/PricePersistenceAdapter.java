package arcis.ramon.inditexProducto.infraestructure.out.persistence;

import arcis.ramon.inditexProducto.application.port.in.PriceInDto;
import arcis.ramon.inditexProducto.application.port.in.PriceOutDto;
import arcis.ramon.inditexProducto.domain.exceptions.EntityNotFoundException;
import arcis.ramon.inditexProducto.domain.model.Price;
import arcis.ramon.inditexProducto.infraestructure.out.persistence.entities.PriceEntity;
import org.springframework.stereotype.Component;
import arcis.ramon.inditexProducto.application.port.out.GetPricePort;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@Component
public class PricePersistenceAdapter implements GetPricePort {

    private final PriceRepository repository;
    private final PriceGetMapper priceGetMapper;
    private final PriceOutMapper priceOutMapper;

    public PricePersistenceAdapter(PriceRepository repository, PriceGetMapper priceGetMapper, PriceOutMapper priceOutMapper) {
        this.repository = repository;
        this.priceGetMapper = priceGetMapper;
        this.priceOutMapper = priceOutMapper;
    }

    public PriceOutDto getPrice(PriceInDto priceInDto) {
        // Intentamos obtener la lista de precios desde la base de datos
        List<PriceEntity> pricesEntities = repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                priceInDto.getBrandId(), priceInDto.getDate(), priceInDto.getDate(), priceInDto.getProductId());

        // Verificamos si la lista no está vacía antes de acceder a ella
        Optional<PriceEntity> priceEntityOpt = pricesEntities.stream().findFirst();

        // Si no hay precios, lanzamos una excepción
        if (!priceEntityOpt.isPresent()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String formattedDate = priceInDto.getDate().format(formatter);
            throw new EntityNotFoundException("La entidad no se encuentra en base de datos, productId:" +
                    priceInDto.getProductId() + " brandId:" + priceInDto.getBrandId() +
                    " date:" + formattedDate);
        }

        // Obtenemos el primer precio de la lista (ya que usamos findFirst, está garantizado que existirá)
        PriceEntity priceEntity = priceEntityOpt.get();

        // Formateamos el precio con DecimalFormat
        String formattedPrice = new DecimalFormat("0.00#").format(priceEntity.getPrice())+" "+priceEntity.getCurrency();

        // Convertimos la entidad a un objeto de dominio (Price)
        List<Price> pricesDomain = priceGetMapper.toDtoIterable(pricesEntities);

        // Creamos el DTO de salida y lo devolvemos
        PriceOutDto priceOutDto = priceOutMapper.toDto(pricesDomain.get(0));

        // Aseguramos de que el precio formateado se agregue al DTO
        priceOutDto.setPrice(formattedPrice);  // Establecemos el precio formateado en el DTO

        return priceOutDto;
    }
}
