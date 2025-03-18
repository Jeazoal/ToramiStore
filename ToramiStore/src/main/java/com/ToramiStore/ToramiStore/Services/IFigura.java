package com.ToramiStore.ToramiStore.Services;

import com.ToramiStore.ToramiStore.Entity.Figura;
import java.util.List;
import java.util.Optional;

public interface IFigura {

    // ✅ CRUD de Figura
    Figura guardarFigura(Figura figura);
    Optional<Figura> obtenerFiguraPorId(Integer id);
    Figura actualizarFigura(Integer id, Figura figura);
    void eliminarFigura(Integer id);

    // ✅ Buscar figuras por nombre
    List<Figura> buscarPorNombre(String nombre);

    // ✅ Buscar figura por código único
    Optional<Figura> buscarPorCodigoFigura(String codigoFigura);

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
    List<Figura> obtenerPorCategoriaYLinea(Integer idCategoria, Integer idLinea);
    List<Figura> obtenerPorCategoriaYMaterial(Integer idCategoria, Integer idMaterial);
    List<Figura> obtenerPorCategoriaYTematica(Integer idCategoria, Integer idTematica);
    List<Figura> obtenerPorCategoriaYEdicion(Integer idCategoria, Integer idEdicion);
}


