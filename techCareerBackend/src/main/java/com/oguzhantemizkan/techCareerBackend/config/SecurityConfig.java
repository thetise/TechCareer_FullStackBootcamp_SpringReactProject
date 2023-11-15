package com.oguzhantemizkan.techCareerBackend.config;


import com.oguzhantemizkan.techCareerBackend.security.JwtAuthenticationEntryPoint;
import com.oguzhantemizkan.techCareerBackend.security.JwtAuthenticationFilter;
import com.oguzhantemizkan.techCareerBackend.services.UserDetailsServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig{

    private UserDetailsServiceImpl userDetailsService;

    private JwtAuthenticationEntryPoint handler;


    public SecurityConfig(UserDetailsServiceImpl userDetailsService, JwtAuthenticationEntryPoint handler) {
        this.userDetailsService = userDetailsService;
        this.handler = handler;
    }

    // JwtAuthenticationFilter genellikle Spring Security konfigürasyonunda kullanılır ve gelen HTTP isteklerini
    // kontrol eder, özellikle de JWT tabanlı kimlik doğrulama ve yetkilendirme işlemlerini gerçekleştirir.
    // Bu filtre, kullanıcının JWT token'ını kontrol eder, geçerli ve doğru ise kullanıcının kimlik bilgilerini
    // belirler ve güvenlik bağlamında uygun ayarlamaları yapar.
    @Bean
    public JwtAuthenticationFilter jwtAuthenticationFilter(){
        return new JwtAuthenticationFilter();
    }

    // PasswordEncoder, genellikle kullanıcı şifrelerini güvenli bir şekilde saklamak ve karşılaştırmak için kullanılır.
    // BCryptPasswordEncoder özellikle güvenli bir hash algoritması olan BCrypt'i kullanarak şifreleri hash'ler.
    // Bu, şifrelerin veritabanında güvenli bir şekilde saklanmasını sağlar. PasswordEncoder, genellikle kullanıcıların
    // şifrelerini doğrulama işlemlerinde veya yeni bir kullanıcı oluşturulurken kullanılır.
    @Bean
    public PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    // AuthenticationManager genellikle kimlik doğrulama işlemlerini yönetir ve Spring Security'nin bir parçası
    // olarak kullanıcıların kimlik bilgilerini doğrular.
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration) throws Exception{
        return authenticationConfiguration.getAuthenticationManager();
    }

    // Bu metodun amacı, CORS (Cross-Origin Resource Sharing) filtresini oluşturarak ve yapılandırarak Spring
    // konteynerine eklemektir. CORS, web tarayıcıları arasında güvenli olmayan kaynak paylaşımını kontrol etmek
    // için kullanılır. Bu filtre, HTTP taleplerinin hangi kaynaklara erişebileceğini belirleyen bir politika sağlar.
    @Bean
    public CorsFilter corsFilter(){
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        CorsConfiguration config = new CorsConfiguration();

        config.setAllowCredentials(true);
        config.addAllowedOrigin("*");
        config.addAllowedHeader("*");
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("PUT");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        source.registerCorsConfiguration("/**", config);
        return new CorsFilter(source);
    }

    // Bu kod, Spring Security'nin güvenlik yapılandırmasını sağlamak için kullanılır. SecurityFilterChain
    // bean'ini oluşturarak ve yönetimini gerçekleştirerek Spring konteynerine ekler.
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .cors()
                .and()
                .csrf().disable()
                .exceptionHandling().authenticationEntryPoint(handler).and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                .authorizeHttpRequests()
                .requestMatchers(HttpMethod.GET, "/bmis")
                .permitAll()
                .requestMatchers("/auth/**")
                .permitAll()
                .anyRequest().authenticated();

        httpSecurity.addFilterBefore(jwtAuthenticationFilter(), UsernamePasswordAuthenticationFilter.class);
        return httpSecurity.build();
    }
}