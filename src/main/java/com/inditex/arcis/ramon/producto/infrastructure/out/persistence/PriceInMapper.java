package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import com.inditex.arcis.ramon.producto.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceInMapper extends GenericMapper<PriceInDto, Price>{
    @Override
    Price toEntity(PriceInDto priceInDto);

    @Override
    PriceInDto toDto(Price price);


}