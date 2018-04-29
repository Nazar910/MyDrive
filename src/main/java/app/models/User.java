package app.models;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.annotation.Id;

import javax.validation.constraints.NotNull;
import java.math.BigInteger;

public class User {
    @Id
    private BigInteger id;
    @Indexed(unique = true)
    @NotNull
    private String userName;
    @NotNull
    private String password;
    private String email;

    private User() {}

    public User(String userName, String password, String email) {
        this.userName = userName;
        this.password = password;
        this.email = email;
    }

    public BigInteger getId() {
        return id;
    }

    public String getUserName() {
        return userName;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }
}