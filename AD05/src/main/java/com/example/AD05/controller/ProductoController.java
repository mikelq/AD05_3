
package com.example.AD05.controller;

import com.example.AD05.domain.Producto;
import com.example.AD05.service.ProductoService;
import com.example.AD05.service.ProveedorService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/productos")
@RequiredArgsConstructor
public class ProductoController {

    private final ProductoService productoService;
    private final ProveedorService proveedorService;

    // Listar productos
    @GetMapping
    public String listar(Model model) {
        model.addAttribute("productos",
                productoService.listarTodos());

        return "productos/lista";
    }

    // Mostrar formulario de alta
    @GetMapping("/nuevo")
    public String nuevo(Model model) {

        if (proveedorService.listarTodos().isEmpty()) {
            model.addAttribute("mensaje",
                    "Primero debes crear un proveedor");

            return "productos/sin-proveedores";
        }

        model.addAttribute("producto", new Producto());
        model.addAttribute("proveedores",
                proveedorService.listarTodos());

        return "productos/formulario";
    }

    // Guardar producto
    @PostMapping("/guardar")
    public String guardar(
            @Valid @ModelAttribute("producto") Producto producto,
            BindingResult result,
            @RequestParam("proveedorId") Long proveedorId,
            Model model) {

        if (result.hasErrors()) {
            model.addAttribute("proveedores",
                    proveedorService.listarTodos());

            return "productos/formulario";
        }

        productoService.guardar(producto, proveedorId);

        return "redirect:/productos";
    }

    // Mostrar formulario de edición
    @GetMapping("/editar/{id}")
    public String editar(
            @PathVariable Long id,
            Model model) {

        Producto producto = productoService.buscarPorId(id)
                .orElseThrow(() ->
                    new IllegalArgumentException("Producto no encontrado"));

        model.addAttribute("producto", producto);
        model.addAttribute("proveedores",
                proveedorService.listarTodos());

        return "productos/formulario";
    }

    // Eliminar producto
    @PostMapping("/eliminar/{id}")
    public String eliminar(
            @PathVariable Long id,
            RedirectAttributes redirectAttributes) {

        productoService.eliminar(id);

        redirectAttributes.addFlashAttribute(
                "mensaje", "Producto eliminado correctamente");

        return "redirect:/productos";
    }
}
