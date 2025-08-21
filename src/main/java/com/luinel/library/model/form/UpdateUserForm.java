package com.luinel.library.model.form;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUserForm {
  @NotBlank(message = "Preencha o nome")
  private String name;

  @NotBlank(message = "Preencha a palavra-passe antiga")
  private String oldPassword;

  @NotBlank(message = "Preencha a palavra-passe nova")
  private String newPassword;

  @NotBlank(message = "Confirma a palavra-passe nova")
  private String confirmPassword;
}
