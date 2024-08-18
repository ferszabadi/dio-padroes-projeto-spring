package one.digitalinnovation.gof.service;

import one.digitalinnovation.gof.model.Biblioteca;

public interface BibliotecaService {

	Iterable<Biblioteca> buscarTodos();

	Biblioteca buscarPorId(Long id);

	void inserir(Biblioteca cliente);

	void atualizar(Long id, Biblioteca cliente);

	void deletar(Long id);

}
