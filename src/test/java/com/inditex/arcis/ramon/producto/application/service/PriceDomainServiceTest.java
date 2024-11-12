package com.inditex.arcis.ramon.producto.application.service;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import org.mockito.junit.jupiter.MockitoExtension;
import com.inditex.arcis.ramon.producto.application.port.out.GetPricePort;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class PriceDomainServiceTest {
    @Mock
    private GetPricePort port;

    @InjectMocks
    private PriceService priceService;

    private PriceOutDto price;

    @BeforeEach
    public void setUp() {
        LocalDateTime startDate = LocalDateTime.of(2023, 4, 1, 0, 0);
        LocalDateTime endDate = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
        price = PriceOutDto.builder()
                .endDate(endDate)
                .price("25")
                .startDate(startDate)
                .brandName("ZARA")
                .productId(35455L)
                .priceList(2)
                .build();
    }

    @Test
     void testPriceService() {
        PriceInDto priceInDto = PriceInDto.builder().brandId(1L).date(LocalDateTime.of(2023, 4, 15, 0, 0)).productId(1L).build();
        when(port.getPrice(priceInDto)).thenReturn(price);

        PriceOutDto expectedPriceOutDto = PriceOutDto.builder()
                .brandName(price.getBrandName())
                .productId(price.getProductId())
                .startDate(price.getStartDate())
                .endDate(price.getEndDate())
                .price( "25")
                .priceList(price.getPriceList())
                .build();

        PriceOutDto actualPriceOutDto = priceService.getPrice(priceInDto);

        assertEquals(expectedPriceOutDto, actualPriceOutDto);
    }

}