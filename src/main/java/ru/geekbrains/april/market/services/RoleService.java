package ru.geekbrains.april.market.services;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.geekbrains.april.market.models.Role;
import ru.geekbrains.april.market.repositories.RoleRepository;

@Service
@RequiredArgsConstructor
public class RoleService {
    private final RoleRepository roleRepository;

    public Role getRoleByName(String name){ return roleRepository.findByName(name);}
}
