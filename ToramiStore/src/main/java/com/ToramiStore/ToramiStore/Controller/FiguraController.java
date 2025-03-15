package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Services.IFigura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/figuras")
@CrossOrigin(origins = "*")
public class FiguraController {

    @Autowired
    private IFigura figuraService;

    // ✅ Buscar figuras por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Figura>> buscarPorNombre(@RequestParam String nombre) {
        List<Figura> figuras = figuraService.buscarPorNombre(nombre);
        return figuras.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras destacadas
    @GetMapping("/destacadas")
    public ResponseEntity<List<Figura>> obtenerFigurasDestacadas() {
        List<Figura> figuras = figuraService.obtenerFigurasDestacadas();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras más vendidas
    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<Figura>> obtenerMasVendidos() {
        List<Figura> figuras = figuraService.obtenerMasVendidos();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras ordenadas por precio
    @GetMapping("/mayor-precio")
    public ResponseEntity<List<Figura>> obtenerPorMayorPrecio() {
        List<Figura> figuras = figuraService.obtenerPorMayorPrecio();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/menor-precio")
    public ResponseEntity<List<Figura>> obtenerPorMenorPrecio() {
        List<Figura> figuras = figuraService.obtenerPorMenorPrecio();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Últimas unidades en stock
    @GetMapping("/ultimas-unidades")
    public ResponseEntity<List<Figura>> obtenerUltimasUnidades() {
        List<Figura> figuras = figuraService.obtenerUltimasUnidades();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras en oferta
    @GetMapping("/ofertas/{tipoOferta}")
    public ResponseEntity<List<Figura>> obtenerPorOferta(@PathVariable String tipoOferta) {
        List<Figura> figuras = figuraService.obtenerPorOferta(tipoOferta);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras en preventa
    @GetMapping("/preventas")
    public ResponseEntity<List<Figura>> obtenerPreventas() {
        List<Figura> figuras = figuraService.obtenerPreventas();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Figuras por categoría y filtros adicionales
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Figura>> obtenerPorCategoria(@PathVariable Integer idCategoria) {
        List<Figura> figuras = figuraService.obtenerPorCategoria(idCategoria);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/fabricante/{idFabricante}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYFabricante(@PathVariable Integer idCategoria, @PathVariable Integer idFabricante) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYFabricante(idCategoria, idFabricante);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/marca/{idMarca}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYMarca(@PathVariable Integer idCategoria, @PathVariable Integer idMarca) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYMarca(idCategoria, idMarca);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/material/{idMaterial}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYMaterial(@PathVariable Integer idCategoria, @PathVariable Integer idMaterial) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYMaterial(idCategoria, idMaterial);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/tematica/{idTematica}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYTematica(@PathVariable Integer idCategoria, @PathVariable Integer idTematica) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYTematica(idCategoria, idTematica);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/edicion/{idEdicion}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYEdicion(@PathVariable Integer idCategoria, @PathVariable Integer idEdicion) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYEdicion(idCategoria, idEdicion);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }
}
