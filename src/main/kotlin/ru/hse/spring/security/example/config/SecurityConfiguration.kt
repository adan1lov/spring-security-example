package ru.hse.spring.security.example.config

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.http.HttpMethod
import org.springframework.security.config.annotation.authentication.configurers.provisioning.UserDetailsManagerConfigurer.UserDetailsBuilder
import org.springframework.security.config.annotation.web.builders.HttpSecurity
import org.springframework.security.core.userdetails.User
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder
import org.springframework.security.crypto.password.PasswordEncoder
import org.springframework.security.provisioning.InMemoryUserDetailsManager
import org.springframework.security.provisioning.UserDetailsManager
import org.springframework.security.web.SecurityFilterChain

@Configuration
class SecurityConfiguration {

    @Bean
    fun userDetailsManager(passwordEncoder: PasswordEncoder): UserDetailsManager {

        var user =
            User.builder()
                .username("user")
                .password(passwordEncoder.encode("password"))
                .roles("USER")
                .build()

        var admin =
            User.builder()
                .username("admin")
                .roles("ADMIN", "USER")
                .password(passwordEncoder.encode("admin"))
                .build()

        return InMemoryUserDetailsManager(user, admin)
    }

    @Bean
    fun securityFilterChain(http: HttpSecurity): SecurityFilterChain {
        return http.csrf { it.disable() }
            .authorizeHttpRequests {
                it.requestMatchers("/").permitAll()
                    .requestMatchers("/products/add").hasRole("ADMIN")
                    .requestMatchers("/products").hasRole("USER")
            }
//            .formLogin { it.permitAll() }
            .build()
    }

    @Bean
    fun passwordEncoder() = BCryptPasswordEncoder()
}