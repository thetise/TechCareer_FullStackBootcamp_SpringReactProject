package com.oguzhantemizkan.techCareerBackend.services;

import com.oguzhantemizkan.techCareerBackend.entities.User;
import com.oguzhantemizkan.techCareerBackend.repositories.IUserRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    IUserRepository iUserRepository;

    public UserService(IUserRepository iUserRepository) {
        this.iUserRepository = iUserRepository;
    }

    // Bu metodun amacı, iUserRepository aracılığıyla veritabanındaki tüm kullanıcıları (User) almak ve bu
    // kullanıcıları bir liste olarak döndürmektir.
    public List<User> getAllUsers() {
        return iUserRepository.findAll();
    }

    // Bu metodun amacı, yeni bir kullanıcı oluşturmak için iUserRepository aracılığıyla veritabanına kullanıcı eklemektir.
    public User createUser(User newUser) {
        return iUserRepository.save(newUser);
    }

    // Bu metodun amacı, belirli bir kullanıcı ID'sine göre ilgili kullanıcıyı veritabanından almak ve bu
    // kullanıcıyı döndürmektir.
    public User getOneUser(Long userId) {
        return iUserRepository.findById(userId).orElse(null);
    }


    // Bu metodun amacı, belirli bir kullanıcı ID'sine sahip kullanıcıyı güncellemek için iUserRepository
    // aracılığıyla veritabanında arama yapmak ve güncellemeleri uygulamaktır.
    public User updateOneUser(Long userId, User updateUser) {
        Optional<User> user = iUserRepository.findById(userId);
        if(user.isPresent()){
            User foundUser = user.get();

            if(updateUser.getUserName() != null){
                foundUser.setUserName(updateUser.getUserName());
            }else{
                foundUser.setUserName(user.get().getUserName());
            }

            if(updateUser.getPassword() != null){
                foundUser.setPassword(updateUser.getPassword());
            }else{
                foundUser.setPassword(user.get().getPassword());
            }
            iUserRepository.save(foundUser);
            return foundUser;
        }else{
            return null;
        }
    }

    // Bu metodun amacı, belirli bir kullanıcı ID'sine sahip kullanıcıyı veritabanından silmek için iUserRepository
    // aracılığıyla ilgili işlemi gerçekleştirmektir.
    public void deleteUser(Long userId) {
        iUserRepository.deleteById(userId);
    }

    // Bu metodun amacı, kullanıcı adına göre belirli bir kullanıcıyı veritabanından almak için iUserRepository
    // aracılığıyla ilgili işlemi gerçekleştirmektir.
    public User getOneUserByName(String userName){
        return iUserRepository.findByUserName(userName);
    }
}
