package gr.hua.ds.springboot1.service;
import gr.hua.ds.springboot1.entity.User;
import gr.hua.ds.springboot1.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.util.ArrayList;
import java.util.List;


@Service
public class UserService {

    private UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    // get all users that are buyers.
    public List<User> getBuyers() {
        List<User> usrs =userRepository.findAll();
        List <User> buyers =new ArrayList<>();

        for(int i=0;i<usrs.size();i++){
            if (usrs.get(i).getAuthority().equals("ROLE_BUYER")) {
                buyers.add(usrs.get(i));
            }
        }
        return buyers;
    }

    // get all users that are sellers.
    public List<User> getSellers() {
        List<User> usrs =userRepository.findAll();
        List <User> sellers =new ArrayList<>();

        for(int i=0;i<usrs.size();i++){
            if (usrs.get(i).getAuthority().equals("ROLE_SELLER")) {
                sellers.add(usrs.get(i));
            }
        }
        return sellers;
    }

    // get a seller user by his username.
    public User getSellerByUsername(String username) {
        List<User> listOfSellers = getSellers();
        for(int i=0;i<listOfSellers.size();i++){
            if(listOfSellers.get(i).getUsername().equals(username)) {
                return listOfSellers.get(i);
            }
        }
        return null;
    }

    // get a user by his username.
    public User getUserByUsername(String username) {
        List<User> listOfUsers =userRepository.findAll();
        for(int i=0;i<listOfUsers.size();i++){
            if(listOfUsers.get(i).getUsername().equals(username)) {
                return listOfUsers.get(i);
            }
        }
        return null;
    }

    // get a user by id
    public User getUser(int id) {
        return userRepository.getById(id);
    }

    // save a user
    public User saveUser(User usr) {
        return userRepository.save(usr);
    }

    // remove a user
    public void removeUser(User usr) {
        userRepository.delete(usr);
    }

    // remove a user by id
    public void removeUserById(int id) {
        userRepository.deleteById(id);
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

}


