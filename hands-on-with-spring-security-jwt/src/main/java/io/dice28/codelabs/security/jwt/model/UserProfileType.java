package io.dice28.codelabs.security.jwt.model;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum UserProfileType {
    FAN("fan"), 
    ADMIN("admin"), 
    SUPERADMIN("super-admin");

    private String profileType;
}
