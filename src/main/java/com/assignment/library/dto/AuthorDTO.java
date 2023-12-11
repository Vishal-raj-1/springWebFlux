package com.assignment.library.dto;

import com.assignment.library.model.Address;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;


import java.util.List;
@AllArgsConstructor
@NoArgsConstructor
public class AuthorDTO {
    @Id
    private String id;
    @NotBlank(message = "Author.name must be present")
    private String name;
    @NotNull(message = "Author.address must be present")
    private Address address;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }
}
