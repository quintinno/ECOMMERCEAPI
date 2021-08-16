package br.com.ecommerce.ecommerce.repository;

import br.com.ecommerce.ecommerce.model.CategoriaProdutoModel;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoriaProdutoRepository extends JpaRepository<CategoriaProdutoModel, Long> { }
