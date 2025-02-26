package com.ecommerce.controladores;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.ecommerce.daos.UsuarioDao;
import com.ecommerce.servicios.ProductoServicio;
import com.ecommerce.servicios.UsuarioServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para modificar y consultar información de usuarios y productos.
 * <p>
 * Proporciona endpoints para actualizar los datos de usuarios y productos,
 * así como obtener información de un usuario en específico.
 * </p>
 */
@RestController
@RequestMapping("/api/modificar")
public class ModificarControlador {

    @Autowired
    private UsuarioServicio usuarioServicio;

    @Autowired
    private ProductoServicio productoServicio;

    /**
     * Modifica los datos de un usuario identificado por su ID.
     *
     * @param idUsuario    ID del usuario a modificar.
     * @param nuevoNombre  Nuevo nombre del usuario (opcional).
     * @param nuevoDni     Nuevo DNI del usuario (opcional).
     * @param nuevoTelefono Nuevo teléfono del usuario (opcional).
     * @param nuevoRol     Nuevo rol del usuario (opcional).
     * @param nuevaFoto    Nueva foto del usuario (opcional, en formato MultipartFile).
     * @return {@link ResponseEntity} con un mensaje indicando el resultado de la operación.
     */
    @PutMapping(value = "/modificarUsuario/{idUsuario}", consumes = "multipart/form-data")
    public ResponseEntity<String> modificarUsuario(
            @PathVariable long idUsuario,
            @RequestParam(required = false) String nuevoNombre,
            @RequestParam(required = false) String nuevoDni,
            @RequestParam(required = false) String nuevoTelefono,
            @RequestParam(required = false) String nuevoRol,
            @RequestPart(required = false) MultipartFile nuevaFoto) {

        Utilidades.escribirLog("INFO", "ModificarControlador", "modificarUsuario", "Modificando usuario con ID: " + idUsuario);

        byte[] nuevaFotoBytes = null;
        if (nuevaFoto != null && !nuevaFoto.isEmpty()) {
            try {
                nuevaFotoBytes = nuevaFoto.getBytes();
                Utilidades.escribirLog("INFO", "ModificarControlador", "modificarUsuario", "Foto recibida: " + nuevaFoto.getOriginalFilename());
            } catch (Exception e) {
                Utilidades.escribirLog("ERROR", "ModificarControlador", "modificarUsuario", "Error al procesar la foto: " + e.getMessage());
                return ResponseEntity.status(500).body("Error al procesar la foto");
            }
        }

        // Se invoca el servicio sin realizar validaciones sobre los datos recibidos
        boolean modificado = usuarioServicio.modificarUsuario(idUsuario, nuevoNombre, nuevoDni, nuevoTelefono, nuevoRol, nuevaFotoBytes);

        if (modificado) {
            Utilidades.escribirLog("INFO", "ModificarControlador", "modificarUsuario", "Usuario actualizado con éxito con ID: " + idUsuario);
            return ResponseEntity.ok("Usuario actualizado con éxito");
        } else {
            Utilidades.escribirLog("INFO", "ModificarControlador", "modificarUsuario", "Usuario no encontrado con ID: " + idUsuario);
            return ResponseEntity.status(404).body("Usuario no encontrado");
        }
    }


    /**
     * Obtiene los datos de un usuario mediante su ID.
     *
     * @param idUsuario ID del usuario a buscar.
     * @return {@link ResponseEntity} con los datos del usuario o un estado 404 si no se encuentra.
     */
    @GetMapping("/buscarUsuario/{idUsuario}")
    public ResponseEntity<UsuarioDao> obtenerUsuario(@PathVariable long idUsuario) {
        String mensaje = "Consultando usuario con ID: " + idUsuario;
        Utilidades.escribirLog("INFO", "ModificarControlador", "obtenerUsuario", mensaje);

        UsuarioDao usuario = usuarioServicio.obtenerUsuarioPorId(idUsuario);

        if (usuario != null) {
            mensaje = "Usuario encontrado con ID: " + idUsuario;
            Utilidades.escribirLog("INFO", "ModificarControlador", "obtenerUsuario", mensaje);
            return ResponseEntity.ok(usuario);
        } else {
            mensaje = "Usuario no encontrado con ID: " + idUsuario;
            Utilidades.escribirLog("INFO", "ModificarControlador", "obtenerUsuario", mensaje);
            return ResponseEntity.status(404).body(null);
        }
    }

    /**
     * Modifica los datos de un producto identificado por su ID.
     *
     * @param idProducto      ID del producto a modificar.
     * @param nuevoNombre     Nuevo nombre del producto (opcional).
     * @param nuevaDescripcion Nueva descripción del producto (opcional).
     * @param nuevoPrecio     Nuevo precio del producto (opcional).
     * @param nuevoStock      Nuevo stock del producto (opcional).
     * @param nuevaCategoria  Nueva categoría del producto (opcional).
     * @param nuevaImagen     Nueva imagen del producto (opcional, en formato MultipartFile).
     * @return {@link ResponseEntity} con un mensaje indicando el resultado de la operación.
     */
    @PutMapping(value = "/modificarProducto/{idProducto}", consumes = "multipart/form-data")
    public ResponseEntity<String> modificarProducto(
            @PathVariable long idProducto,
            @RequestParam(required = false) String nuevoNombre,
            @RequestParam(required = false) String nuevaDescripcion,
            @RequestParam(required = false) Double nuevoPrecio,
            @RequestParam(required = false) Integer nuevoStock,  
            @RequestParam(required = false) String nuevaCategoria,
            @RequestPart(required = false) MultipartFile nuevaImagen) {

        String mensaje = "Modificando producto con ID: " + idProducto;
        Utilidades.escribirLog("INFO", "ModificarControlador", "modificarProducto", mensaje);

        byte[] nuevaImagenBytes = null;
        try {
            if (nuevaImagen != null && !nuevaImagen.isEmpty()) {
                nuevaImagenBytes = nuevaImagen.getBytes();
                mensaje = "Imagen recibida: " + nuevaImagen.getOriginalFilename();
                Utilidades.escribirLog("INFO", "ModificarControlador", "modificarProducto", mensaje);
            } else {
                mensaje = "No se recibió ninguna imagen. No se actualizará la imagen.";
                Utilidades.escribirLog("INFO", "ModificarControlador", "modificarProducto", mensaje);
            }
        } catch (Exception e) {
            mensaje = "Error al procesar la imagen: " + e.getMessage();
            Utilidades.escribirLog("ERROR", "ModificarControlador", "modificarProducto", mensaje);
            return ResponseEntity.status(500).body("Error al procesar la imagen");
        }

        boolean modificado = productoServicio.modificarProducto(idProducto, nuevoNombre, nuevaDescripcion, nuevoPrecio, nuevoStock, nuevaImagenBytes, nuevaCategoria);

        if (modificado) {
            mensaje = "Producto actualizado con éxito con ID: " + idProducto;
            Utilidades.escribirLog("INFO", "ModificarControlador", "modificarProducto", mensaje);
            return ResponseEntity.ok(nuevaImagenBytes == null ? 
                    "Producto actualizado con éxito, pero la imagen no fue modificada." : 
                    "Producto actualizado con éxito");
        } else {
            mensaje = "Producto no encontrado con ID: " + idProducto;
            Utilidades.escribirLog("INFO", "ModificarControlador", "modificarProducto", mensaje);
            return ResponseEntity.status(404).body("Producto no encontrado");
        }
    }
}
