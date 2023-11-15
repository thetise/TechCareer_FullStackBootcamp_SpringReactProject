package com.oguzhantemizkan.techCareerBackend.security;


import com.oguzhantemizkan.techCareerBackend.services.UserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

public class JwtAuthenticationFilter extends OncePerRequestFilter{

    // Bu kod, bir Spring bileşeni (component) veya servis sınıfının içindeki alanları @Autowired anotasyonu
    // kullanarak bağımlılıklarını enjekte ettiğini gösterir.
    @Autowired
    JwtTokenProvider jwtTokenProvider;

    @Autowired
    UserDetailsServiceImpl userDetailsService;


    // Bu kod, bir Spring Security filtresini temsil eder ve genellikle JWT (JSON Web Token) tabanlı yetkilendirme
    // uygulamalarında kullanılır. Bu filtre, gelen HTTP isteklerini kontrol eder, eğer geçerli bir JWT varsa, bu
    // JWT'yi doğrular ve bu JWT'den elde edilen bilgileri kullanarak kullanıcıyı yetkilendirir.
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain)
            throws ServletException, IOException {
        try {
            String jwtToken = extractJwtFromRequest(request);
            if(StringUtils.hasText(jwtToken) && jwtTokenProvider.validateToken(jwtToken)) {
                Long id = jwtTokenProvider.getUserIdFromJwt(jwtToken);
                UserDetails user = userDetailsService.loadUserById(id);
                if(user != null) {
                    UsernamePasswordAuthenticationToken auth = new UsernamePasswordAuthenticationToken(user, null, user.getAuthorities());
                    auth.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    SecurityContextHolder.getContext().setAuthentication(auth);
                }
            }
        } catch(Exception e) {
            return;
        }
        filterChain.doFilter(request, response);
    }

    // Bu metod, genellikle Spring Security filtrelerinde veya interceptor'larında kullanılır. Özellikle, gelen
    // HTTP isteklerinin güvenlikle ilgili bilgilerini (JWT gibi) çıkartmak ve bu bilgileri kullanarak yetkilendirme
    // işlemlerini gerçekleştirmek için kullanılır.
    private String extractJwtFromRequest(HttpServletRequest request) {
        String bearer = request.getHeader("Authorization");
        if(StringUtils.hasText(bearer) && bearer.startsWith("Bearer "))
            return bearer.substring("Bearer".length() + 1);
        return null;
    }
}
