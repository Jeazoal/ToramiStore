package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByVerificationToken(String token);
    User findByCorreo(String correo);
}
