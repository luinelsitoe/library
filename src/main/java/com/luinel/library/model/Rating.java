package com.luinel.library.model;

import com.luinel.library.model.exception.InvalidRatingException;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.MapsId;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Entity
@Table(name = "tb_rating")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Rating {
  @EmbeddedId
  private RatingId id;

  @ManyToOne
  @MapsId("userId")
  @JoinColumn(name = "user_id", nullable = false)
  @NonNull
  private User user;

  @ManyToOne
  @MapsId("bookId")
  @JoinColumn(name = "book_id", nullable = false)
  @NonNull
  private Book book;

  @Column(nullable = false)
  private int rating;

  public Rating(@NonNull User user, @NonNull Book book, int rating) {
    if (rating > 5 || rating < 1) {
      throw new InvalidRatingException("Avaliação fora da faixa");
    }
    this.user = user;
    this.book = book;
    this.rating = rating;
    this.id = new RatingId(user.getId(), book.getId());
  }

  public void setRating(int rating) {
    if (rating > 5 || rating < 1) {
      throw new InvalidRatingException("Avaliação fora da faixa");
    }
    this.rating = rating;
  }
}
