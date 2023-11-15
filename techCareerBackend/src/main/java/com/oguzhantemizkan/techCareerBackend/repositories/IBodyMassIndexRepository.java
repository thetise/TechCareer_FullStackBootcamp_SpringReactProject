package com.oguzhantemizkan.techCareerBackend.repositories;

import com.oguzhantemizkan.techCareerBackend.entities.BodyMassIndex;
import org.springframework.data.jpa.repository.JpaRepository;

// Bu kod, Spring Data JPA'nın JpaRepository arabirimini genişleten ve BodyMassIndex varlığı için özel olarak
// oluşturulmuş bir JPA repository arabirimini temsil eder. Bu repository, BodyMassIndex varlıkları üzerinde standart CRUD
// işlemlerini gerçekleştirmek için kullanılabilir. JpaRepository'nin sunduğu hazır metotlar aracılığıyla veri tabanı işlemleri
// gerçekleştirilebilir. Örneğin, save, findById, findAll, deleteById, vb. gibi metodlar bu arayüz üzerinden kullanılabilir.
public interface IBodyMassIndexRepository extends JpaRepository<BodyMassIndex, Long> {

}
