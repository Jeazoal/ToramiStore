package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Fabricante;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FabricanteRepository extends JpaRepository<Fabricante, Integer> {
}
