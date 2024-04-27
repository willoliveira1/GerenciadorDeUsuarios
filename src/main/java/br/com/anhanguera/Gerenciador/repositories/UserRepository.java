package br.com.anhanguera.Gerenciador.repositories;

import br.com.anhanguera.Gerenciador.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {
}
