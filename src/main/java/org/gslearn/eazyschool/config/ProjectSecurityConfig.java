package org.gslearn.eazyschool.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class ProjectSecurityConfig {

    //Default configurations
//    @Bean
//    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//
//        // Permits all API Request inside the application
//        http.authorizeHttpRequests((authorizeRequests) -> ((AuthorizeHttpRequestsConfigurer.AuthorizedUrl) authorizeRequests.anyRequest()).permitAll())
//                .formLogin(Customizer.withDefaults())
//                .httpBasic(Customizer.withDefaults());
//
//        return http.build();
//    }
//AbstractHttpConfigurer::disable
    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
        http.csrf((csrf) -> csrf.ignoringRequestMatchers("/saveMsg")
                                .ignoringRequestMatchers("/public/**")
                                .ignoringRequestMatchers("/api/**")
                                .ignoringRequestMatchers("/data-api/**")
                                .ignoringRequestMatchers("/eazyschool/actuator/**")
//                        .ignoringRequestMatchers(PathRequest.toH2Console())
                )
                .authorizeHttpRequests((authorizeRequests) -> authorizeRequests.requestMatchers("/dashboard").authenticated()
                                .requestMatchers("/displayMessages/**").hasRole("ADMIN")
                                .requestMatchers("/closeMsg/**").hasRole("ADMIN")
                                .requestMatchers("/admin/**").hasRole("ADMIN")
                                .requestMatchers("/api/**").authenticated()
                                .requestMatchers("/data-api/**").authenticated()
                                .requestMatchers("/displayProfile").authenticated()
                                .requestMatchers("/eazyschool/actuator/**").authenticated()
                                .requestMatchers("/updateProfile").authenticated()
                                .requestMatchers("/student/**").hasRole("STUDENT")
                                /*.requestMatchers("/profile/**").permitAll()
                                .requestMatchers("/courseses/**").permitAll()
                                .requestMatchers("/contacts/**").permitAll()
                                 .requestMatchers("/data-api/**").permitAll()*/
                                .requestMatchers("/", "/home").permitAll()
                                .requestMatchers("/holidays/**").permitAll()
                                .requestMatchers("/contact").permitAll()
                                .requestMatchers("/saveMsg").permitAll()
                                .requestMatchers("/courses").permitAll()
                                .requestMatchers("/about").permitAll()
                                .requestMatchers("/assets/**").permitAll()
                                .requestMatchers("/login").permitAll()
                                .requestMatchers("/logout").permitAll()
                                .requestMatchers("/public/**").permitAll()
//                        .requestMatchers(PathRequest.toH2Console()).permitAll()
                )
                .formLogin((formLogin) ->
                        formLogin.loginPage("/login").defaultSuccessUrl("/dashboard").failureUrl("/login?error=true").permitAll())
                .logout((logout) -> logout.logoutSuccessUrl("/login?logout=true").invalidateHttpSession(true).permitAll())
                .httpBasic(Customizer.withDefaults());
//        http.headers().frameOptions().disable();
        //http.headers(h->h.frameOptions(HeadersConfigurer.FrameOptionsConfig::disable));
        return http.build();
    }

// Not needed any more as are using the username and pwd from the DB
//    @Bean
//    public InMemoryUserDetailsManager userDetailsManager() {
//        UserDetails adminUser = User.withUsername("admin").password("{noop}Saranya").roles("ADMIN", "USER").build();
//        UserDetails normalUser = User.withUsername("gomathi").password("{noop}Saranya").roles("USER").build();
//        return new InMemoryUserDetailsManager(adminUser, normalUser);
//    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
