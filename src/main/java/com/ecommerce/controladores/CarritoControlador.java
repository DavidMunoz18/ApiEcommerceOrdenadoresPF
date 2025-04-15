package com.ecommerce.controladores;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ecommerce.dtos.CarritoDto;
import com.ecommerce.servicios.CarritoServicio;
import com.ecommerce.utilidades.Utilidades;

import jakarta.servlet.http.HttpSession;

/**
 * Controlador que expone los endpoints para la gestión del carrito.
 * Permite agregar, obtener, eliminar productos y limpiar el carrito.
 */
@RestController
@RequestMapping("/api/carrito")
public class CarritoControlador {

    @Autowired
    private CarritoServicio carritoServicio;

    /**
     * Agrega un producto al carrito.
     *
     * @param producto Objeto CarritoDto que representa el producto a agregar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @PostMapping("/agregar")
    public ResponseEntity<String> agregarProducto(@RequestBody CarritoDto producto) {
        Utilidades.escribirLog("[INFO]", "CarritoControlador", "agregarProducto", "Iniciando ejecución");

        try {
            System.out.println("Producto recibido: " + producto);
            boolean resultado = carritoServicio.agregarProducto(producto);
            if (resultado) {
                return ResponseEntity.ok("Producto añadido al carrito correctamente.");
            } else {
                return ResponseEntity.badRequest().body("No se pudo añadir el producto al carrito.");
            }
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoControlador", "agregarProducto", "Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el carrito de productos desde el servicio.
     *
     * @return ResponseEntity que contiene la lista de productos del carrito.
     */
    @GetMapping
    public ResponseEntity<List<CarritoDto>> obtenerCarrito() {
        Utilidades.escribirLog("[INFO]", "CarritoControlador", "obtenerCarrito (servicio)", "Iniciando ejecución");

        try {
            List<CarritoDto> carrito = carritoServicio.obtenerCarrito();
            return ResponseEntity.ok(carrito);
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoControlador", "obtenerCarrito (servicio)", "Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Obtiene el carrito de productos almacenado en la sesión.
     *
     * @param session Sesión HTTP del usuario.
     * @return ResponseEntity que contiene la lista de productos del carrito.
     */
    @GetMapping("/carrito")
    public ResponseEntity<List<CarritoDto>> obtenerCarrito(HttpSession session) {
        Utilidades.escribirLog("[INFO]", "CarritoControlador", "obtenerCarrito (session)", "Iniciando ejecución");

        try {
            @SuppressWarnings("unchecked")
            List<CarritoDto> carrito = (List<CarritoDto>) session.getAttribute("carrito");
            if (carrito == null) {
                carrito = new ArrayList<>();
            }
            return ResponseEntity.ok(carrito);
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoControlador", "obtenerCarrito (session)", "Error: " + e.getMessage());
            throw e;
        }
    }

    /**
     * Elimina un producto del carrito identificado por su ID.
     *
     * @param id Identificador del producto a eliminar.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<String> eliminarProducto(@PathVariable int id) {
        Utilidades.escribirLog("[INFO]", "CarritoControlador", "eliminarProducto", "Iniciando ejecución con id: " + id);

        try {
            boolean resultado = carritoServicio.eliminarProducto(id);
            if (resultado) {
                return ResponseEntity.ok("Producto eliminado del carrito.");
            } else {
                return ResponseEntity.badRequest().body("No se pudo eliminar el producto del carrito.");
            }
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoControlador", "eliminarProducto", "Error: " + e.getMessage());
            throw e;
        }
    }
    /**
     * Limpia (elimina TODOS los productos) del carrito.
     *
     * @param session Sesión HTTP del usuario.
     * @return ResponseEntity con un mensaje indicando el resultado de la operación.
     */
    @DeleteMapping("/limpiar")
    public ResponseEntity<String> limpiarCarrito(HttpSession session) {
        Utilidades.escribirLog("[INFO]", "CarritoControlador", "limpiarCarrito", "Iniciando ejecución");
        try {
            boolean resultado = carritoServicio.limpiarCarrito();
            if (resultado) {
                // También actualizamos el carrito en la sesión para que quede vacío
                session.setAttribute("carrito", new ArrayList<CarritoDto>());
                return ResponseEntity.ok("Carrito limpiado correctamente.");
            } else {
                return ResponseEntity.badRequest().body("No se pudo limpiar el carrito.");
            }
        } catch(Exception e) {
            Utilidades.escribirLog("[ERROR]", "CarritoControlador", "limpiarCarrito", "Error: " + e.getMessage());
            throw e;
        }
    }
}
