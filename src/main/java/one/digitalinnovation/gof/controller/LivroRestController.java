package one.digitalinnovation.gof.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import one.digitalinnovation.gof.model.Biblioteca;
import one.digitalinnovation.gof.model.Livro;
import one.digitalinnovation.gof.model.LivroComBibliotecaDTO;
import one.digitalinnovation.gof.service.LivroService;

@RestController
@RequestMapping("livros")
public class LivroRestController {

	@Autowired
	private LivroService livroService;

	@GetMapping
	public ResponseEntity<Iterable<Livro>> buscarTodos() {
		return ResponseEntity.ok(livroService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Livro> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(livroService.buscarPorId(id));
	}

	@PostMapping
    public ResponseEntity<Livro> inserir(@RequestBody LivroComBibliotecaDTO dto) {
        Livro livro = dto.getLivro();
        Biblioteca biblioteca = dto.getBiblioteca();
        livroService.inserir(livro, biblioteca);
        return ResponseEntity.ok(livro);
    }

	@PutMapping("/{id}")
	public ResponseEntity<Livro> atualizar(@PathVariable Long id, @RequestBody Livro livro) {
		livroService.atualizar(id, livro);
		return ResponseEntity.ok(livro);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		livroService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
