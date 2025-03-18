package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Edicion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EdicionRepository extends JpaRepository<Edicion, Integer> {
}
