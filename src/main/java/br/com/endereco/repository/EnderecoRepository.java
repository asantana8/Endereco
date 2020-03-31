package br.com.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.endereco.domain.Endereco;
import br.com.endereco.repository.custom.EnderecoRepositoryCustom;

@Repository
public interface EnderecoRepository extends JpaRepository<Endereco, Integer>, EnderecoRepositoryCustom {

}
