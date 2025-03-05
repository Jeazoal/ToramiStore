package com.ToramiStore.ToramiStore.Repository;

import com.ToramiStore.ToramiStore.Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User,Integer> {

    User findByVerificationToken(String token);
    Optional<User> findByCorreo(String correo);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.verificationToken = NULL, u.tokenExpiration = NULL WHERE u.tokenExpiration <= :now")
    int clearExpiredTokens(@Param("now") LocalDateTime now);

}
