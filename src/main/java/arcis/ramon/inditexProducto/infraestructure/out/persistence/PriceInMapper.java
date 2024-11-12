package arcis.ramon.inditexProducto.infraestructure.out.persistence;

import arcis.ramon.inditexProducto.application.port.in.PriceInDto;
import arcis.ramon.inditexProducto.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceInMapper extends GenericMapper<PriceInDto, Price>{
    @Override
    Price toEntity(PriceInDto priceInDto);

    @Override
    PriceInDto toDto(Price price);


}