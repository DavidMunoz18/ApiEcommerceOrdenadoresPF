package com.ecommerce.servicios;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce.daos.MarcaDao;
import com.ecommerce.daos.ProductoDao;
import com.ecommerce.dtos.MarcaDto;
import com.ecommerce.dtos.ProductoDto;
import com.ecommerce.repositorios.MarcaRepository;
import com.ecommerce.repositorios.ProductoRepository;
import com.ecommerce.utilidades.Utilidades;

@Service
public class ProductoServicio {

    @Autowired
    private ProductoRepository productoRepository;
    @Autowired
    private MarcaRepository marcaRepository; 

    /**
     * Obtiene todos los productos y los convierte a ProductoDto.
     */
    public List<ProductoDto> obtenerTodosProductos() {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "obtenerTodosProductos", "Iniciando ejecución de obtenerTodosProductos");
        List<ProductoDao> productos = productoRepository.findAll();
        // Se registra cada producto encontrado, sin realizar comprobaciones
        for (ProductoDao producto : productos) {
            Utilidades.escribirLog("[INFO]", "ProductoServicio", "obtenerTodosProductos", "Producto: " + producto.getNombreProducto());
        }
        return productos.stream()
                .map(this::convertirADto)
                .collect(Collectors.toList());
    }

    /**
     * Actualiza un producto existente con los nuevos valores proporcionados.
     * (No se realizan validaciones; se asume que Dynamic Web envía datos correctos.)
     */
    public ProductoDto actualizarProducto(Long id, ProductoDto productoDto) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "actualizarProducto", "Iniciando actualización de producto con ID: " + id);
        // Se obtiene el producto sin comprobar si es nulo
        ProductoDao productoDao = productoRepository.findById(id).orElse(null);
        ProductoDto producto = convertirADto(productoDao);
        // Actualización directa de campos
        producto.setNombre(productoDto.getNombre());
        producto.setDescripcion(productoDto.getDescripcion());
        producto.setPrecio(productoDto.getPrecio());
        producto.setStock(productoDto.getStock());
        producto.setCategoria(productoDto.getCategoria());
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "actualizarProducto", "Producto actualizado con ID: " + id);
        return guardarProducto(producto);
    }

    /**
     * Busca un producto por su ID y lo convierte a ProductoDto.
     */
    public Optional<ProductoDto> obtenerProductoPorId(Long id) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "obtenerProductoPorId", "Buscando producto con ID: " + id);
        Optional<ProductoDao> productoDao = productoRepository.findById(id);
        return productoDao.map(this::convertirADto);
    }

    /**
     * Busca un producto por su nombre y lo convierte a ProductoDto.
     */
    public ProductoDto obtenerProductoPorNombre(String nombreProducto) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "obtenerProductoPorNombre", "Buscando producto con nombre: " + nombreProducto);
        ProductoDao productoDao = productoRepository.findByNombreProducto(nombreProducto);
        return convertirADto(productoDao);
    }

    /**
     * Guarda un nuevo producto en la base de datos.
     */
    public ProductoDto guardarProducto(ProductoDto producto) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "guardarProducto", "Guardando nuevo producto: " + producto.getNombre());
        ProductoDao productoDao = convertirADao(producto);
        ProductoDao productoGuardado = productoRepository.save(productoDao);
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "guardarProducto", "Producto guardado con ID: " + productoGuardado.getIdProducto());
        return convertirADto(productoGuardado);
    }

    /**
     * Elimina un producto por su ID.
     */
    public void eliminarProducto(Long id) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "eliminarProducto", "Eliminando producto con ID: " + id);
        productoRepository.deleteById(id);
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "eliminarProducto", "Producto eliminado con ID: " + id);
    }

    /**
     * Modifica un producto existente en la base de datos con los valores proporcionados.
     * (No se realizan validaciones; se asume que Dynamic Web envía datos correctos.)
     */
    public boolean modificarProducto(long idProducto, String nuevoNombre, String nuevaDescripcion, Double nuevoPrecio,
                                     Integer nuevoStock, byte[] nuevaImagen, String nuevaCategoria) {
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "modificarProducto", "Iniciando modificación de producto con ID: " + idProducto);
        ProductoDao productoExistente = productoRepository.findById(idProducto).orElse(null);
        // Se actualizan los campos directamente sin comprobar nulos ni vacíos
        productoExistente.setNombreProducto(nuevoNombre);
        productoExistente.setDescripcionProducto(nuevaDescripcion);
        productoExistente.setPrecioProducto(nuevoPrecio);
        productoExistente.setStockProducto(nuevoStock);
        productoExistente.setFotoProducto(nuevaImagen);
        productoExistente.setCategoriaProducto(nuevaCategoria);
        productoRepository.save(productoExistente);
        Utilidades.escribirLog("[INFO]", "ProductoServicio", "modificarProducto", "Producto modificado exitosamente con ID: " + idProducto);
        return true;
    }

    private ProductoDto convertirADto(ProductoDao productoDao) {
        if (productoDao == null) {
            return null;
        }
        return new ProductoDto(
                productoDao.getIdProducto(),
                productoDao.getNombreProducto(),
                productoDao.getDescripcionProducto(),
                productoDao.getPrecioProducto(),
                productoDao.getFotoProducto(),
                productoDao.getStockProducto(),
                productoDao.getCategoriaProducto(), 
                convertirAMarcaDto(productoDao.getMarca())  // Conversión de MarcaDao a MarcaDto
        );
    }

    private MarcaDto convertirAMarcaDto(MarcaDao marcaDao) {
        if (marcaDao == null) {
            return null;
        }
        return new MarcaDto(marcaDao.getIdMarca(), marcaDao.getNombreMarca(), marcaDao.getPaisOrigen(), marcaDao.getAnioFundacion(), marcaDao.getDescripcion());
    }


    

    private ProductoDao convertirADao(ProductoDto productoDto) {
        if (productoDto == null) {
            return null;
        }
        ProductoDao productoDao = new ProductoDao();
        if (productoDto.getId() != null) {
            productoDao.setIdProducto(productoDto.getId());
        }
        productoDao.setNombreProducto(productoDto.getNombre());
        productoDao.setDescripcionProducto(productoDto.getDescripcion());
        productoDao.setPrecioProducto(productoDto.getPrecio());
        productoDao.setFotoProducto(productoDto.getImagen());
        productoDao.setStockProducto(productoDto.getStock());
        productoDao.setCategoriaProducto(productoDto.getCategoria());
        
        // Asignar la marca
        if (productoDto.getMarca() != null) {
            // Se carga la marca persistente usando el id del DTO.
            MarcaDao marca = marcaRepository.findById(productoDto.getMarca().getId())
                .orElseThrow(() -> new RuntimeException("Marca no encontrada"));
            productoDao.setMarca(marca);
        } else {
            // Si la marca viene como null, se podría lanzar un error o manejarlo según la lógica de negocio.
            throw new RuntimeException("La marca es obligatoria");
        }
        return productoDao;
    }

}
