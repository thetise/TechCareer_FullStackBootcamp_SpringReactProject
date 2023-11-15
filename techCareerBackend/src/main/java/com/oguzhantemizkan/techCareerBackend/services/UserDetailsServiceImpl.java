package com.oguzhantemizkan.techCareerBackend.services;

import com.oguzhantemizkan.techCareerBackend.repositories.IUserRepository;
import com.oguzhantemizkan.techCareerBackend.security.JwtUserDetails;
import com.oguzhantemizkan.techCareerBackend.entities.User;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    private IUserRepository iUserRepositories;

    public UserDetailsServiceImpl(IUserRepository iUserRepositories) {
        this.iUserRepositories = iUserRepositories;
    }

    // Bu metodun amacı, Spring Security tarafından kullanılan UserDetailsService arabirimini uygulamak için
    // loadUserByUsername metodunu sağlamaktır. Bu metot, verilen kullanıcı adına (username) göre bir kullanıcıyı
    // veritabanından bulur ve bu kullanıcıyı temsil eden bir UserDetails nesnesini döndürür. UserDetails
    // nesnesi, kullanıcının kimlik bilgilerini, rollerini ve yetkilendirmelerini sağlar ve bu bilgileri kullanarak
    // kullanıcı yetkilendirme kontrolleri yapılır.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = iUserRepositories.findByUserName(username);
        return JwtUserDetails.create(user);
    }

    // Bu metodun amacı, belirli bir kullanıcı kimliği (ID) ile veritabanından bir kullanıcıyı almak ve bu kullanıcıyı
    // temsil eden bir UserDetails nesnesini döndürmektir. Bu metodun kullanımı genellikle, kullanıcının kimliğine
    // göre belirli bir kullanıcıyı getirmek ve bu kullanıcıyı yetkilendirme ve kimlik doğrulama süreçlerinde kullanmaktır.
    public UserDetails loadUserById(Long id) {
        User user = iUserRepositories.findById(id).get();
        return JwtUserDetails.create(user);
    }

}