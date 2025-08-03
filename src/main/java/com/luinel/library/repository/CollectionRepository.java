package com.luinel.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luinel.library.model.Collection;

@Repository
public interface CollectionRepository extends JpaRepository<Collection, Long> {

}
