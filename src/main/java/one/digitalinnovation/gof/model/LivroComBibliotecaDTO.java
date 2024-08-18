package one.digitalinnovation.gof.model;

public class LivroComBibliotecaDTO {

    private Livro livro;
    private Biblioteca biblioteca;

    public Livro getLivro() {
        return livro;
    }

    public void setLivro(Livro livro) {
        this.livro = livro;
    }

    public Biblioteca getBiblioteca() {
        return biblioteca;
    }

    public void setBiblioteca(Biblioteca biblioteca) {
        this.biblioteca = biblioteca;
    }
}