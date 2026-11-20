
package com.example.AD05.service;

import com.example.AD05.domain.Proveedor;
import com.example.AD05.repository.ProveedorRepository;

import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ProveedorService {

    private final ProveedorRepository proveedorRepository;

    @Transactional(readOnly = true)
    public List<Proveedor> listarTodos() {
        return proveedorRepository.findAll();
    }

    @Transactional(readOnly = true)
    public Optional<Proveedor> buscarPorId(Long id) {
        return proveedorRepository.findById(id);
    }

    @Transactional
    public Proveedor guardar(Proveedor proveedor) {
        return proveedorRepository.save(proveedor);
    }

    @Transactional
    public void eliminar(Long id) {
        Proveedor proveedor = proveedorRepository.findById(id).orElseThrow(() -> new IllegalArgumentException("Proveedor no encontrado"));
         

        proveedorRepository.delete(proveedor);
    }
}
