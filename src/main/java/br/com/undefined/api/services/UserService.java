package br.com.undefined.api.services;

import br.com.undefined.api.dto.UserDTO;
import br.com.undefined.api.entities.User;
import br.com.undefined.api.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository<User> userRepo;

    public List<User> findAll(){
        return userRepo.findAll();
    }

    public User findById(Long id) {
        Optional<User> user = userRepo.findById(id);
        return user.get();
    }

    public void delete(Long id) {
        userRepo.deleteById(id);
    }

    public User insert(User user) {
        return userRepo.save(user);
    }


    /*public User update(User user) {
        User usr = findById(user.getId());
        dataUpdate(usr, user);
        return userRepo.save(usr);
    }

    public void dataUpdate(User userToAtt, User user) {
        userToAtt.setUserName(user.getUserName());
        userToAtt.setEmail(user.getEmail());
    }*/

    public User fromDTO(UserDTO userDTO){
        return new User(userDTO.getUserName(), userDTO.getEmail(), userDTO.getPassword());
    }

}
