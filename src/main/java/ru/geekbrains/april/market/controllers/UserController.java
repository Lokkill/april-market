package ru.geekbrains.april.market.controllers;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.geekbrains.april.market.dtos.JwtResponse;
import ru.geekbrains.april.market.dtos.UserDto;
import ru.geekbrains.april.market.error_handling.ResourceNotFoundException;
import ru.geekbrains.april.market.models.Role;
import ru.geekbrains.april.market.models.User;
import ru.geekbrains.april.market.services.RoleService;
import ru.geekbrains.april.market.services.UserService;

import java.security.Principal;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {
    private final UserService userService;
    private final PasswordEncoder passwordEncoder;
    private final RoleService roleService;

    @GetMapping("/me")
    public UserDto getCurrentUsername(Principal principal) {
        User currentUser = userService.findByUsername(principal.getName()).orElseThrow(() -> new ResourceNotFoundException("User doesn't exist"));
        return new UserDto(currentUser.getUsername(), currentUser.getEmail());
    }

    @PostMapping
    public void register(@RequestBody @Validated User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword())); // encode email to bcrypt
        Collection<Role> list = new ArrayList<>();
        list.add(roleService.getRoleByName("ROLE_USER"));
        user.setRoles(list);
        userService.addUser(user);
    }

    // Это для проверки через постман
    @GetMapping("/all")
    public List<User> getAll(){
        return userService.findAll();
    }
}
