package com.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.daos.ProductoDao;

/**
 * Interfaz que define el repositorio para gestionar los datos de los productos en la base de datos.
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD básicas sobre la entidad ProductoDao.
 * 
 * Esta interfaz está marcada con la anotación {@link Repository}, lo que la convierte en un componente de Spring 
 * encargado de interactuar con la base de datos para persistir y recuperar datos relacionados con los productos.
 */
@Repository
public interface ProductoRepository extends JpaRepository<ProductoDao, Long> {

    /**
     * Busca un producto por su nombre.
     *
     * @param nombreProducto el nombre del producto.
     * @return el producto encontrado con ese nombre. Si no se encuentra, retorna null.
     */
    ProductoDao findByNombreProducto(String nombreProducto);
}
