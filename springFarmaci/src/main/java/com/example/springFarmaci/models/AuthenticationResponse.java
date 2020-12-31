package com.example.springFarmaci.models;

public class AuthenticationResponse {
    private final String jwt;
    private final String firstName;

    public AuthenticationResponse(String jwt, String firstName)
    {
        this.jwt = jwt;
        this.firstName = firstName;
    }

    public String getJwt(){
        return jwt;
    }

    public String getFirstName() {
        return firstName;
    }
}
