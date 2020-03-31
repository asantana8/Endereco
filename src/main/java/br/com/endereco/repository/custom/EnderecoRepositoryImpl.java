package br.com.endereco.repository.custom;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import br.com.endereco.domain.Endereco;

public class EnderecoRepositoryImpl implements EnderecoRepositoryCustom {

	@PersistenceContext
	private EntityManager entityManager;

	public Endereco pesquisarBairro(String bairro) {

		Query query = entityManager.createNativeQuery(
				"select e.id, e.logradouro, e.numero,"
						+ "e.complemento, e.bairro, e.cep, e.cidade_id from endereco e where e.bairro = :bairro",
				Endereco.class);
		query.setParameter("bairro", bairro);

		return (Endereco) query.getSingleResult();
	}

}
