package arcis.ramon.inditexProducto.infraestructure.out.persistence;

public interface GenericMapper<D, E> {
    D toDto(E entity);
    E toEntity(D dto);
}