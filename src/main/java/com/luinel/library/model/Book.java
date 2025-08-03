package com.luinel.library.model;

import com.luinel.library.model.enums.Genre;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.ToString;

@Table(name = "tb_book")
@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Book {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NonNull
  private String title;

  @Column(nullable = false)
  @NonNull
  private String author;

  @Column(nullable = false)
  @NonNull
  private String description;

  @Enumerated(EnumType.STRING)
  @NonNull
  private Genre genre;

  @OneToMany(mappedBy = "book", cascade = CascadeType.ALL, orphanRemoval = true)
  @ToString.Exclude
  private Rating rating;
}
