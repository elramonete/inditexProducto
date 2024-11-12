package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.model.PriceDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceOutMapper extends GenericMapper<PriceOutDto, PriceDomain>{
    @Override
    PriceDomain toEntity(PriceOutDto priceOutDto);

    @Override
    PriceOutDto toDto(PriceDomain priceDomain);


}