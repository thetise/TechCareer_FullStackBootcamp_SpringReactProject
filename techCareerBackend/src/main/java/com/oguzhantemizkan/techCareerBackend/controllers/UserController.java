package com.oguzhantemizkan.techCareerBackend.controllers;

import com.oguzhantemizkan.techCareerBackend.entities.User;
import com.oguzhantemizkan.techCareerBackend.services.UserService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/users")
public class UserController {

    UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    // Bu kod, istemcinin tüm kullanıcıları almak için bir GET isteği göndermesine olanak tanır. userService üzerinden
    // tüm kullanıcılar alınır ve bu kullanıcılar liste halinde istemciye döndürülür.
    @GetMapping
    public List<User> getAllUsers(){
        return userService.getAllUsers();
    }

    // Bu kod, istemcinin yeni bir kullanıcı oluşturmak için POST isteği göndermesine olanak tanır. İstek gövdesindeki
    // veri, User türünde olmalıdır ve bu bilgiyi kullanarak yeni bir kullanıcı oluşturulur ve oluşturulan kullanıcı
    // nesnesi istemciye gönderilir.
    @PostMapping
    public User createUser(@RequestBody User newUser){
        return userService.createUser(newUser);
    }

    // Bu kod, istemcinin belirli bir kullanıcı ID'sini içeren GET isteği göndermesine olanak tanır. userId'ye
    // bağlı olarak, ilgili kullanıcı bilgisi userService üzerinden alınır ve istemciye döndürülür.
    @GetMapping("/{userId}")
    public User getOneUser(@PathVariable Long userId){
        return userService.getOneUser(userId);
    }

    // Bu kod, istemcinin belirli bir kullanıcı ID'sini ve güncellenmiş kullanıcı bilgilerini içeren bir PUT isteği göndermesine
    // olanak tanır. userId'ye bağlı olarak, ilgili kullanıcı bilgisi userService üzerinden güncellenir ve
    // güncellenen bilgi istemciye döndürülür.
    @PutMapping("/{userId}")
    public User updateOneUser(@PathVariable Long userId, @RequestBody User updateUser){
        return userService.updateOneUser(userId, updateUser);
    }

    // Bu kod, istemcinin belirli bir kullanıcı ID'sini içeren DELETE isteği göndermesine olanak tanır. userId'ye
    // bağlı olarak, ilgili kullanıcı userService üzerinden silinir.
    @DeleteMapping("/{userId}")
    public void deleteUser(@PathVariable Long userId){
        userService.deleteUser(userId);
    }
}