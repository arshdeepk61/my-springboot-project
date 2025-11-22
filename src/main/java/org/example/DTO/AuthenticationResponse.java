package org.example.DTO;


   // creates no-args constructor // creates all-args constructor
public class AuthenticationResponse {
    private String token;

    public AuthenticationResponse(String token) {
        this.token = token;
    }

    // getter
    public String getToken() {
        return token;
    }
}

