package br.com.undefined.api.controllers;

import br.com.undefined.api.entities.User;
import br.com.undefined.api.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/users")
public class UserController {

    @Autowired
    private UserService userService;

    @PostMapping("/signup")
    public ResponseEntity<User> signUp(@RequestBody @Valid User user){
        //user.setPassword(encoder.encode(user.getSenha()));
        userService.insert(user);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @DeleteMapping("/del/{id}")
    public ResponseEntity<Void> deleteById(Long id){
        userService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
