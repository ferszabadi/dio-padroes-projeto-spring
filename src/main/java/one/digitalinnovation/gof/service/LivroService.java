package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Biblioteca;
import one.digitalinnovation.gof.model.Livro;

public interface LivroService {

	Iterable<Livro> buscarTodos();

	Livro buscarPorId(Long id);

	void inserir(Livro livro, Biblioteca biblioteca);

	void atualizar(Long id, Livro livro);

	void deletar(Long id);

}
