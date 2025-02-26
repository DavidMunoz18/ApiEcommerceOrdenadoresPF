package com.ecommerce.servicios;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.daos.ProductoDao;
import com.ecommerce.daos.ReseniaDao;
import com.ecommerce.daos.UsuarioDao;
import com.ecommerce.dtos.ReseniaDto;
import com.ecommerce.repositorios.ProductoRepository;
import com.ecommerce.repositorios.ReseniaRepository;
import com.ecommerce.repositorios.UsuarioRepository;
import com.ecommerce.utilidades.Utilidades;

/**
 * Servicio encargado de manejar las operaciones relacionadas con las reseñas de productos.
 * Incluye la creación y recuperación de reseñas tanto por producto como por usuario.
 */
@Service
public class ReseniaServicio {

    @Autowired
    private ReseniaRepository reseniaRepository;

    @Autowired
    private ProductoRepository productoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    /**
     * Agrega una nueva reseña para un producto por parte de un usuario.
     * 
     * @param idProducto el ID del producto al que se le agrega la reseña.
     * @param idUsuario el ID del usuario que escribe la reseña.
     * @param contenidoResenia el contenido de la reseña.
     * @param calificacion la calificación dada al producto por el usuario.
     * @return un objeto {@link ReseniaDto} con los detalles de la reseña agregada, o null si el producto o usuario no existen.
     */
    public ReseniaDto agregarResenia(Long idProducto, Long idUsuario, String contenidoResenia, Integer calificacion) {
        // Validar si el producto y el usuario existen
        ProductoDao producto = productoRepository.findById(idProducto).orElse(null);
        UsuarioDao usuario = usuarioRepository.findById(idUsuario).orElse(null);

        if (producto == null || usuario == null) {
            // Si el producto o el usuario no existen, registrar un log y retornar nulo
            Utilidades.escribirLog("[ERROR]", "ReseniaServicio", "agregarResenia", "Producto o usuario no encontrado. Producto ID: " + idProducto + ", Usuario ID: " + idUsuario);
            return null;
        }

        // Crear la nueva reseña
        ReseniaDao nuevaResenia = new ReseniaDao();
        nuevaResenia.setContenidoResenia(contenidoResenia);
        nuevaResenia.setCalificacion(calificacion);
        nuevaResenia.setProducto(producto);
        nuevaResenia.setUsuario(usuario);

        // Guardar la reseña en la base de datos
        ReseniaDao reseniaGuardada = reseniaRepository.save(nuevaResenia);

        // Registrar un log indicando que la reseña fue agregada correctamente
        Utilidades.escribirLog("[INFO]", "ReseniaServicio", "agregarResenia", "Reseña agregada correctamente para Producto ID: " + idProducto + " por Usuario ID: " + idUsuario);

        // Convertir la reseña guardada a un DTO
        return new ReseniaDto(reseniaGuardada.getIdResenia(), reseniaGuardada.getContenidoResenia(),
                reseniaGuardada.getCalificacion(), reseniaGuardada.getUsuario().getIdUsuario(),
                reseniaGuardada.getProducto().getIdProducto());
    }

    /**
     * Obtiene todas las reseñas de un producto específico.
     * 
     * @param idProducto el ID del producto del cual se obtendrán las reseñas.
     * @return una lista de objetos {@link ReseniaDto} que representan las reseñas del producto.
     */
    public List<ReseniaDto> obtenerReseniasPorProducto(Long idProducto) {
        // Obtener las reseñas por producto
        List<ReseniaDao> resenias = reseniaRepository.findByProducto_IdProducto(idProducto);

        // Registrar log sobre la cantidad de reseñas recuperadas
        Utilidades.escribirLog("[INFO]", "ReseniaServicio", "obtenerReseniasPorProducto", "Reseñas recuperadas para Producto ID: " + idProducto + ". Total de reseñas: " + resenias.size());

        return resenias.stream().map(resenia -> new ReseniaDto(resenia.getIdResenia(), resenia.getContenidoResenia(),
                resenia.getCalificacion(), resenia.getUsuario().getIdUsuario(),
                resenia.getProducto().getIdProducto()))
                .collect(Collectors.toList());
    }

    /**
     * Obtiene todas las reseñas de un usuario específico.
     * 
     * @param idUsuario el ID del usuario del cual se obtendrán las reseñas.
     * @return una lista de objetos {@link ReseniaDto} que representan las reseñas del usuario.
     */
    public List<ReseniaDto> obtenerReseniasPorUsuario(Long idUsuario) {
        // Obtener las reseñas por usuario
        List<ReseniaDao> resenias = reseniaRepository.findByUsuario_IdUsuario(idUsuario);

        // Registrar log sobre la cantidad de reseñas recuperadas
        Utilidades.escribirLog("[INFO]", "ReseniaServicio", "obtenerReseniasPorUsuario", "Reseñas recuperadas para Usuario ID: " + idUsuario + ". Total de reseñas: " + resenias.size());

        return resenias.stream().map(resenia -> new ReseniaDto(resenia.getIdResenia(), resenia.getContenidoResenia(),
                resenia.getCalificacion(), resenia.getUsuario().getIdUsuario(),
                resenia.getProducto().getIdProducto()))
                .collect(Collectors.toList());
    }
}
