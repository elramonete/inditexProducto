package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.exceptions.EntityNotFoundException;
import com.inditex.arcis.ramon.producto.domain.model.PriceDomain;
import com.inditex.arcis.ramon.producto.domain.model.PriceRequest;
import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.PriceEntity;
import org.springframework.stereotype.Component;
import com.inditex.arcis.ramon.producto.application.port.out.GetPricePort;


import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;
import java.util.List;

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

    public PriceOutDto getPrice(PriceRequest priceRequest) {

        List<PriceEntity> priceEntities  = repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                priceRequest.getBrandId(), priceRequest.getDate(), priceRequest.getDate(), priceRequest.getProductId());

        PriceEntity priceEntity = priceEntities.stream()
                .findFirst()
                .orElseThrow(() -> {
                    DateTimeFormatter formatter = DateTimeFormatter.ISO_DATE_TIME;
                    String formattedDate = priceRequest.getDate().format(formatter);
                    return new EntityNotFoundException("La entidad no se encuentra en base de datos, productId:" +
                            priceRequest.getProductId() + " brandId:" + priceRequest.getBrandId() +
                            " date:" + formattedDate);
                });



        String formattedPrice = new DecimalFormat("0.00#").format(priceEntity.getPrice())+" "+priceEntity.getCurrency();

        List<PriceDomain> pricesDomain = priceGetMapper.toDtoIterable(priceEntities);

        PriceOutDto priceOutDto = priceOutMapper.toDto(pricesDomain.get(0));

        priceOutDto.setPrice(formattedPrice);

        return priceOutDto;
    }
}
