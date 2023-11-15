package com.oguzhantemizkan.techCareerBackend.responses;

import lombok.Data;

// Bu Java sınıfı, bir yetkilendirme (authentication) işleminden kaynaklanan yanıt verilerini taşıyan bir veri
// transfer nesnesini (DTO - Data Transfer Object) temsil eder. Bu sınıf, bir yetkilendirme işlemi sonucunda istemciye
// dönüş yapmak için kullanılır. Örneğin, bir kullanıcının başarılı bir şekilde giriş yapmasından sonra, sunucu
// tarafından oluşturulan bir yetkilendirme yanıtında bu sınıftan bir nesne oluşturulur ve bu nesne istemciye gönderilir.
@Data
public class AuthResponse {

    String message;
    Long userId;
}


