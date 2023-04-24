package org.example.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class User {
    String userName;
    String password;
    String firstName;
    String lastName;
    String zipCode;
}
