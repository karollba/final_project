package pl.visa.finalproject.employee;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.security.config.core.GrantedAuthorityDefaults;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "employee")
public class Employee implements UserDetails {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private UUID id;

    private String password;

    @Column(unique = true, nullable = false)
    private Long idToShow;

    @NotBlank(message = "Pole imię nie może być puste")
    @Min(value = 4, message = "Imię musi mieć minim 4 znaki")
    private String firstName;

    @NotBlank(message = "Pole nazwisko nie może być puste")
    @Min(value = 4, message = "Nazwisko musi mieć min 4 znaki!")
    private String lastName;

    private boolean adminAccess;
    private boolean deleted;
    private LocalDateTime timeDeleted;
    private String login;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        if (adminAccess) {
            return List.of(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        return List.of(new SimpleGrantedAuthority("ROLE_EMPLOYEE"));
    }

    @Override
    public String getUsername() {
        return login;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return !deleted;
    }


}
