package com.luinel.library.model;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
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

@Table(name = "tb_user")
@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column(nullable = false)
  @NonNull
  private String name;

  @Column(nullable = false)
  @NonNull
  private String password;

  @Column(nullable = false)
  private String role;

  @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
  @ToString.Exclude
  private List<Collection> booksCollections = new ArrayList<>();
}
