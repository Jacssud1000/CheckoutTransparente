package br.com.meli.onboardingchecktransparente.dto;

import javax.validation.constraints.NotNull;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Data
public class PayerDto {
  private String name;
  @NotNull
  private String email;
  @NotNull
  private Identification identification;

  public PayerDto(String name, String email, Identification identification) {
    this.name = name;
    this.email = email;
    this.identification = identification;
  }

  public void setName(String name) {
    this.name = name;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public void setIdentification(Identification identification) {
    this.identification = identification;
  }

}
