package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Repository.FiguraRepository;
import com.ToramiStore.ToramiStore.Services.IFigura;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class FiguraServiceImpl implements IFigura {

    private final FiguraRepository figuraRepository;

    public FiguraServiceImpl(FiguraRepository figuraRepository) {
        this.figuraRepository = figuraRepository;
    }

    // ✅ CRUD de Figura
    @Override
    public Figura guardarFigura(Figura figura) {
        return figuraRepository.save(figura);
    }

    @Override
    public Optional<Figura> obtenerFiguraPorId(Integer id) {
        return figuraRepository.findById(id);
    }

    @Override
    public Figura actualizarFigura(Integer id, Figura figura) {
        return figuraRepository.findById(id)
                .map(figuraExistente -> {
                    // Verifica si los datos no son nulos antes de actualizar
                    if (figura.getCodigoFigura() != null) figuraExistente.setCodigoFigura(figura.getCodigoFigura());
                    if (figura.getNombreFigura() != null) figuraExistente.setNombreFigura(figura.getNombreFigura());
                    if (figura.getDescripcion() != null) figuraExistente.setDescripcion(figura.getDescripcion());
                    if (figura.getImagenUrl() != null) figuraExistente.setImagenUrl(figura.getImagenUrl());
                    if (figura.getPrecio() != null) figuraExistente.setPrecio(figura.getPrecio());
                    if (figura.getCantidadInventario() != null) figuraExistente.setCantidadInventario(figura.getCantidadInventario());
                    if (figura.getAltura() != null) figuraExistente.setAltura(figura.getAltura());
                    if (figura.getPeso() != null) figuraExistente.setPeso(figura.getPeso());
                    if (figura.getDestacado() != null) figuraExistente.setDestacado(figura.getDestacado());
                    if (figura.getPreventa() != null) figuraExistente.setPreventa(figura.getPreventa());


                    // Asignar relaciones con validación
                    if (figura.getCategoria() != null) figuraExistente.setCategoria(figura.getCategoria());
                    if (figura.getFabricante() != null) figuraExistente.setFabricante(figura.getFabricante());
                    if (figura.getLinea() != null) figuraExistente.setLinea(figura.getLinea());
                    if (figura.getMaterial() != null) figuraExistente.setMaterial(figura.getMaterial());
                    if (figura.getFranquicia() != null) figuraExistente.setFranquicia(figura.getFranquicia());
                    if (figura.getTematica() != null) figuraExistente.setTematica(figura.getTematica());
                    if (figura.getEdicion() != null) figuraExistente.setEdicion(figura.getEdicion());

                    return figuraRepository.save(figuraExistente);
                })
                .orElseThrow(() -> new RuntimeException("Figura no encontrada con ID: " + id));
    }

    @Override
    public void eliminarFigura(Integer id) {
        figuraRepository.deleteById(id);
    }

    // ✅ Buscar figuras por nombre
    @Override
    public List<Figura> buscarPorNombre(String nombre) {
        return figuraRepository.buscarPorNombre(nombre);
    }

    // ✅ Buscar figura por código único
    @Override
    public Optional<Figura> buscarPorCodigoFigura(String codigoFigura) {
        return figuraRepository.findByCodigoFigura(codigoFigura);
    }

    // ✅ Figuras destacadas
    @Override
    public List<Figura> obtenerFigurasDestacadas() {
        return figuraRepository.findByDestacadoTrue();
    }

    // ✅ Figuras más vendidas
    @Override
    public List<Figura> obtenerMasVendidos() {
        return figuraRepository.findMasVendidos();
    }

    // ✅ Figuras ordenadas por precio
    @Override
    public List<Figura> obtenerPorMayorPrecio() {
        return figuraRepository.findAllByOrderByPrecioDesc();
    }

    @Override
    public List<Figura> obtenerPorMenorPrecio() {
        return figuraRepository.findAllByOrderByPrecioAsc();
    }

    // ✅ Últimas unidades en stock
    @Override
    public List<Figura> obtenerUltimasUnidades() {
        return figuraRepository.findByCantidadInventarioLessThanEqual(5);
    }

    // ✅ Figuras en oferta
    @Override
    public List<Figura> obtenerPorOferta(String tipoOferta) {
        return figuraRepository.findByOfertaTipo(tipoOferta);
    }

    // ✅ Figuras en preventa
    @Override
    public List<Figura> obtenerPreventas() {
        return figuraRepository.findByPreventaTrue();
    }

    // ✅ Figuras por categoría y filtros adicionales
    @Override
    public List<Figura> obtenerPorCategoria(Integer idCategoria) {
        return figuraRepository.findByCategoriaId(idCategoria);
    }

    @Override
    public List<Figura> obtenerPorCategoriaYFabricante(Integer idCategoria, Integer idFabricante) {
        return figuraRepository.findByCategoriaIdAndFabricanteId(idCategoria, idFabricante);
    }

    @Override
    public List<Figura> obtenerPorCategoriaYLinea(Integer idCategoria, Integer idLinea) {
        return figuraRepository.findByCategoriaIdAndLineaId(idCategoria, idLinea);
    }

    @Override
    public List<Figura> obtenerPorCategoriaYMaterial(Integer idCategoria, Integer idMaterial) {
        return figuraRepository.findByCategoriaIdAndMaterialId(idCategoria, idMaterial);
    }

    @Override
    public List<Figura> obtenerPorCategoriaYTematica(Integer idCategoria, Integer idTematica) {
        return figuraRepository.findByCategoriaIdAndTematicaId(idCategoria, idTematica);
    }

    @Override
    public List<Figura> obtenerPorCategoriaYEdicion(Integer idCategoria, Integer idEdicion) {
        return figuraRepository.findByCategoriaIdAndEdicionId(idCategoria, idEdicion);
    }
}


