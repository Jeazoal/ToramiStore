package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Tematica;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TematicaRepository extends JpaRepository<Tematica, Integer> {
}
