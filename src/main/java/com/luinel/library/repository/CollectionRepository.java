package com.luinel.library.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luinel.library.model.BookCollection;

@Repository
public interface CollectionRepository extends JpaRepository<BookCollection, Long> {
  List<BookCollection> findAllByUserId(Long userId);

  Optional<BookCollection> findByIdAndUserId(Long collectionId, Long userId);

  List<BookCollection> findByNameContainingAndUserId(String name, Long userId);

  void deleteByIdAndUserId(Long collectionId, Long userId);

}
