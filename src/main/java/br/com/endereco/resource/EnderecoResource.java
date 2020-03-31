package br.com.endereco.resource;

import java.net.URI;
import java.util.List;
import java.util.stream.Collectors;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import br.com.endereco.domain.Endereco;
import br.com.endereco.dto.EnderecoDTO;
import br.com.endereco.dto.EnderecoNewDTO;
import br.com.endereco.service.EnderecoService;
import javassist.tools.rmi.ObjectNotFoundException;

@RestController
@RequestMapping(value = "/enderecos")
public class EnderecoResource {

	@Autowired
	EnderecoService service;

	@PostMapping("/salvar")
	public ResponseEntity<Void> salvarEndereco(@Valid @RequestBody EnderecoNewDTO objDto) {

		Endereco obj = service.fromDTO(objDto);
		obj = service.insert(obj);

		URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(obj.getId()).toUri();
		return ResponseEntity.created(uri).build();

	}

	@PostMapping("pesquisar/bairro")
	public ResponseEntity<Endereco> pesquisarBairro(@RequestBody EnderecoDTO objDto) {

		Endereco obj = service.pesquisarBairro(objDto.getBairro());
		return ResponseEntity.ok().body(obj);

	}

	@GetMapping("/listar")
	public ResponseEntity<List<EnderecoDTO>> listarEndereco() {
		List<Endereco> lista = service.findAll();
		List<EnderecoDTO> listaDto = lista.stream().map(obj -> new EnderecoDTO(obj)).collect(Collectors.toList());
		return ResponseEntity.ok().body(listaDto);

	}

	@GetMapping("/pesquisar/{id}")
	public ResponseEntity<Endereco> findById(@PathVariable Integer id) throws ObjectNotFoundException {

		Endereco obj = service.findById(id);
		return ResponseEntity.ok().body(obj);

	}

}
