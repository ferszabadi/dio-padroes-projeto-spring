package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Biblioteca;
import one.digitalinnovation.gof.model.BibliotecaRepository;
import one.digitalinnovation.gof.model.Endereco;
import one.digitalinnovation.gof.model.EnderecoRepository;
import one.digitalinnovation.gof.service.BibliotecaService;
import one.digitalinnovation.gof.service.ViaCepService;

@Service
public class BibliotecaServiceImpl implements BibliotecaService {

	// Singleton: Injetar os componentes do Spring com @Autowired.
	@Autowired
	private BibliotecaRepository bibliotecaRepository;
	@Autowired
	private EnderecoRepository enderecoRepository;
	@Autowired
	private ViaCepService viaCepService;
	
	// Strategy: Implementar os métodos definidos na interface.
	// Facade: Abstrair integrações com subsistemas, provendo uma interface simples.

	@Override
	public Iterable<Biblioteca> buscarTodos() {
		// Buscar todas as Bibliotecas.
		return bibliotecaRepository.findAll();
	}

	@Override
	public Biblioteca buscarPorId(Long id) {
		// Buscar Biblioteca por ID.
		Optional<Biblioteca> biblioteca = bibliotecaRepository.findById(id);
		return biblioteca.get();
	}

	@Override
	public void inserir(Biblioteca biblioteca) {
		salvarBibliotecaComCep(biblioteca);
	}

	@Override
	public void atualizar(Long id, Biblioteca biblioteca) {
		// Buscar Biblioteca por ID, caso exista:
		Optional<Biblioteca> bibliotecaBd = bibliotecaRepository.findById(id);
		if (bibliotecaBd.isPresent()) {
			salvarBibliotecaComCep(biblioteca);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Biblioteca por ID.
		bibliotecaRepository.deleteById(id);
	}

	private void salvarBibliotecaComCep(Biblioteca biblioteca) {
		// Verificar se o Endereco da Biblioteca já existe (pelo CEP).
		String cep = biblioteca.getEndereco().getCep();
		Endereco endereco = enderecoRepository.findById(cep).orElseGet(() -> {
			// Caso não exista, integrar com o ViaCEP e persistir o retorno.
			Endereco novoEndereco = viaCepService.consultarCep(cep);
			enderecoRepository.save(novoEndereco);
			return novoEndereco;
		});
		biblioteca.setEndereco(endereco);
		// Inserir Biblioteca, vinculando o Endereco (novo ou existente).
		bibliotecaRepository.save(biblioteca);
	}

}
