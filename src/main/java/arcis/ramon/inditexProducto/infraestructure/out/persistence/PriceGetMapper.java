package arcis.ramon.inditexProducto.infraestructure.out.persistence;


import arcis.ramon.inditexProducto.domain.model.Price;
import arcis.ramon.inditexProducto.infraestructure.out.persistence.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceGetMapper extends GenericMapper<Price, PriceEntity>{


        // Convertir de PriceEntity a Price (DTO)
        @Override
        @Mapping(source = "brand.brandName", target = "brandName")  // Mapeo manual para el nombre de la marca
        @Mapping(source = "price", target = "price")  // Mapear directamente el BigDecimal a String
        Price toDto(PriceEntity priceEntity);

        // Convertir de Price (DTO) a PriceEntity
        @Override
        @Mapping(source = "brandName", target = "brand.brandName")  // Si la entidad Price tiene el nombre de la marca
        @Mapping(source = "price", target = "price")  // Mapear el String a BigDecimal
        PriceEntity toEntity(Price price);
        List<Price> toDtoIterable(List<PriceEntity> priceEntities);


}