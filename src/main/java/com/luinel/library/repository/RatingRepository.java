package com.luinel.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.luinel.library.model.Rating;
import com.luinel.library.model.RatingId;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
  @Query("SELECT AVG(r.rating) FROM Rating r WHERE r.book.id = :bookId")
  Double findAverageRatingByBookId(@Param("bookId") Long bookId);
}
