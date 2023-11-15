package com.oguzhantemizkan.techCareerBackend.services;

import com.oguzhantemizkan.techCareerBackend.entities.BodyMassIndex;
import com.oguzhantemizkan.techCareerBackend.entities.User;
import com.oguzhantemizkan.techCareerBackend.repositories.IBodyMassIndexRepository;
import com.oguzhantemizkan.techCareerBackend.requests.BodyMassIndexCreateRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BodyMassIndexService {

    IBodyMassIndexRepository iBodyMassIndexRepository;
    UserService userService;

    public BodyMassIndexService(IBodyMassIndexRepository iBodyMassIndexRepository, UserService userService) {
        this.iBodyMassIndexRepository = iBodyMassIndexRepository;
        this.userService = userService;
    }

    // Bu metodun amacı, IBodyMassIndexRepository aracılığıyla tüm vücut kitle indeksi (BMI) kayıtlarını
    // veritabanından çekmek ve bunları bir liste olarak döndürmektir.
    public List<BodyMassIndex> getAllBodyMassIndex() {
        return iBodyMassIndexRepository.findAll();
    }

    // Bu metodun amacı, yeni bir vücut kitle indeksi (BMI) kaydı oluşturmak için gerekli işlemleri gerçekleştirmektir.
    public BodyMassIndex createBodyMassIndex(BodyMassIndexCreateRequest newBodyMassIndexRequest) {
        User user = userService.getOneUser(newBodyMassIndexRequest.getUserId());

        if(user == null){
            return null;
        }

        BodyMassIndex toSave = new BodyMassIndex();
        toSave.setId(newBodyMassIndexRequest.getId());
        toSave.setUser(user);
        toSave.setHeight(newBodyMassIndexRequest.getHeight());
        toSave.setWeight(newBodyMassIndexRequest.getWeight());
        toSave.setBodyMassIndexValue(newBodyMassIndexRequest.getWeight() / Math.pow(newBodyMassIndexRequest.getHeight(), 2));

        if(toSave.getBodyMassIndexValue() < 18.50){
            toSave.setResult("Düşük Kilolu");
        }else if(toSave.getBodyMassIndexValue() >= 18.50 && toSave.getBodyMassIndexValue() <= 24.99){
            toSave.setResult("Normal Kilolu");
        }else if(toSave.getBodyMassIndexValue() >= 25.00 && toSave.getBodyMassIndexValue() <= 29.99){
            toSave.setResult("Fazla Kilolu");
        }else{
            toSave.setResult("Obez");
        }
        return iBodyMassIndexRepository.save(toSave);
    }


    // Bu metodun amacı, verilen bir vücut kitle indeksi (BMI) ID'sine göre ilgili BMI kaydını veritabanından
    // almak ve bu kaydı döndürmektir.
    public BodyMassIndex getOneBodyMassIndex(Long bmiId) {
        return iBodyMassIndexRepository.findById(bmiId).orElse(null);
    }
}
