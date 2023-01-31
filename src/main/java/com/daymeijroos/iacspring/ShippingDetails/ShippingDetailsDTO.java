package com.daymeijroos.iacspring.ShippingDetails;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.Data;

@Data
public class ShippingDetailsDTO {
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY)
    private String userId;

    @NotBlank(message = "First name cannot be empty.")
    private String firstName;

    @NotBlank(message = "Last name cannot be empty")
    private String lastName;

    @Nullable
    @Size(min = 10, max = 10, message = "Length does not match a regular phone number")
    @Pattern(regexp = "^[0-9]*$")
    private String phone;

    @NotNull(message = "Country cannot be null")
    private Country country;

    @NotBlank(message = "City cannot be empty")
    private String city;

    @NotBlank(message = "Postal code cannot be empty")
    @Size(min = 4, max = 10, message = "Length does not match a postal code")
    private String postalCode;

    @NotBlank(message = "Address cannot be empty")
    private String address;
}
