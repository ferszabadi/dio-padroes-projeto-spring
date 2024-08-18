package one.digitalinnovation.gof.service.impl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import one.digitalinnovation.gof.model.Biblioteca;
import one.digitalinnovation.gof.model.BibliotecaRepository;
import one.digitalinnovation.gof.model.Livro;
import one.digitalinnovation.gof.model.LivroRepository;
import one.digitalinnovation.gof.service.LivroService;

@Service
public class LivroServiceImpl implements LivroService {

	@Autowired
	private LivroRepository livroRepository;
	@Autowired
	private BibliotecaRepository bibliotecaRepository;

	@Override
	public Iterable<Livro> buscarTodos() {
		// Buscar todos os Livros.
		return livroRepository.findAll();
	}

	@Override
	public Livro buscarPorId(Long id) {
		// Buscar Livro por ID.
		Optional<Livro> livro = livroRepository.findById(id);
		return livro.get();
	}

    @Override
    public void inserir(Livro livro, Biblioteca biblioteca) {
        // Verifica se a biblioteca existe
        Biblioteca bibliotecaExistente = bibliotecaRepository.findById(biblioteca.getId())
            .orElseThrow(() -> new RuntimeException("Biblioteca não encontrada"));

        livro.setBiblioteca(bibliotecaExistente);
        livroRepository.save(livro);
    }

	@Override
	public void atualizar(Long id, Livro livro) {
		// Buscar Livro por ID, caso exista:
		Optional<Livro> livroBd = livroRepository.findById(id);
		if (livroBd.isPresent()) {
			livroRepository.save(livro);
		}
	}

	@Override
	public void deletar(Long id) {
		// Deletar Livro por ID.
		livroRepository.deleteById(id);
	}

}
