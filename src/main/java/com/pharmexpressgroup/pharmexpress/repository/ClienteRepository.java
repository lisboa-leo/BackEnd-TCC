package com.pharmexpressgroup.pharmexpress.repository;

import com.pharmexpressgroup.pharmexpress.model.Cliente;
import com.pharmexpressgroup.pharmexpress.model.Produto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Integer> {

    @Query(value = "select * from cliente where usuario = :usuario and senha = :senha", nativeQuery = true)
    public Cliente Login(String usuario, String senha);

}
