package com.capstone.tech;

import com.capstone.tech.services.UserDetailsLoader;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private UserDetailsLoader usersLoader;

    public SecurityConfiguration(UserDetailsLoader usersLoader) {
        this.usersLoader = usersLoader;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(usersLoader) // How to find users by their username
                .passwordEncoder(passwordEncoder()) // How to encode and verify passwords
        ;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                /* Login configuration */
                .formLogin()
                .loginPage("/login")
                .defaultSuccessUrl("/posts") // user's home page, it can be any URL
                .permitAll() // Anyone can go to the login page

                /* Logout configuration */
                .and()
                .logout()
                .logoutSuccessUrl("/login?logout") // append a query string value

                /* Pages that can be viewed without having to log in */
                .and()
                .authorizeRequests()
                .antMatchers("/", "/home") // anyone can see the home and the ads pages
                .permitAll()

                /* Pages that require authentication */
                .and()
                .authorizeRequests()
                .antMatchers(
//                             ***HomeController***
                        "/ads/create", // only authenticated users can create ads
                        "/ads/{id}/edit", // only authenticated users can edit ads
                        "/resources", // only authenticated users can navigate to resources
                        "resources", // only authenticated users can view resources page
                        "/availability", // only authenticated users can navigate to availability page
                        "users/availability", // only authenticated users can view availability page

//                           ***PostController***
                        "/posts", // only authenticated users can view all posts
                        "posts/all-posts", // only authenticated users can view all posts
                        "/posts/{id}", // only authenticated users can navigate to users by id
                        "posts/show", // only authenticated users can view individual posts
                        "/posts/create", // only authenticated users can navigate to create posts
                        "posts/create", // only authenticated users can create posts
                        "/posts/{id}/edit", // only authenticated users can navigate to edit their own posts
                        "posts/edit", // only authenticated users can edit their own posts
                        "/posts/delete", // only authenticated users delete their own posts
//
//                             ***UserController***
//                                    --Users--
                        "/users", // only authenticated users can navigate to all users page
                        "users/all-users", // only authenticated users can view all users
                        "/users/{id}", // only authenticated users can navigate to find individual user
                        "users/show-user", // only authenticated users can navigate to individual users page
//
//                                        --UserDetails--
                        "/users/details/create", // only authenticated users can navigate to details page
                        "users/details-create", // only authenticated users can create details
                        "/users/{id}/details/edit", // only authenticated users can navigate to edit details page
                        "users/details-edit", // only authenticated users can edit details
                        "/users/{id}/details/delete" // only authenticated users can delete their own details
                )
                .authenticated()
        ;
    }
}