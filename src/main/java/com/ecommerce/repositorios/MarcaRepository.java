package com.ecommerce.repositorios;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce.daos.MarcaDao;
import java.util.Optional;

/**
 * Repositorio JPA para la entidad Marca.
 * 
 * Extiende JpaRepository para proveer operaciones CRUD y de paginación.
 * Contiene métodos para acceder y manipular datos de marcas en la base de datos.
 */
@Repository
public interface MarcaRepository extends JpaRepository<MarcaDao, Long> {
    
	/**
     * Busca una marca por su ID.
     * 
     * @param id Identificador único de la marca.
     * @return Un Optional que contiene la Marca si se encuentra, o vacío en caso contrario.
     */
    Optional<MarcaDao> findById(Long id);
}
