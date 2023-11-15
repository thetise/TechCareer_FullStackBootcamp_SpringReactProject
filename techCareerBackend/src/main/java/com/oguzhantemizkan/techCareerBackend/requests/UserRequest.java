package com.oguzhantemizkan.techCareerBackend.requests;

import lombok.Data;

// Bu Java sınıfı, kullanıcı oluşturma veya güncelleme gibi işlemler için HTTP isteği gövdelerini temsil eden bir
// veri transfer nesnesidir (DTO - Data Transfer Object). Bu sınıf, HTTP isteği gövdelerindeki verileri almak ve bu
// verileri kullanarak bir kullanıcı oluşturmak veya giriş yapmak için kullanılabilir. Örneğin, bir istemci yeni
// bir kullanıcı oluşturmak için bir POST isteği gönderirken, bu sınıftan bir nesne oluşturup bu nesneyi kullanarak
// ilgili bilgileri taşıyabilir.
@Data
public class UserRequest {
    String userName;
    String password;
}
