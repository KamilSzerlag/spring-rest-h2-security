package kamilszerlag.model;

public enum Role{
    ADMIN("ADMIN"),
    LOGGED_USER("LOGGED"),
    VIP("VIP");

    String role_name;

    Role(String role_name) {
        this.role_name = role_name;
    }
}
