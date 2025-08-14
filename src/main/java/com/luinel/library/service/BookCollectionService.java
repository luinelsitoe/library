package com.luinel.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luinel.library.model.BookCollection;
import com.luinel.library.repository.BookCollectionRepository;
import com.luinel.library.repository.BookRepository;
import com.luinel.library.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookCollectionService {
  private final BookCollectionRepository collectionRepository;
  private final UserRepository userRepository;
  private final BookRepository bookRepository;

  private BookCollection findCollection(Long collectionId, Long userId) {
    return collectionRepository.findByIdAndUserId(collectionId, userId)
        .orElseThrow(() -> new EntityNotFoundException("Coleção não encontrada"));
  }

  public String createCollection(String name, Long userId) {
    var collection = new BookCollection();
    var user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuario não encotrado"));

    collection.setName(name);
    collection.setUser(user);
    collectionRepository.save(collection);
    return "Coleção criada!";
  }

  public String addToCollection(Long collectionId, Long userId, Long bookId) {
    var collection = findCollection(collectionId, userId);
    var book = bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

    collection.getBooks().add(book);
    collectionRepository.save(collection);
    return "Livro adicionado a " + collection.getName() + "!";
  }

  public String removeFromCollection(Long collectionId, Long userId, Long bookId) {
    var collection = findCollection(collectionId, userId);
    var book = bookRepository.findById(bookId)
        .orElseThrow(() -> new EntityNotFoundException("Livro não encontrado!"));

    collection.getBooks().remove(book);
    collectionRepository.save(collection);
    return "Livro removido de " + collection.getName() + "!";
  }

  public List<BookCollection> getAllUserCollections(Long userId) {
    return collectionRepository.findAllByUserId(userId);
  }

  public List<BookCollection> getAllUserCollectionsByName(String name, Long userId) {
    return collectionRepository.findByNameContainingAndUserId(name, userId);
  }

  public BookCollection getCollection(Long collectionId, Long userId) {
    return findCollection(collectionId, userId);
  }

  public String deleteCollection(Long collectionId, Long userId) {
    collectionRepository.deleteByIdAndUserId(collectionId, userId);
    return "Coleção removida!";
  }
}
