package org.example.retodam.dto;

public class LoginResponse {

    private String token;
    private String tokenType = "Bearer";
    private Integer empresaid;
    private String role;

    public LoginResponse() {}

    public LoginResponse(String token, Integer id, String role) {
        this.token = token;
        this.empresaid = id;
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Integer getEmpresaid() {
        return empresaid;
    }

    public void setEmpresaid(Integer empresaid) {
        this.empresaid = empresaid;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getTokenType() {
        return tokenType;
    }

    public void setTokenType(String tokenType) {
        this.tokenType = tokenType;
    }
}
