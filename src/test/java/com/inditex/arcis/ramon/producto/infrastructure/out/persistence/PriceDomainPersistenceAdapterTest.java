package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

import com.inditex.arcis.ramon.producto.application.port.in.PriceInDto;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;
import com.inditex.arcis.ramon.producto.domain.exceptions.EntityNotFoundException;
import com.inditex.arcis.ramon.producto.domain.model.PriceDomain;
import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.BrandEntity;
import com.inditex.arcis.ramon.producto.infrastructure.out.persistence.entities.PriceEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
@ExtendWith(MockitoExtension.class)
class PriceDomainPersistenceAdapterTest {

    @Mock
    private PriceRepository repository;

    @Mock
    private PriceGetMapper priceGetMapper;
    @Mock
    private PriceOutMapper priceOutMapper;

    @InjectMocks
    private PricePersistenceAdapter priceRepositoryAdapter;

    private PriceEntity price1;
    private PriceEntity price2;

    @BeforeEach
    public void setUp() {
        LocalDateTime startDate1 = LocalDateTime.of(2023, 4, 1, 0, 0);
        LocalDateTime endDate1 = LocalDateTime.of(2023, 4, 30, 23, 59, 59);
        LocalDateTime startDate2 = LocalDateTime.of(2023, 5, 1, 0, 0);
        LocalDateTime endDate2 = LocalDateTime.of(2023, 5, 31, 23, 59, 59);

        price1 = PriceEntity.builder()
                .endDate(endDate1)
                .price(new BigDecimal(25))
                .startDate(startDate1)
                .brand(new BrandEntity(1L,"ZARA"))
                .productId(35455L)
                .currency("EUR")
                .id(1L)
                .priceList(2)
                .priority(0)
                .build();
        price2 = PriceEntity.builder()
                .endDate(endDate2)
                .price(new BigDecimal(35))
                .startDate(startDate2)
                .brand(new BrandEntity(1L,"ZARA"))
                .productId(35455L)
                .currency("EUR")
                .id(1L)
                .priceList(2)
                .priority(0)
                .build();
    }

    @Test
    void testGetPriceWithExistingPrice() {
        // Datos de entrada
        PriceInDto priceInDto = PriceInDto.builder()
                .brandId(1L)
                .date(LocalDateTime.of(2023, 4, 15, 0, 0))
                .productId(35455L)
                .build();

        // Lista de entidades esperadas
        List<PriceEntity> expectedPrices = new ArrayList<>();
        expectedPrices.add(price1); // Suponiendo que price1 y price2 son las entidades ya definidas en @BeforeEach
        expectedPrices.add(price2);

        // Configuración del mock para el repositorio
        when(repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                1L, LocalDateTime.of(2023, 4, 15, 0, 0), LocalDateTime.of(2023, 4, 15, 0, 0), 35455L))
                .thenReturn(expectedPrices);

        // Convertimos las entidades a objetos de dominio (PriceDomain)
        List<PriceDomain> pricesDomain = new ArrayList<>();
        pricesDomain.add(PriceDomain.builder()
                .productId(35455L)
                .price("25")
                .startDate(LocalDateTime.of(2023, 4, 1, 0, 0))
                .endDate(LocalDateTime.of(2023, 4, 30, 23, 59, 59))
                .build());
        pricesDomain.add(PriceDomain.builder()
                .productId(35455L)
                .price("35")
                .startDate(LocalDateTime.of(2023, 5, 1, 0, 0))
                .endDate(LocalDateTime.of(2023, 5, 31, 23, 59, 59))
                .build());

        // Configuración del mock para priceGetMapper
        when(priceGetMapper.toDtoIterable(expectedPrices)).thenReturn(pricesDomain);

        // Simulamos el comportamiento de priceOutMapper
        PriceOutDto expectedPriceOutDto = PriceOutDto.builder()
                .productId(35455L)
                .price("25")
                .startDate(LocalDateTime.of(2023, 4, 1, 0, 0))
                .endDate(LocalDateTime.of(2023, 4, 30, 23, 59, 59))
                .build();

        // Simulamos que el primer precio de la lista se mapea a expectedPriceOutDto
        when(priceOutMapper.toDto(pricesDomain.get(0))).thenReturn(expectedPriceOutDto);

        // Llamada al metodo que estamos probando
        PriceOutDto actualPriceOutDto = priceRepositoryAdapter.getPrice(priceInDto);

        // Comparación entre el resultado esperado y el resultado real
        assertEquals(expectedPriceOutDto, actualPriceOutDto);
    }

    @Test
    void testGetPriceWithNonExistingPrice() {
        // Datos de entrada
        PriceInDto priceInDto = PriceInDto.builder()
                .brandId(1L)
                .date(LocalDateTime.of(2023, 4, 15, 0, 0))
                .productId(35455L)
                .build();

        // Configuración del mock para el repositorio, haciendo que devuelva una lista vacía
        when(repository.findByBrandIdAndStartDateLessThanEqualAndEndDateGreaterThanEqualAndProductIdOrderByPriorityDesc(
                1L, LocalDateTime.of(2023, 4, 15, 0, 0), LocalDateTime.of(2023, 4, 15, 0, 0), 35455L))
                .thenReturn(new ArrayList<>());  // Lista vacía, simulando que no hay precios

        // Verificar que se lanza la excepción EntityNotFoundException
        EntityNotFoundException exception = assertThrows(EntityNotFoundException.class,
                () -> priceRepositoryAdapter.getPrice(priceInDto));

        // Verificar el mensaje de la excepción (opcional)
        String expectedMessage = "La entidad no se encuentra en base de datos, productId:35455 brandId:1 date:2023-04-15T00:00:00";
        String actualMessage = exception.getMessage();
        assertTrue(actualMessage.contains(expectedMessage));
    }

}