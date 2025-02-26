package com.ecommerce.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.ProductoDto;
import com.ecommerce.servicios.ProductoServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para manejar las operaciones CRUD sobre los productos.
 * Define endpoints para la gestión de productos en el sistema.
 */
@RestController
@RequestMapping("/api/productos")
public class ProductoControlador {

    @Autowired
    private ProductoServicio productoServicio;

    /**
     * Endpoint para obtener todos los productos.
     *
     * @return una lista de productos.
     */
    @GetMapping
    public ResponseEntity<List<ProductoDto>> obtenerTodosProductos() {
        Utilidades.escribirLog("INFO", "ProductoControlador", "obtenerTodosProductos", "Consultando todos los productos");
        List<ProductoDto> productos = productoServicio.obtenerTodosProductos();
        return ResponseEntity.ok(productos);
    }

    /**
     * Endpoint para obtener un producto por su ID.
     *
     * @param id el ID del producto a buscar.
     * @return un producto si se encuentra, o un error 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<ProductoDto> obtenerProductoPorId(@PathVariable Long id) {
        Utilidades.escribirLog("INFO", "ProductoControlador", "obtenerProductoPorId", "Buscando producto con ID: " + id);
        Optional<ProductoDto> producto = productoServicio.obtenerProductoPorId(id);

        return producto.map(ResponseEntity::ok)
                .orElseGet(() -> {
                    Utilidades.escribirLog("ERROR", "ProductoControlador", "obtenerProductoPorId", "Producto no encontrado con ID: " + id);
                    return ResponseEntity.notFound().build();
                });
    }

    /**
     * Endpoint para crear un nuevo producto.
     *
     * @param producto el objeto ProductoDto a crear.
     * @return el producto creado con código de respuesta 201.
     */
    @PostMapping
    public ResponseEntity<ProductoDto> crearProducto(@RequestBody ProductoDto producto) {
        Utilidades.escribirLog("INFO", "ProductoControlador", "crearProducto", "Creando nuevo producto: " + producto.getNombre());
        ProductoDto productoGuardado = productoServicio.guardarProducto(producto);
        return ResponseEntity.status(201).body(productoGuardado);
    }

    /**
     * Endpoint para obtener un producto por su nombre.
     *
     * @param nombre el nombre del producto a buscar.
     * @return el producto encontrado o un error 404 si no se encuentra.
     */
    @GetMapping("/nombre/{nombre}")
    public ResponseEntity<ProductoDto> obtenerProductoPorNombre(@PathVariable String nombre) {
        Utilidades.escribirLog("INFO", "ProductoControlador", "obtenerProductoPorNombre", "Buscando producto con nombre: " + nombre);
        ProductoDto producto = productoServicio.obtenerProductoPorNombre(nombre);
        if (producto != null) {
            return ResponseEntity.ok(producto);
        } else {
            Utilidades.escribirLog("ERROR", "ProductoControlador", "obtenerProductoPorNombre", "Producto no encontrado con nombre: " + nombre);
            return ResponseEntity.status(404).build();
        }
    }
}
