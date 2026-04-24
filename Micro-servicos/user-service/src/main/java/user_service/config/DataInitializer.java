package user_service.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import user_service.entity.User;
import user_service.repository.UserRepository;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        // Verificar se o utilizador de teste já existe
        if (!userRepository.existsByUsername("testuser")) {
            User testUser = new User();
            testUser.setUsername("testuser");
            testUser.setFullname("Test User");
            testUser.setEmail("testuser@example.com");
            testUser.setPassword(passwordEncoder.encode("password123"));

            userRepository.save(testUser);
            System.out.println("✓ Utilizador de teste criado com sucesso!");
            System.out.println("  Username: testuser");
            System.out.println("  Password: password123");
        } else {
            System.out.println("✓ Utilizador de teste já existe na base de dados");
        }
    }
}
