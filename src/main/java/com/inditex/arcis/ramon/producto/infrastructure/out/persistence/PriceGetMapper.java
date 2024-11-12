package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;


import com.inditex.arcis.ramon.producto.domain.model.PriceDomain;
import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.PriceEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface PriceGetMapper extends GenericMapper<PriceDomain, PriceEntity>{


        // Convertir de PriceEntity a PriceDomain (DTO)
        @Override
        @Mapping(source = "brand.brandName", target = "brandName")  // Mapeo manual para el nombre de la marca
        @Mapping(source = "price", target = "price")  // Mapear directamente el BigDecimal a String
        PriceDomain toDto(PriceEntity priceEntity);

        // Convertir de PriceDomain (DTO) a PriceEntity
        @Override
        @Mapping(source = "brandName", target = "brand.brandName")  // Si la entidad PriceDomain tiene el nombre de la marca
        @Mapping(source = "price", target = "price")  // Mapear el String a BigDecimal
        PriceEntity toEntity(PriceDomain priceDomain);
        List<PriceDomain> toDtoIterable(List<PriceEntity> priceEntities);


}