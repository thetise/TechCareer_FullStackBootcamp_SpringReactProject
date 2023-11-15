package com.oguzhantemizkan.techCareerBackend.security;

import io.jsonwebtoken.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class JwtTokenProvider {

    // Bu kod, Spring framework'ün @Value anotasyonunu kullanarak bir sınıf alanına dış konfigürasyon dosyalarından
    // veya başka kaynaklardan değerleri enjekte etmeyi sağlar. Bu sayede değerlerin uygulama davranışını dışarıdan
    // yapılandırmak daha esnek ve kolay hale gelir.
    @Value("${techCareer.app.secret}")
    private String APP_SECRET;

    @Value("${techCareer.expires.in}")
    private long EXPIRES_IN;


    // Bu metodun amacı, bir kullanıcının kimlik doğrulama bilgilerini içeren bir Authentication nesnesinden
    // yola çıkarak, belirli bir süre içinde geçerli olan bir JWT oluşturmak ve bu JWT'yi döndürmektir.
    // Oluşturulan JWT, genellikle kullanıcının bir oturum açma işlemi sonrasında istemci tarafında saklanır ve
    // yetkilendirme işlemlerinde kullanılır.
    public String generateJwtToken(Authentication auth) {
        JwtUserDetails userDetails = (JwtUserDetails) auth.getPrincipal();
        Date expireDate = new Date(new Date().getTime() + EXPIRES_IN);
        return Jwts.builder().setSubject(Long.toString(userDetails.getId()))
                .setIssuedAt(new Date()).setExpiration(expireDate)
                .signWith(SignatureAlgorithm.HS512, APP_SECRET).compact();
    }

    // Bu metodun amacı, bir JWT içindeki kullanıcı ID'sini çözerek, bu ID'yi kullanarak kullanıcıya özgü
    // işlemleri gerçekleştirmektir. Özellikle, JWT tabanlı yetkilendirme uygulamalarında, kullanıcının kimliği
    // bu şekilde saklanarak ve taşınarak güvenli bir şekilde yetkilendirme işlemleri gerçekleştirilir.
    Long getUserIdFromJwt(String token) {
        Claims claims = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody();
        return Long.parseLong(claims.getSubject());
    }

    // Bu metod, bir JWT'nin geçerliliğini doğrulamak için kullanılır.
    boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token);
            return !isTokenExpired(token);
        } catch (SignatureException e) {
            return false;
        } catch (MalformedJwtException e) {
            return false;
        } catch (ExpiredJwtException e) {
            return false;
        } catch (UnsupportedJwtException e) {
            return false;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }

    // Bu metod, bir JWT'nin süresinin dolup dolmadığını kontrol etmek için kullanılır. Eğer bir JWT'nin süresi
    // dolmuşsa, bu durumda token geçerli sayılmaz ve kullanıcı yetkilendirilmez. Bu şekilde, token'ın süresi dolup
    // dolmadığı kontrol edilerek, güvenlik ve yetkilendirme süreçleri yönetilebilir.
    private boolean isTokenExpired(String token) {
        Date expiration = Jwts.parser().setSigningKey(APP_SECRET).parseClaimsJws(token).getBody().getExpiration();
        return expiration.before(new Date());
    }

}