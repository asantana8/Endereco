package br.com.endereco.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.endereco.domain.Cidade;

public class CidadeRepositoryImpl implements CidadeRopisotoryCustom {

	@PersistenceContext
	private EntityManager entityManager;
	
	public Cidade buscarCidade(Integer id) {

		Query query = entityManager.createNativeQuery("select c.id, c.nome, c.estado_id from cidade c where c.id = :id", Cidade.class);
		query.setParameter("id", id);

		return (Cidade) query.getSingleResult();
	}

}
