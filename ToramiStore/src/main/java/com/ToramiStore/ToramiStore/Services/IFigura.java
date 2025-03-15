package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Entity.Figura;
import java.util.List;

public interface IFigura {

    // ✅ Buscar figuras por nombre
    List<Figura> buscarPorNombre(String nombre);

    // ✅ Figuras destacadas
    List<Figura> obtenerFigurasDestacadas();

    // ✅ Figuras más vendidas
    List<Figura> obtenerMasVendidos();

    // ✅ Figuras ordenadas por precio
    List<Figura> obtenerPorMayorPrecio();
    List<Figura> obtenerPorMenorPrecio();

    // ✅ Últimas unidades en stock
    List<Figura> obtenerUltimasUnidades();

    // ✅ Figuras en oferta
    List<Figura> obtenerPorOferta(String tipoOferta);

    // ✅ Figuras en preventa
    List<Figura> obtenerPreventas();

    // ✅ Figuras por categoría y filtros adicionales
    List<Figura> obtenerPorCategoria(Integer idCategoria);
    List<Figura> obtenerPorCategoriaYFabricante(Integer idCategoria, Integer idFabricante);
    List<Figura> obtenerPorCategoriaYMarca(Integer idCategoria, Integer idMarca);
    List<Figura> obtenerPorCategoriaYMaterial(Integer idCategoria, Integer idMaterial);
    List<Figura> obtenerPorCategoriaYTematica(Integer idCategoria, Integer idTematica);
    List<Figura> obtenerPorCategoriaYEdicion(Integer idCategoria, Integer idEdicion);
}

