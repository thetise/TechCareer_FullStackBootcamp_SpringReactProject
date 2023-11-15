package com.oguzhantemizkan.techCareerBackend.security;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import java.io.IOException;

// Bu Java sınıfı, Spring Security tarafından sağlanan AuthenticationEntryPoint arabirimini uygulayan ve yetkilendirme
// başarısız olduğunda gerçekleşecek olayları yöneten bir bileşen (component) olarak tasarlanmıştır. Bu sınıf, özellikle
// Spring Security yapılandırmasında belirli bir kullanıcının erişim yetkisi olmadığında veya yetkilendirme başarısız
// olduğunda tetiklenen bir olayı ele alır. Örneğin, bu sınıf, bir JWT tabanlı yetkilendirme senaryosunda, geçerli
// bir JWT token'ı olmayan bir kullanıcının erişim talebinde bulunması durumunda çalışabilir.

@Component
public class JwtAuthenticationEntryPoint implements AuthenticationEntryPoint {

    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response,
                         AuthenticationException authException) throws IOException, ServletException {
        response.sendError(HttpServletResponse.SC_UNAUTHORIZED, authException.getMessage());
    }
}
