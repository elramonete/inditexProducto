package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import com.inditex.arcis.ramon.producto.domain.model.PriceDomain;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceInMapper extends GenericMapper<PriceInDto, PriceDomain>{
    @Override
    PriceDomain toEntity(PriceInDto priceInDto);

    @Override
    PriceInDto toDto(PriceDomain priceDomain);


}