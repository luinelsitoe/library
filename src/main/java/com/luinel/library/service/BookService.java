package com.luinel.library.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.luinel.library.model.Book;
import com.luinel.library.model.enums.Genre;
import com.luinel.library.model.form.BookForm;
import com.luinel.library.repository.BookRepository;
import com.luinel.library.service.exception.UploadFileException;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookService {
  private final BookRepository bookRepository;

  public String publishBook(BookForm bookForm) {
    var book = bookForm.toBook();
    bookRepository.save(book);
    return "Livro publicado!";
  }

  private Book findBook(Long bookId) {
    return bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado"));
  }

  public String updateBook(Long bookId, BookForm bookForm) {
    var book = findBook(bookId);
    book.setAuthor(bookForm.getAuthor());
    book.setDescription(bookForm.getDescription());
    book.setGenre(bookForm.getGenre());
    book.setTitle(bookForm.getTitle());

    bookRepository.save(book);
    return "Livro actualizado!";
  }

  public List<Book> getAllBooks() {
    return bookRepository.findAll();
  }

  public List<Book> getAllBooksByTitle(String title) {
    return bookRepository.findByTitleContaining(title);
  }

  public List<Book> getAllBooksByAuthor(String author) {
    return bookRepository.findByAuthorContaining(author);
  }

  public List<Book> getAllBooksByGenre(Genre genre) {
    return bookRepository.findByGenre(genre);
  }

  public Book getBookById(Long bookId) {
    return findBook(bookId);
  }

  public String deleteBook(Long bookId) {
    bookRepository.deleteById(bookId);
    return "Livro removido!";
  }

  private void uploadFile(MultipartFile file, String folderName) {
    try {
      Path destination = Paths.get("/uploads", folderName, file.getOriginalFilename());
      Files.createDirectories(destination.getParent());
      Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
    } catch (IOException ex) {
      throw new UploadFileException("Erro ao salvar ficheiro: " + file.getOriginalFilename(), ex);
    }
  }

  public String uploadBookFile(MultipartFile bookFile) {
    uploadFile(bookFile, "book-files");
    return "Ficheiro do livro salvo!";
  }

  public String uploadCoverFile(MultipartFile coverFile) {
    uploadFile(coverFile, "cover-files");
    return "Capa salva!";
  }

}
