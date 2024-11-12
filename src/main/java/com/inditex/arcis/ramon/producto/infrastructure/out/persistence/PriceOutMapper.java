package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.model.Price;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PriceOutMapper extends GenericMapper<PriceOutDto, Price>{
    @Override
    Price toEntity(PriceOutDto priceOutDto);

    @Override
    PriceOutDto toDto(Price price);


}