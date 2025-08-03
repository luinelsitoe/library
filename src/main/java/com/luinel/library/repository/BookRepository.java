package com.luinel.library.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luinel.library.model.Book;
import com.luinel.library.model.enums.Genre;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {
  List<Book> findByTitleContaining(String title);

  List<Book> findByAuthorContaining(String author);

  List<Book> findByGenre(Genre genre);
}
