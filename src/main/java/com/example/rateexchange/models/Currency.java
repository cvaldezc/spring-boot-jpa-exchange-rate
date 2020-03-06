package com.example.rateexchange.models;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import lombok.Data;

@Data
public class Currency {

  @Pattern(regexp = "[A-Z]*")
  @Size(min = 3, max = 3)
  private String origin;

  @Pattern(regexp = "[A-Z]*")
  @Size(min = 3, max = 3)
  private String target;
}
