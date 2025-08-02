package com.luinel.library.model;

import java.io.Serializable;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Embeddable
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RatingId implements Serializable {
  @NonNull
  private Long userId;

  @NonNull
  private Long bookId;
}
