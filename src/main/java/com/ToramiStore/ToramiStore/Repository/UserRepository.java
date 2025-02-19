package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

}
