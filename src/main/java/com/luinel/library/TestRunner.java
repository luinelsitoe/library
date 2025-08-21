package com.luinel.library;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;

import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

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

    var msg = ratingService.getBookRating(1L);
    System.out.println(msg);
  }

  public static class SimpleMultipartFile implements MultipartFile {
    private final String name;
    private final byte[] content;

    public SimpleMultipartFile(String name, byte[] content) {
      this.name = name;
      this.content = content;
    }

    @Override
    public String getName() {
      return name;
    }

    @Override
    public String getOriginalFilename() {
      return name;
    }

    @Override
    public String getContentType() {
      return null;
    }

    @Override
    public boolean isEmpty() {
      return content.length == 0;
    }

    @Override
    public long getSize() {
      return content.length;
    }

    @Override
    public byte[] getBytes() throws IOException {
      return content;
    }

    @Override
    public InputStream getInputStream() throws IOException {
      return new ByteArrayInputStream(content);
    }

    @Override
    public void transferTo(java.io.File dest) throws IOException, IllegalStateException {
      java.nio.file.Files.write(dest.toPath(), content);
    }
  }

}
