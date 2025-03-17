package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Venta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VentaRepository extends JpaRepository<Venta, Integer> {

    // 🔹 Obtener todas las ventas de una figura específica
    List<Venta> findByFiguraId(Integer idFigura);

    // 🔹 Obtener ventas por rango de fechas (opcional, si lo necesitas)
    // List<Venta> findByFechaBetween(LocalDateTime startDate, LocalDateTime endDate);
}