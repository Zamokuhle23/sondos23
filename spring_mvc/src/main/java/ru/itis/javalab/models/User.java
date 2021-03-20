package ru.itis.javalab.models;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Getter
@Setter
@Builder
@EqualsAndHashCode
@ToString
public class User {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String confirmCode;
    private State state;

    public enum State {
        CONFIRMED, NOT_CONFIRMED
    }
}
