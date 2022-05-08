package com.alejandro.banco.repository;

import com.alejandro.banco.entity.Cliente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {
    public Optional<Cliente> findByDni(Long dni);
    public boolean existsByDni(Long dni);
    public void deleteByDni(Long dni);
}
