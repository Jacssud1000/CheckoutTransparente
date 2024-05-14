package br.com.meli.onboardingchecktransparente.dto;

import java.math.BigDecimal;
import java.util.Objects;
import javax.validation.constraints.NotNull;
import lombok.Data;

@Data
public class Identification {

  @NotNull
  private String type;
  @NotNull
  private String number;

  public Identification(String type, String number) {
    this.type = type;
    this.number = number;
  }

  public String getType() {
    return type;
  }

  public void setType(String type) {
    this.type = type;
  }

  public String getNumber() {
    return number;
  }

  public void setNumber(String number) {
    this.number = number;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Identification that = (Identification) o;
    return Objects.equals(type, that.type) && Objects.equals(number, that.number);
  }

  @Override
  public int hashCode() {
    return Objects.hash(type, number);
  }
}
