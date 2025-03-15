package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Figura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FiguraRepository extends JpaRepository<Figura, Integer> {

    // ✅ Buscar figuras por nombre
    @Query("SELECT f FROM Figura f WHERE LOWER(f.nombreFigura) LIKE LOWER(CONCAT('%', :nombre, '%'))")
    List<Figura> buscarPorNombre(@Param("nombre") String nombre);

    // ✅ Figuras destacadas
    List<Figura> findByDestacadoTrue();

    // ✅ Figuras más vendidas
    @Query("SELECT f FROM Figura f JOIN f.ventas v GROUP BY f.id ORDER BY SUM(v.cantidad) DESC")
    List<Figura> findMasVendidos();

    // ✅ Figuras ordenadas por precio
    List<Figura> findAllByOrderByPrecioDesc();
    List<Figura> findAllByOrderByPrecioAsc();

    // ✅ Últimas unidades en stock
    List<Figura> findByCantidadInventarioLessThanEqual(int cantidad);

    // ✅ Figuras en oferta
    @Query("SELECT f FROM Figura f JOIN f.ofertas o WHERE LOWER(o.tipo) = LOWER(:tipoOferta)")
    List<Figura> findByOfertaTipo(@Param("tipoOferta") String tipoOferta);

    // ✅ Figuras en preventa
    List<Figura> findByPreventaTrue();

    // ✅ Figuras por categoría y filtros adicionales
    List<Figura> findByCategoriaId(Integer categoriaId);
    List<Figura> findByCategoriaIdAndFabricanteId(Integer categoriaId, Integer fabricanteId);
    List<Figura> findByCategoriaIdAndMarcaId(Integer categoriaId, Integer marcaId);
    List<Figura> findByCategoriaIdAndMaterialId(Integer categoriaId, Integer materialId);
    List<Figura> findByCategoriaIdAndTematicaId(Integer categoriaId, Integer tematicaId);
    List<Figura> findByCategoriaIdAndEdicionId(Integer categoriaId, Integer edicionId);
}

