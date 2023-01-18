package pro.sky.graduate_work_group5_team1.security;

import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.EnableGlobalAuthentication;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import pro.sky.graduate_work_group5_team1.model.User;
import pro.sky.graduate_work_group5_team1.repository.UserRepository;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@EnableConfigurationProperties
@EnableGlobalAuthentication
public class WebSecurityConfig {

    private static final String[] AUTH_WHITELIST = {
            "/swagger-resources/**",
            "/swagger-ui.html",
            "/v3/api-docs",
            "/webjars/**",
            "/login", "/register",
            "/ads", "/ads/*",
            "/ads/*/comments", "/ads/*/comments/*",
            "/users", "/users/*", "/users/me", "/images/*"
    };

//    @Bean
//    public InMemoryUserDetailsManager userDetailsService() {
//        UserDetails user = User.withDefaultPasswordEncoder()
//                .username("user@gmail.com")
//                .password("password")
//                .roles("USER")
//                .build();
//        return new InMemoryUserDetailsManager(user);
//    }

    @Bean
    public UserDetailsService userDetailsService(UserRepository userRepository) {
        return username -> {
            User user = userRepository.findByEmail(username).get();
            if (user != null) return user;
            throw new UsernameNotFoundException("User " + username + " not found");
        };
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        return http
                .csrf().disable()
                .authorizeHttpRequests((authz) ->
                        authz
                                .mvcMatchers(AUTH_WHITELIST).permitAll()
                                .anyRequest()
                                .authenticated()
                )
                .cors().disable()
                .httpBasic(withDefaults())
//                .authorizeRequests()
//                .antMatchers("/users/**", "/users/me").access("hasRole('USER')")
////                .antMatchers("/login", "/register").permitAll()
//                .and()
//                .formLogin()
//                .loginPage("/login")
////                .defaultSuccessUrl("/profile")
//                .and()
                .build();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
