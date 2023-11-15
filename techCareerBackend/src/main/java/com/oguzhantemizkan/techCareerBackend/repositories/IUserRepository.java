package com.oguzhantemizkan.techCareerBackend.repositories;

import com.oguzhantemizkan.techCareerBackend.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Bu kod, Spring Data JPA'nın JpaRepository arabirimini genişleten ve User varlığı için özel olarak oluşturulmuş
// bir JPA repository arabirimini temsil eder. Ayrıca, findByUserName adında özel bir sorgu metodu tanımlanmıştır.

//User findByUserName(String uname): Bu metod, kullanıcı adına (userName) göre bir kullanıcıyı bulmak için kullanılır.
public interface IUserRepository extends JpaRepository<User, Long> {
    User findByUserName(String uname);
}
