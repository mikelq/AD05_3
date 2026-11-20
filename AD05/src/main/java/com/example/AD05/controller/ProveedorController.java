
package com.example.AD05.controller;

import com.example.AD05.domain.Proveedor;
import com.example.AD05.service.ProveedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/proveedores")
@RequiredArgsConstructor
public class ProveedorController {

    private final ProveedorService proveedorService;

    // Listar proveedores
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("proveedores",
                proveedorService.listarTodos());

        return "proveedores/lista";
    }

    // Mostrar formulario de alta
    @GetMapping("/nuevo")
    public String nuevo(Model model) {
        model.addAttribute("proveedor", new Proveedor());
        
        return "proveedores/formulario";
    }

    // Guardar proveedor
    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("proveedor") Proveedor proveedor,
            BindingResult result) {

        if (result.hasErrors()) {
            return "proveedores/formulario";
        }

        proveedorService.guardar(proveedor);
        //redirigimos para que actualice 
        return "redirect:/proveedores";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        Proveedor proveedor = proveedorService.buscarPorId(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Proveedor no encontrado"));

        model.addAttribute("proveedor", proveedor);

        return "proveedores/formulario";
    }

    // Eliminar proveedor
    @PostMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        proveedorService.eliminar(id);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Proveedor eliminado correctamente");

        return "redirect:/proveedores";
    }
}
