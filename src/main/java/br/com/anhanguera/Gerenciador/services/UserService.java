package br.com.anhanguera.Gerenciador.services;

import br.com.anhanguera.Gerenciador.entities.User;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public interface UserService {

    List<User> findAll();

    Optional<User> findById(Long id);

    User insert(User user);

    void delete(Long id);

    User update(User user);

}
