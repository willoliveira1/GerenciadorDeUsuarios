package br.com.anhanguera.Gerenciador.resources;

import br.com.anhanguera.Gerenciador.entities.User;
import br.com.anhanguera.Gerenciador.services.UserService;
import br.com.anhanguera.Gerenciador.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping(value = "/users")
public class UserResource {

    @Autowired
    UserService userService;

    @Autowired
    public UserResource(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public List<User> findAll() {
        return userService.findAll();
    }

    @GetMapping("/{id}")
    public Optional<User> findById(@PathVariable Long id) {
        try {
            return userService.findById(id);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    @PostMapping
    public User insert(@RequestBody User user) {
        return userService.insert(user);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        try {
            userService.delete(id);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<User> update(@PathVariable Long id, @RequestBody User userDetails) {
        try {
            Optional<User> userOptional = userService.findById(id);
            if (!userOptional.isPresent()) {
                return ResponseEntity.notFound().build();
            }

            User user = userOptional.get();

            user.setNome(userDetails.getNome());
            user.setEmail(userDetails.getEmail());
            user.setTelefone(userDetails.getTelefone());
            user.setPassword(userDetails.getPassword());

            userService.update(user);
            return ResponseEntity.ok(user);
        } catch (ResourceNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

}
