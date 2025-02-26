package com.ecommerce.daos;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;

/**
 * Representa una reseña realizada por un usuario sobre un producto en la base de datos.
 * La reseña contiene un contenido de texto y una calificación asociada.
 * 
 * Relación:
 * - Muchos a uno con la entidad {@link UsuarioDao} (Un usuario puede escribir varias reseñas).
 * - Muchos a uno con la entidad {@link ProductoDao} (Un producto puede tener varias reseñas).
 */
@Entity
@Table(name = "resenias", schema = "gestion")
public class ReseniaDao {

    /**
     * Identificador único de la reseña.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_resenia")
    private Long idResenia;

    /**
     * Contenido textual de la reseña proporcionado por el usuario.
     * No debe superar los 255 caracteres.
     */
    @Column(name = "contenido_resenia", length = 255, nullable = false)
    private String contenidoResenia;

    /**
     * Calificación otorgada al producto por el usuario. 
     * Debe ser un valor numérico no nulo.
     */
    @Column(name = "calificacion", nullable = false)
    private Integer calificacion;

    /**
     * Usuario que ha escrito la reseña. 
     * Relación muchos a uno con la entidad {@link UsuarioDao}.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading para optimizar rendimiento
    @JoinColumn(name = "id_usuario", nullable = false)
    private UsuarioDao usuario;

    /**
     * Producto sobre el cual se realiza la reseña. 
     * Relación muchos a uno con la entidad {@link ProductoDao}.
     */
    @ManyToOne(fetch = FetchType.LAZY) // Lazy loading para optimizar rendimiento
    @JoinColumn(name = "id_producto", nullable = false)
    private ProductoDao producto;

    // Getters y Setters

    /**
     * Obtiene el identificador único de la reseña.
     * 
     * @return El identificador único de la reseña.
     */
    public Long getIdResenia() {
        return idResenia;
    }

    /**
     * Establece el identificador único de la reseña.
     * 
     * @param idResenia El identificador único de la reseña.
     */
    public void setIdResenia(Long idResenia) {
        this.idResenia = idResenia;
    }

    /**
     * Obtiene el contenido textual de la reseña.
     * 
     * @return El contenido textual de la reseña.
     */
    public String getContenidoResenia() {
        return contenidoResenia;
    }

    /**
     * Establece el contenido textual de la reseña.
     * 
     * @param contenidoResenia El contenido textual de la reseña.
     */
    public void setContenidoResenia(String contenidoResenia) {
        this.contenidoResenia = contenidoResenia;
    }

    /**
     * Obtiene la calificación otorgada por el usuario en la reseña.
     * 
     * @return La calificación otorgada en la reseña.
     */
    public Integer getCalificacion() {
        return calificacion;
    }

    /**
     * Establece la calificación otorgada por el usuario en la reseña.
     * 
     * @param calificacion La calificación otorgada en la reseña.
     */
    public void setCalificacion(Integer calificacion) {
        this.calificacion = calificacion;
    }

    /**
     * Obtiene el usuario que ha escrito la reseña.
     * 
     * @return El usuario que ha escrito la reseña.
     */
    public UsuarioDao getUsuario() {
        return usuario;
    }

    /**
     * Establece el usuario que ha escrito la reseña.
     * 
     * @param usuario El usuario que ha escrito la reseña.
     */
    public void setUsuario(UsuarioDao usuario) {
        this.usuario = usuario;
    }

    /**
     * Obtiene el producto sobre el cual se ha realizado la reseña.
     * 
     * @return El producto sobre el cual se ha realizado la reseña.
     */
    public ProductoDao getProducto() {
        return producto;
    }

    /**
     * Establece el producto sobre el cual se ha realizado la reseña.
     * 
     * @param producto El producto sobre el cual se ha realizado la reseña.
     */
    public void setProducto(ProductoDao producto) {
        this.producto = producto;
    }

    /**
     * Constructor por defecto.
     */
    public ReseniaDao() {
        super();
    }

    /**
     * Constructor con parámetros para inicializar una nueva reseña.
     * 
     * @param idResenia El identificador único de la reseña.
     * @param contenidoResenia El contenido de la reseña.
     * @param calificacion La calificación otorgada.
     * @param usuario El usuario que escribió la reseña.
     * @param producto El producto sobre el cual se escribió la reseña.
     */
    public ReseniaDao(Long idResenia, String contenidoResenia, Integer calificacion, UsuarioDao usuario,
                      ProductoDao producto) {
        super();
        this.idResenia = idResenia;
        this.contenidoResenia = contenidoResenia;
        this.calificacion = calificacion;
        this.usuario = usuario;
        this.producto = producto;
    }

    /**
     * Método que devuelve una representación en forma de cadena de la reseña.
     * 
     * @return Una cadena que representa la reseña con su id, contenido, calificación, usuario y producto.
     */
    @Override
    public String toString() {
        return "ReseniaDao [idResenia=" + idResenia + ", contenidoResenia=" + contenidoResenia + ", calificacion="
                + calificacion + ", usuario=" + usuario + ", producto=" + producto + "]";
    }
}
