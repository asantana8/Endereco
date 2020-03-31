package br.com.endereco.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.endereco.domain.Cidade;
import br.com.endereco.domain.Endereco;
import br.com.endereco.dto.EnderecoNewDTO;
import br.com.endereco.repository.CidadeRepository;
import br.com.endereco.repository.EnderecoRepository;
import javassist.tools.rmi.ObjectNotFoundException;

@Service
public class EnderecoService {

	@Autowired
	private EnderecoRepository repository;

	@Autowired
	private CidadeRepository cidadeRepository;

	public Endereco fromDTO(EnderecoNewDTO objDto) {

		Cidade cidade = cidadeRepository.buscarCidade(objDto.getCidadeId());

		return new Endereco(null, objDto.getLogradouro(), objDto.getNumero(), objDto.getComplemento(),
				objDto.getBairro(), objDto.getCep(), cidade);
	}

	public Endereco insert(Endereco obj) {
		obj.setId(null);
		obj = repository.save(obj);
		return obj;
	}

	public Endereco pesquisarBairro(String bairro) {

		Endereco endereco = repository.pesquisarBairro(bairro);
		return endereco;
	}

	public List<Endereco> findAll() {
		return repository.findAll();
	}

	public Endereco findById(Integer id) throws ObjectNotFoundException {

		Optional<Endereco> obj = repository.findById(id);
		return obj.orElseThrow(() -> new ObjectNotFoundException("Endereço não encontrado!" + id));
	}

}
