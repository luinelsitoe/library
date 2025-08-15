package com.luinel.library;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.luinel.library.service.BookCollectionService;
import com.luinel.library.service.BookService;
import com.luinel.library.service.RatingService;
import com.luinel.library.service.UserService;

import lombok.RequiredArgsConstructor;

@Component
@RequiredArgsConstructor
public class TestRunner implements CommandLineRunner {

  private final BookService bookService;
  private final BookCollectionService bookCollectionService;
  private final RatingService ratingService;
  private final UserService userService;

  @Override
  public void run(String... args) throws Exception {

    var msg = ratingService.rateBook(1L, 1L, 5);
    System.out.println("============================================================================================");
    System.out.println(msg);
  }

}
