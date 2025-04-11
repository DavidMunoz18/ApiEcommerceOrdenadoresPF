package com.ecommerce.controladores;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.ecommerce.dtos.MarcaDto;
import com.ecommerce.servicios.MarcaServicio;
import com.ecommerce.utilidades.Utilidades;

/**
 * Controlador para manejar las operaciones CRUD sobre las marcas.
 * Define endpoints para la gestión de marcas en el sistema.
 */
@RestController
@RequestMapping("/api/marcas")
public class MarcaControlador {

    @Autowired
    private MarcaServicio marcaServicio;

    /**
     * Endpoint para obtener todas las marcas.
     *
     * @return una lista de marcas.
     */
    @GetMapping
    public ResponseEntity<List<MarcaDto>> obtenerTodasMarcas() {
        Utilidades.escribirLog("INFO", "MarcaControlador", "obtenerTodasMarcas", "Consultando todas las marcas");
        List<MarcaDto> marcas = marcaServicio.obtenerTodasMarcas();
        return ResponseEntity.ok(marcas);
    }

    /**
     * Endpoint para obtener una marca por su ID.
     *
     * @param id el ID de la marca a buscar.
     * @return la marca encontrada o un error 404 si no se encuentra.
     */
    @GetMapping("/{id}")
    public ResponseEntity<MarcaDto> obtenerMarcaPorId(@PathVariable Long id) {
        Utilidades.escribirLog("INFO", "MarcaControlador", "obtenerMarcaPorId", "Buscando marca con ID: " + id);
        Optional<MarcaDto> marca = marcaServicio.obtenerMarcaPorId(id);
        return marca.map(ResponseEntity::ok)
                    .orElseGet(() -> {
                        Utilidades.escribirLog("ERROR", "MarcaControlador", "obtenerMarcaPorId", "Marca no encontrada con ID: " + id);
                        return ResponseEntity.notFound().build();
                    });
    }

    /**
     * Endpoint para crear una nueva marca.
     *
     * @param marca el objeto MarcaDto a crear.
     * @return la marca creada con código de respuesta 201.
     */
    @PostMapping
    public ResponseEntity<MarcaDto> crearMarca(@RequestBody MarcaDto marca) {
        Utilidades.escribirLog("INFO", "MarcaControlador", "crearMarca", "Creando nueva marca: " + marca.getNombre());
        MarcaDto marcaGuardada = marcaServicio.guardarMarca(marca);
        return ResponseEntity.status(201).body(marcaGuardada);
    }

    /**
     * Endpoint para actualizar una marca existente.
     *
     * @param id el ID de la marca a actualizar.
     * @param marca el objeto MarcaDto con los datos a actualizar.
     * @return la marca actualizada o un error 404 si no se encuentra.
     */
    @PutMapping("/{id}")
    public ResponseEntity<MarcaDto> actualizarMarca(@PathVariable Long id, @RequestBody MarcaDto marca) {
        Utilidades.escribirLog("INFO", "MarcaControlador", "actualizarMarca", "Actualizando marca con ID: " + id);
        Optional<MarcaDto> marcaExistente = marcaServicio.obtenerMarcaPorId(id);
        
        if (marcaExistente.isPresent()) {
            // Actualizamos solo si la marca existe
            marca.setId(id);  // Asegurarse de que el ID no cambie
            MarcaDto marcaActualizada = marcaServicio.actualizarMarca(id, marca);
            return ResponseEntity.ok(marcaActualizada);
        } else {
            Utilidades.escribirLog("ERROR", "MarcaControlador", "actualizarMarca", "Marca no encontrada con ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }

    /**
     * Endpoint para eliminar una marca por su ID.
     *
     * @param id el ID de la marca a eliminar.
     * @return respuesta indicando si la eliminación fue exitosa.
     */
    @DeleteMapping("eliminar/{id}")
    public ResponseEntity<Void> eliminarMarca(@PathVariable Long id) {
        Utilidades.escribirLog("INFO", "MarcaControlador", "eliminarMarca", "Eliminando marca con ID: " + id);
        Optional<MarcaDto> marcaExistente = marcaServicio.obtenerMarcaPorId(id);
        
        if (marcaExistente.isPresent()) {
            marcaServicio.eliminarMarca(id);
            return ResponseEntity.noContent().build();  // Respuesta 204 No Content para eliminación exitosa
        } else {
            Utilidades.escribirLog("ERROR", "MarcaControlador", "eliminarMarca", "Marca no encontrada con ID: " + id);
            return ResponseEntity.notFound().build();
        }
    }
}
