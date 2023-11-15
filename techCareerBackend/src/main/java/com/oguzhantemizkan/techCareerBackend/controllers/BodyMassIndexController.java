package com.oguzhantemizkan.techCareerBackend.controllers;

import com.oguzhantemizkan.techCareerBackend.entities.BodyMassIndex;
import com.oguzhantemizkan.techCareerBackend.requests.BodyMassIndexCreateRequest;
import com.oguzhantemizkan.techCareerBackend.services.BodyMassIndexService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bmis")
public class BodyMassIndexController {

    BodyMassIndexService bodyMassIndexService;

    public BodyMassIndexController(BodyMassIndexService bodyMassIndexService) {
        this.bodyMassIndexService = bodyMassIndexService;
    }

    // Bu kod, bir istemci tarafından yapılan HTTP GET isteği üzerine, tüm vücut kitle indeksi verilerini almak ve
    // bu verileri liste halinde yanıt olarak dönmek için kullanılır.
    @GetMapping
    public List<BodyMassIndex> getAllBodyMassIndex(){
        return bodyMassIndexService.getAllBodyMassIndex();
    }

    // Bu kod, istemcinin yeni bir vücut kitle indeksi oluşturmak için POST isteği göndermesine olanak tanır.
    // İstek gövdesindeki veri, BodyMassIndexCreateRequest türünde olmalıdır ve bu bilgiyi kullanarak yeni bir
    // vücut kitle indeksi oluşturulur ve oluşturulan indeksi içeren yanıt istemciye gönderilir.
    @PostMapping
    public BodyMassIndex createBodyMassIndex(@RequestBody BodyMassIndexCreateRequest newBodyMassIndexRequest){
        return bodyMassIndexService.createBodyMassIndex(newBodyMassIndexRequest);
    }


    // Bu kod, istemcinin belirli bir vücut kitle indeksi ID'sini içeren GET isteği göndermesine olanak tanır.
    // bmiId'ye bağlı olarak, ilgili vücut kitle indeksi bilgisi bodyMassIndexService üzerinden alınır ve istemciye döndürülür.
    @GetMapping("/{bmiId}")
    public BodyMassIndex getOneBodyMassIndex(@PathVariable Long bmiId){
        return bodyMassIndexService.getOneBodyMassIndex(bmiId);
    }
}
