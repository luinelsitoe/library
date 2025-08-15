package com.luinel.library.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.luinel.library.model.Rating;
import com.luinel.library.model.RatingId;

@Repository
public interface RatingRepository extends JpaRepository<Rating, RatingId> {
  Double findAverageRatingByBookId(Long bookId);
}
