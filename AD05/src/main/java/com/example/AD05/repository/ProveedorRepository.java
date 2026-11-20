
package com.example.AD05.repository;

import com.example.AD05.domain.Proveedor;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ProveedorRepository
        extends JpaRepository<Proveedor, Long> {

}
