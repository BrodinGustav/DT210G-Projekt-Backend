package DT210G_Projekt.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import DT210G_Projekt.model.User;

public interface UserRepository extends JpaRepository<User, Long> { 
     Optional<User> findByEmail(String email);
      Optional<User> findById(String userId);
    
}
