package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.exceptions.EntityNotFoundException;
import com.inditex.arcis.ramon.producto.domain.model.Price;
import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.PriceEntity;
import org.springframework.stereotype.Component;
import com.inditex.arcis.ramon.producto.application.port.out.GetPricePort;


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

        List<PriceEntity> pricesEntities = repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                priceInDto.getBrandId(), priceInDto.getDate(), priceInDto.getDate(), priceInDto.getProductId());

        Optional<PriceEntity> priceEntityOpt = pricesEntities.stream().findFirst();

        if (priceEntityOpt.isEmpty()) {
            DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
            String formattedDate = priceInDto.getDate().format(formatter);
            throw new EntityNotFoundException("La entidad no se encuentra en base de datos, productId:" +
                    priceInDto.getProductId() + " brandId:" + priceInDto.getBrandId() +
                    " date:" + formattedDate);
        }

        PriceEntity priceEntity = priceEntityOpt.get();

        String formattedPrice = new DecimalFormat("0.00#").format(priceEntity.getPrice())+" "+priceEntity.getCurrency();

        List<Price> pricesDomain = priceGetMapper.toDtoIterable(pricesEntities);

        PriceOutDto priceOutDto = priceOutMapper.toDto(pricesDomain.get(0));

        priceOutDto.setPrice(formattedPrice);

        return priceOutDto;
    }
}
