package com.ecommerce.daos;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

/**
 * Clase que representa la entidad Marca en la base de datos.
 * Esta clase está vinculada a la tabla "marcas" y mapea las columnas correspondientes.
 */
@Entity
@Table(name = "marcas", schema = "gestion")
public class MarcaDao {

    /**
     * Identificador único de la marca.
     * Este campo es la clave primaria y se genera automáticamente.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_marca", updatable = false)
    private long idMarca;

    /**
     * Nombre de la marca.
     * Este campo es obligatorio y tiene una longitud máxima de 100 caracteres.
     */
    @Column(name = "nombre_marca", length = 100, nullable = false, unique = true)
    private String nombreMarca;

    /**
     * País de origen de la marca.
     */
    @Column(name = "pais_origen", length = 100, nullable = false)
    private String paisOrigen;

    /**
     * Año de fundación de la marca.
     */
    @Column(name = "anio_fundacion", nullable = false)
    private int anioFundacion;

    /**
     * Descripción de la marca.
     */
    @Column(name = "descripcion", length = 500)
    private String descripcion;

    /**
     * Relación uno a muchos con la entidad ProductoDao.
     * Una marca puede estar asociada a múltiples productos.
     */
    @OneToMany(mappedBy = "marca", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<ProductoDao> productos;

    // ===========================
    // Getters y Setters
    // ===========================

    public long getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(long idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    public String getPaisOrigen() {
        return paisOrigen;
    }

    public void setPaisOrigen(String paisOrigen) {
        this.paisOrigen = paisOrigen;
    }

    public int getAnioFundacion() {
        return anioFundacion;
    }

    public void setAnioFundacion(int anioFundacion) {
        this.anioFundacion = anioFundacion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public List<ProductoDao> getProductos() {
        return productos;
    }

    public void setProductos(List<ProductoDao> productos) {
        this.productos = productos;
    }

    // ===========================
    // Constructores
    // ===========================

    public MarcaDao() {
        super();
    }

    public MarcaDao(String nombreMarca, String paisOrigen, int anioFundacion, String descripcion) {
        this.nombreMarca = nombreMarca;
        this.paisOrigen = paisOrigen;
        this.anioFundacion = anioFundacion;
        this.descripcion = descripcion;
    }

    @Override
    public String toString() {
        return "Marca{" +
               "idMarca=" + idMarca +
               ", nombreMarca='" + nombreMarca + '\'' +
               ", paisOrigen='" + paisOrigen + '\'' +
               ", anioFundacion=" + anioFundacion +
               ", descripcion='" + descripcion + '\'' +
               '}';
    }
}
