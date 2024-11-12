package com.inditex.arcis.ramon.producto.infrastructure.out.persistence;

public interface GenericMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}