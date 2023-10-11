package com.pharmexpressgroup.pharmexpress.repository;

import javax.transaction.Transactional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.pharmexpressgroup.pharmexpress.model.Produto;




@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Integer>{

    @Transactional
    void deleteByCodProduto(Integer codProduto);

}
