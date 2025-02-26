package com.ecommerce.repositorios;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.ecommerce.daos.ReseniaDao;

/**
 * Interfaz que define el repositorio para gestionar las reseñas en la base de datos.
 * Extiende de {@link JpaRepository} para proporcionar operaciones CRUD básicas sobre la entidad ReseniaDao.
 * 
 * Esta interfaz permite realizar consultas específicas para obtener las reseñas asociadas a usuarios y productos.
 */
public interface ReseniaRepository extends JpaRepository<ReseniaDao, Long> {

    /**
     * Método para obtener todas las reseñas de un usuario específico.
     *
     * @param idUsuario el identificador del usuario cuya reseña se desea recuperar.
     * @return una lista de {@link ReseniaDao} asociadas al usuario con el id dado.
     */
    List<ReseniaDao> findByUsuario_IdUsuario(Long idUsuario);

    /**
     * Método para obtener todas las reseñas de un producto específico.
     *
     * @param idProducto el identificador del producto cuyas reseñas se desean recuperar.
     * @return una lista de {@link ReseniaDao} asociadas al producto con el id dado.
     */
    List<ReseniaDao> findByProducto_IdProducto(Long idProducto);

    /**
     * Método para obtener las reseñas de un usuario específico para un producto determinado.
     *
     * @param idUsuario el identificador del usuario cuya reseña se desea recuperar.
     * @param idProducto el identificador del producto para el cual se desea recuperar la reseña.
     * @return una lista de {@link ReseniaDao} asociadas al usuario y producto específicos.
     */
    List<ReseniaDao> findByUsuario_IdUsuarioAndProducto_IdProducto(Long idUsuario, Long idProducto);
}
