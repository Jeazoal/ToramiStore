package com.ToramiStore.ToramiStore.Controller;

import com.ToramiStore.ToramiStore.Adapter.FiguraAdapter;
import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Payloads.request.SavefiguraRequest;
import com.ToramiStore.ToramiStore.Payloads.request.EditfiguraRequest;
import com.ToramiStore.ToramiStore.Repository.*;
import com.ToramiStore.ToramiStore.Services.IFigura;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/figuras")
@CrossOrigin(origins = "*")
public class FiguraController {

    private final IFigura figuraService;
    private final FiguraAdapter figuraAdapter;

    @Autowired
    private CategoriaRepository categoriaRepository;

    @Autowired
    private FabricanteRepository fabricanteRepository;

    @Autowired
    private LineaRepository lineaRepository;

    @Autowired
    private MaterialRepository materialRepository;

    @Autowired
    private FranquiciaRepository franquiciaRepository;

    @Autowired
    private TematicaRepository tematicaRepository;

    @Autowired
    private EdicionRepository edicionRepository;

    @Autowired
    public FiguraController(IFigura figuraService, FiguraAdapter figuraAdapter) {
        this.figuraService = figuraService;
        this.figuraAdapter = figuraAdapter;
    }

    // ✅ Crear figura
    @PostMapping(value = "/crear", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Figura> crearFigura(@RequestBody SavefiguraRequest savefiguraRequest) {
        Figura figura = figuraAdapter.toEntity(savefiguraRequest);
        Figura nuevaFigura = figuraService.guardarFigura(figura);
        return ResponseEntity.ok(nuevaFigura);
    }

    // ✅ Obtener figura por ID
    @GetMapping("/{id}")
    public ResponseEntity<Figura> obtenerFiguraPorId(@PathVariable Integer id) {
        Optional<Figura> figura = figuraService.obtenerFiguraPorId(id);
        return figura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Actualizar figura
    @PutMapping(value = "/actualizar/{id}", consumes = "application/json", produces = "application/json")
    public ResponseEntity<Figura> actualizarFigura(@PathVariable Integer id, @RequestBody EditfiguraRequest request) {
        Optional<Figura> figuraExistente = figuraService.obtenerFiguraPorId(id);

        if (figuraExistente.isEmpty()) {
            return ResponseEntity.notFound().build();
        }

        Figura figura = figuraExistente.get();

        if (request.getCodigoFigura() != null) figura.setCodigoFigura(request.getCodigoFigura());
        if (request.getNombreFigura() != null) figura.setNombreFigura(request.getNombreFigura());
        if (request.getDescripcion() != null) figura.setDescripcion(request.getDescripcion());
        if (request.getImagenUrl() != null) figura.setImagenUrl(request.getImagenUrl());
        if (request.getPrecio() != null) figura.setPrecio(request.getPrecio());
        if (request.getCantidadInventario() != null) figura.setCantidadInventario(request.getCantidadInventario());
        if (request.getAltura() != null) figura.setAltura(request.getAltura());
        if (request.getPeso() != null) figura.setPeso(request.getPeso());
        if (request.getDestacado() != null) figura.setDestacado(request.getDestacado());
        if (request.getPreventa() != null) figura.setPreventa(request.getPreventa());

        if (request.getCategoriaId() != null) figura.setCategoria(categoriaRepository.findById(request.getCategoriaId()).orElse(null));
        if (request.getFabricanteId() != null) figura.setFabricante(fabricanteRepository.findById(request.getFabricanteId()).orElse(null));
        if (request.getLineaId() != null) figura.setLinea(lineaRepository.findById(request.getLineaId()).orElse(null));
        if (request.getMaterialId() != null) figura.setMaterial(materialRepository.findById(request.getMaterialId()).orElse(null));
        if (request.getFranquiciaId() != null) figura.setFranquicia(franquiciaRepository.findById(request.getFranquiciaId()).orElse(null));
        if (request.getTematicaId() != null) figura.setTematica(tematicaRepository.findById(request.getTematicaId()).orElse(null));
        if (request.getEdicionId() != null) figura.setEdicion(edicionRepository.findById(request.getEdicionId()).orElse(null));

        Figura figuraActualizada = figuraService.guardarFigura(figura);
        return ResponseEntity.ok(figuraActualizada);
    }

    // ✅ Eliminar figura
    @DeleteMapping("/eliminar/{id}")
    public ResponseEntity<Void> eliminarFigura(@PathVariable Integer id) {
        figuraService.eliminarFigura(id);
        return ResponseEntity.noContent().build();
    }

    // ✅ Buscar figuras por nombre
    @GetMapping("/buscar")
    public ResponseEntity<List<Figura>> buscarPorNombre(@RequestParam String nombre) {
        List<Figura> figuras = figuraService.buscarPorNombre(nombre);
        return figuras.isEmpty() ? ResponseEntity.status(404).build() : ResponseEntity.ok(figuras);
    }

    // ✅ Buscar figura por código
    @GetMapping("/codigo/{codigo}")
    public ResponseEntity<Figura> buscarPorCodigo(@PathVariable String codigo) {
        Optional<Figura> figura = figuraService.buscarPorCodigoFigura(codigo);
        return figura.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    // ✅ Obtener figuras destacadas
    @GetMapping("/destacadas")
    public ResponseEntity<List<Figura>> obtenerFigurasDestacadas() {
        List<Figura> figuras = figuraService.obtenerFigurasDestacadas();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Obtener figuras más vendidas
    @GetMapping("/mas-vendidos")
    public ResponseEntity<List<Figura>> obtenerMasVendidos() {
        List<Figura> figuras = figuraService.obtenerMasVendidos();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }
    // ✅ Obtener figuras ordenadas por precio
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

    // ✅ Obtener figuras en oferta
    @GetMapping("/ofertas/{tipoOferta}")
    public ResponseEntity<List<Figura>> obtenerPorOferta(@PathVariable String tipoOferta) {
        List<Figura> figuras = figuraService.obtenerPorOferta(tipoOferta);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Obtener figuras en preventa
    @GetMapping("/preventas")
    public ResponseEntity<List<Figura>> obtenerPreventas() {
        List<Figura> figuras = figuraService.obtenerPreventas();
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    // ✅ Obtener figuras por categoría y otros filtros
    @GetMapping("/categoria/{idCategoria}")
    public ResponseEntity<List<Figura>> obtenerPorCategoria(@PathVariable Integer idCategoria) {
        List<Figura> figuras = figuraService.obtenerPorCategoria(idCategoria);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/linea/{idLinea}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYLinea(@PathVariable Integer idCategoria, @PathVariable Integer idLinea) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYLinea(idCategoria, idLinea);
        return figuras.isEmpty() ? ResponseEntity.noContent().build() : ResponseEntity.ok(figuras);
    }

    @GetMapping("/categoria/{idCategoria}/fabricante/{idFabricante}")
    public ResponseEntity<List<Figura>> obtenerPorCategoriaYFabricante(@PathVariable Integer idCategoria, @PathVariable Integer idFabricante) {
        List<Figura> figuras = figuraService.obtenerPorCategoriaYFabricante(idCategoria, idFabricante);
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
