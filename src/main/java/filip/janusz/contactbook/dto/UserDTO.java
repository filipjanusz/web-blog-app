package filip.janusz.contactbook.dto;


import filip.janusz.contactbook.domain.Role;
import org.hibernate.validator.constraints.NotEmpty;

import javax.validation.constraints.Size;

public class UserDTO extends RecaptchaDTO {

    @NotEmpty
    @Size(min = 3, max = 20, message = "First name length must be between 3 and 20 tokens")
    private String firstname;
    @NotEmpty
    @Size(min = 3, max = 20, message = "Last name length must be between 3 and 20 tokens")
    private String lastname;
    @NotEmpty
    @Size(min = 4, max = 40, message = "Email adress length must be between 4 and 40 tokens")
    private String email;
    @NotEmpty
    @Size(min = 4, max = 20, message = "Username length must be between 4 and 20 tokens")
    private String username;
    @NotEmpty
    @Size(min = 4, max = 20, message = "Password length must be between 4 and 20 tokens")
    private String password;
    @NotEmpty
    //@Size(min = 4, max = 20, message = "Password length must be between 4 and 20 tokens")
    private String confirmPassword;
    private Role role;

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }
}
