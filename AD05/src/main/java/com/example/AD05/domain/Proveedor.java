
package com.example.AD05.domain;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "PROVEEDORES")
@Getter
@Setter
@NoArgsConstructor
public class Proveedor {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    @Size(max = 100)
    @Column(nullable = false, length = 100)
    private String nombre;

    @Size(max = 200)
    @Column(length = 200)
    private String direccion;

    @Size(max = 20)
    @Column(length = 20)
    private String telefono;

    @Size(max = 100)
    @Column(length = 100)
    private String email;

    @OneToMany(
        mappedBy = "proveedor",
        cascade = CascadeType.ALL,
        orphanRemoval = true
    )
    private List<Producto> productos = new ArrayList<>();
}
