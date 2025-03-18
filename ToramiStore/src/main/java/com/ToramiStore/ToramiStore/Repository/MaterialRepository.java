package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.Material;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MaterialRepository extends JpaRepository<Material, Integer> {
}
