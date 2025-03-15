package com.ToramiStore.ToramiStore.Services.impl;

import com.ToramiStore.ToramiStore.Entity.Figura;
import com.ToramiStore.ToramiStore.Repository.FiguraRepository;
import com.ToramiStore.ToramiStore.Services.IFigura;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FiguraServiceImpl implements IFigura {

    private final FiguraRepository figuraRepository;

    public FiguraServiceImpl(FiguraRepository figuraRepository) {
        this.figuraRepository = figuraRepository;
    }

    // ✅ Buscar figuras por nombre
    @Override
    public List<Figura> buscarPorNombre(String nombre) {
        return figuraRepository.buscarPorNombre(nombre);
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
    public List<Figura> obtenerPorCategoriaYMarca(Integer idCategoria, Integer idMarca) {
        return figuraRepository.findByCategoriaIdAndMarcaId(idCategoria, idMarca);
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

