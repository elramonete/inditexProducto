package arcis.ramon.inditexProducto.infraestructure.out.persistence;

import arcis.ramon.inditexProducto.application.port.in.PriceOutDto;
import arcis.ramon.inditexProducto.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceOutMapper extends GenericMapper<PriceOutDto, Price>{
    @Override
    Price toEntity(PriceOutDto priceOutDto);

    @Override
    PriceOutDto toDto(Price price);


}