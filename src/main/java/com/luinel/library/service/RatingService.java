package com.luinel.library.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.luinel.library.model.Rating;
import com.luinel.library.model.RatingId;
import com.luinel.library.repository.BookRepository;
import com.luinel.library.repository.RatingRepository;
import com.luinel.library.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class RatingService {
  private final RatingRepository ratingRepository;
  private final BookRepository bookRepository;
  private final UserRepository userRepository;

  public String rateBook(Long bookId, Long userId, int ratingValue) {
    var ratingId = new RatingId(userId, bookId);

    if (ratingRepository.existsById(ratingId)) {
      var rating = ratingRepository.findById(ratingId).get();
      rating.setRating(ratingValue);
      ratingRepository.save(rating);

      return "Livro avaliado!";
    } else {
      var book = bookRepository.findById(bookId)
          .orElseThrow(() -> new EntityNotFoundException("Livro não encotrado"));

      var user = userRepository.findById(userId)
          .orElseThrow(() -> new EntityNotFoundException("Usuario não encontrado"));

      var rating = new Rating(user, book, ratingValue);
      book.setRating(rating);

      bookRepository.save(book);
      ratingRepository.save(rating);
      return "Livro avaliado!";
    }
  }

  public Double getBookRating(Long bookId) {
    return ratingRepository.findAvarageRatingByBookId(bookId);
  }

}
