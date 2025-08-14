package com.luinel.library.model.form;

import com.luinel.library.model.Book;
import com.luinel.library.model.enums.Genre;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class BookForm {
  @NotBlank(message = "Preencha o titulo")
  private String title;

  @NotBlank(message = "Preencha o nome do author")
  private String author;

  @NotBlank(message = "Preencha a descrição do livro")
  private String description;

  @NotNull(message = "Selecione o genero")
  private Genre genre;

  @NotBlank(message = "Preencha o caminho do arquivo do livro")
  private String bookPath;

  private String coverPath;

  public Book toBook() {
    Book book = new Book();
    book.setTitle(this.title);
    book.setAuthor(this.author);
    book.setDescription(this.description);
    book.setGenre(this.genre);
    book.setBookPath(this.bookPath);
    book.setCoverPath(this.coverPath);
    return book;
  }
}
