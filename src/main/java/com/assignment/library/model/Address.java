package com.assignment.library.model;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Address {
    @NotBlank(message = "Address.houseNo must be present")
    private String houseNo;
    @NotBlank(message = "Address.city must be present")
    private String city;
    @NotBlank(message = "Address.state must be present")
    private String state;
}
