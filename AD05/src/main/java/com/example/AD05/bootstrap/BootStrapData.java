
package com.example.AD05.bootstrap;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.example.AD05.domain.Proveedor;
import com.example.AD05.domain.Producto;
import com.example.AD05.repository.ProveedorRepository;
import com.example.AD05.repository.ProductoRepository;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class BootStrapData implements CommandLineRunner {

    private final ProveedorRepository proveedorRepository;
    private final ProductoRepository productoRepository;

    @Transactional
    @Override
    public void run(String... args) throws Exception {

        // Si no hay datos, mete los datos por bootstrap
        if (proveedorRepository.count() == 0) {

            // Crear proveedores
            Proveedor proveedor1 = new Proveedor();
            proveedor1.setNombre("Distribuciones García");
            proveedor1.setDireccion("Calle Mayor 15, Madrid");
            proveedor1.setTelefono("912345678");
            proveedor1.setEmail("garcia@email.com");

            Proveedor proveedor2 = new Proveedor();
            proveedor2.setNombre("Suministros López");
            proveedor2.setDireccion("Avenida España 24, Madrid");
            proveedor2.setTelefono("987654321");
            proveedor2.setEmail("lopez@email.com");

            // Crear productos del proveedor 1
            Producto producto1 = new Producto();
            producto1.setNombre("Teclado inalámbrico");
            producto1.setDescripcion("Teclado Bluetooth");
            producto1.setPrecio(29.99);
            producto1.setStock(50);
            producto1.setProveedor(proveedor1);

            Producto producto2 = new Producto();
            producto2.setNombre("Ratón óptico");
            producto2.setDescripcion("Ratón USB");
            producto2.setPrecio(15.50);
            producto2.setStock(100);
            producto2.setProveedor(proveedor1);

            // Crear productos del proveedor 2
            Producto producto3 = new Producto();
            producto3.setNombre("Monitor 24 pulgadas");
            producto3.setDescripcion("Monitor Full HD");
            producto3.setPrecio(149.99);
            producto3.setStock(20);
            producto3.setProveedor(proveedor2);
            
            Producto producto4 = new Producto();
            producto4.setNombre("Teclado mecanico");
            producto4.setDescripcion("Retrailuminado");
            producto4.setPrecio(70.80);
            producto4.setStock(20);
            producto4.setProveedor(proveedor2);

            // Relacionar los productos con sus proveedores
            proveedor1.getProductos().add(producto1);
            proveedor1.getProductos().add(producto2);

            proveedor2.getProductos().add(producto3);
            proveedor2.getProductos().add(producto4);

            // Guardar los proveedores
            // Los productos se guardan por la cascada JPA
            proveedorRepository.save(proveedor1);
            proveedorRepository.save(proveedor2);

            System.out.println("DATOS INICIALES INSERTADOS CORRECTAMENTE");
        }
    }
}
