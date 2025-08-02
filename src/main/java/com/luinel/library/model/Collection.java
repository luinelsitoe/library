package com.luinel.library.model;

import java.util.HashSet;
import java.util.Set;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Table(name = "tb_collection")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Collection {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @ManyToOne
  @JoinColumn(name = "user_id", nullable = false)
  @NonNull
  @ToString.Exclude
  private User user;

  @ManyToMany
  @JoinTable(name = "tb_collection_book", joinColumns = @JoinColumn(name = "collection_id"), inverseJoinColumns = @JoinColumn(name = "book_id"))
  @ToString.Exclude
  private Set<Book> books = new HashSet<>();
}
