package br.com.endereco.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.endereco.domain.Cidade;
import br.com.endereco.repository.custom.CidadeRopisotoryCustom;

@Repository
public interface CidadeRepository extends JpaRepository<Cidade, Integer>, CidadeRopisotoryCustom {

}
