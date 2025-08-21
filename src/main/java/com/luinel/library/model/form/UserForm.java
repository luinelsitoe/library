package com.luinel.library.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserForm {
  @NotBlank(message = "Preencha o nome")
  private String name;

  @NotBlank(message = "Preencha a palavra-passe antiga")
  private String password;
}
