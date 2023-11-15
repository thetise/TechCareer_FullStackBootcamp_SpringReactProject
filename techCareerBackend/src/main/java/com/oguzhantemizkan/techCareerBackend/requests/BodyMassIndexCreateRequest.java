package com.oguzhantemizkan.techCareerBackend.requests;

import lombok.Data;

// Bu Java sınıfı, bir vücut kitle indeksi (BMI) oluşturmak için kullanılacak olan HTTP isteği gövdelerini temsil
// eden bir veri transfer nesnesidir (DTO - Data Transfer Object). Bu sınıf, HTTP POST isteği gövdelerindeki verileri
// almak ve bu verileri kullanarak bir vücut kitle indeksi oluşturmak için kullanılır. Örneğin, bir istemci yeni
// bir BMI oluşturmak için bir POST isteği gönderirken, bu sınıftan bir nesne oluşturup bu nesneyi kullanarak ilgili
// bilgileri taşır.
@Data
public class BodyMassIndexCreateRequest {

    Long id;
    Long userId;
    double height;
    double weight;
}
