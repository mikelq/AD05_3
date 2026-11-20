
package com.example.AD05.service;

import com.example.AD05.domain.Producto;
import com.example.AD05.domain.Proveedor;

import com.example.AD05.repository.ProductoRepository;
import com.example.AD05.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProductoService {

    private final ProductoRepository productoRepository;
    private final ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public List<Producto> listarTodos() {
        return productoRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Producto> buscarPorId(Long id) {
        return productoRepository.findById(id);
    }

    @Transactional
    public Producto guardar(Producto producto, Long proveedorId) {

        Proveedor proveedor = proveedorRepository.findById(proveedorId).orElseThrow(() ->  new IllegalArgumentException("Proveedor no encontrado"));
                

        producto.setProveedor(proveedor);

        return productoRepository.save(producto);
    }

    @Transactional
    public void eliminar(Long id) {
        productoRepository.deleteById(id);
    }
}
