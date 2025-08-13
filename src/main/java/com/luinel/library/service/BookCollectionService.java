package com.luinel.library.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luinel.library.model.BookCollection;
import com.luinel.library.repository.CollectionRepository;
import com.luinel.library.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BookCollectionService {
  private final CollectionRepository collectionRepository;
  private final UserRepository userRepository;

  public String createCollection(String name, Long userId) {
    var collection = new BookCollection();
    var user = userRepository.findById(userId)
        .orElseThrow(() -> new EntityNotFoundException("Usuario não encotrado!"));

    collection.setName(name);
    collection.setUser(user);
    collectionRepository.save(collection);
    return "Coleção criada!";
  }

  public List<BookCollection> getAllUserCollections(Long userId) {
    return collectionRepository.findAllByUserId(userId);
  }

  public List<BookCollection> getAllUserCollectionsByName(String name, Long userId) {
    return collectionRepository.findByNameContainingAndUserId(name, userId);
  }

  public BookCollection getCollection(Long collectionId, Long userId) {
    return collectionRepository.findByIdAndUserId(collectionId, userId)
        .orElseThrow(() -> new EntityNotFoundException("Coleção não encontrada!"));
  }

  public String deleteCollection(Long collectionId, Long userId) {
    collectionRepository.deleteByIdAndUserId(collectionId, userId);
    return "Coleção removida!";
  }
}
