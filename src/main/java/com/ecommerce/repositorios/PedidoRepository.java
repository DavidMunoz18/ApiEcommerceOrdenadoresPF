package com.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.daos.PedidoDao;

/**
 * Interfaz que define el repositorio para gestionar los datos de los pedidos en la base de datos.
 * Extiende de JpaRepository para proporcionar operaciones CRUD básicas sobre la entidad PedidoDao.
 * 
 * El repositorio está marcado con la anotación {@link Repository}, lo que indica que es un componente 
 * de Spring encargado de interactuar con la base de datos para persistir y recuperar datos relacionados con los pedidos.
 */
@Repository
public interface PedidoRepository extends JpaRepository<PedidoDao, Long> {
    // No es necesario definir ningún método adicional si solo se necesitan las operaciones CRUD básicas
}
