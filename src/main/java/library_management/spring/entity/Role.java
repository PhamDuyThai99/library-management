package library_management.spring.entity;

import lombok.Getter;

@Getter
public enum Role {
    ROLE_MEMBER("ROLE_MEMBER"),
    ROLE_ADMIN("ROLE_ADMIN")
    ;
    private final String name;

    Role(String name) {
        this.name = name;
    }

}
