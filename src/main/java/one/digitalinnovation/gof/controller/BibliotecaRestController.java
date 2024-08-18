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
import one.digitalinnovation.gof.service.BibliotecaService;

@RestController
@RequestMapping("bibliotecas")
public class BibliotecaRestController {

	@Autowired
	private BibliotecaService bibliotecaService;

	@GetMapping
	public ResponseEntity<Iterable<Biblioteca>> buscarTodos() {
		return ResponseEntity.ok(bibliotecaService.buscarTodos());
	}

	@GetMapping("/{id}")
	public ResponseEntity<Biblioteca> buscarPorId(@PathVariable Long id) {
		return ResponseEntity.ok(bibliotecaService.buscarPorId(id));
	}

	@PostMapping
	public ResponseEntity<Biblioteca> inserir(@RequestBody Biblioteca biblioteca) {
		bibliotecaService.inserir(biblioteca);
		return ResponseEntity.ok(biblioteca);
	}

	@PutMapping("/{id}")
	public ResponseEntity<Biblioteca> atualizar(@PathVariable Long id, @RequestBody Biblioteca biblioteca) {
		bibliotecaService.atualizar(id, biblioteca);
		return ResponseEntity.ok(biblioteca);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deletar(@PathVariable Long id) {
		bibliotecaService.deletar(id);
		return ResponseEntity.ok().build();
	}
}
