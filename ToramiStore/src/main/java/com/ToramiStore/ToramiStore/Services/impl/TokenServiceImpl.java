    package com.ToramiStore.ToramiStore.Services.impl;

    import com.ToramiStore.ToramiStore.Repository.UserRepository;
    import org.springframework.scheduling.annotation.Scheduled;
    import org.springframework.stereotype.Service;
    import org.springframework.transaction.annotation.Transactional;

    import java.time.LocalDateTime;
    import java.util.UUID;

    @Service
    public class TokenServiceImpl {

        private final UserRepository userRepository;

        public TokenServiceImpl(UserRepository userRepository) {
            this.userRepository = userRepository;
        }


        @Transactional
        @Scheduled(fixedRate = 60000)
        public void removeExpiredTokens() {
            System.out.println("⏳ Eliminando tokens expirados...");
            int affectedRows = userRepository.clearExpiredTokens(LocalDateTime.now());
            userRepository.flush();
            System.out.println("✅ Tokens expirados eliminados. Registros afectados: " + affectedRows);
        }



    }
