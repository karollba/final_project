package pl.visa.finalproject.config;

import jakarta.servlet.DispatcherType;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.ProviderManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import pl.visa.finalproject.employee.EmployeeDetailsService;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    private final EmployeeDetailsService employeeDetailsService;

    public SecurityConfig(EmployeeDetailsService employeeDetailsService) {
        this.employeeDetailsService = employeeDetailsService;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider(employeeDetailsService);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(auth -> auth
                    .dispatcherTypeMatchers(
                            DispatcherType.FORWARD,
                            DispatcherType.ERROR).permitAll()
                    .requestMatchers(
                            "/login",
                            "/theme/**",
                            "/css/**",
                            "/js/**",
                            "/error").permitAll()

                // tylko admin
                  .requestMatchers("/employee/**").hasAuthority("ROLE_ADMIN")
                  .requestMatchers("/supplier/add", "/supplier/edit", "/supplier/delete").hasAnyAuthority("ROLE_ADMIN")

                  // admin + employee
                  .requestMatchers("/delivery/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                  .requestMatchers("/productorder/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                  .requestMatchers("/ordered/**").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")

                  .requestMatchers("/product/list", "/product/details", "/product/search" ).hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                  .requestMatchers("/product/edit", "/product/add", "/product/delete").hasAuthority("ROLE_ADMIN")

                  .requestMatchers("/supplier/list", "/supplier/search").hasAnyAuthority("ROLE_ADMIN", "ROLE_EMPLOYEE")
                  .anyRequest().authenticated())

              .formLogin(form -> form
                .loginPage("/login")
                .defaultSuccessUrl("/delivery/list", true)
                .permitAll())
                .logout(logout -> logout.logoutUrl("/logout")
                        .logoutSuccessUrl("/login?logout")
                        .permitAll())
                .authenticationProvider(authenticationProvider());

        return http.build();
    }


}