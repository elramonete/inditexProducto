package com.inditex.arcis.ramon.producto.infrastructure.in.web;


import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import com.inditex.arcis.ramon.producto.application.port.in.PriceOutDto;

import java.time.LocalDateTime;
@Tag(name = "prices", description = "Operaciones de consulta de precios")
public interface PriceApi {

    @Operation(summary = "Obtener el precio de un producto para una fecha determinada")
    @ApiResponse(responseCode = "200", description = "Precio del producto encontrado", content = @Content(schema = @Schema(implementation = PriceOutDto.class)))
    @ApiResponse(responseCode = "404", description = "Precio del producto no encontrado")
    @GetMapping("/price")
    ResponseEntity<PriceOutDto> getProductPrice(@Parameter( name = "brandId", description = "Identificador de la cadena de la marca", example = "1",required = true) Long brandId,
                                                @Parameter(name="date",description = "Fecha y hora de consulta del precio", example = "2020-06-14T10:00:00",required = true) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime date,
                                                @Parameter(name="productId",description = "Identificador del producto", example = "35455",required = true) Long productId);
}

