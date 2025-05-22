package br.com.phlimadev.simplified_picpay.user;

import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void save(UserDTO userDTO) {
        UserModel userModel = new UserModel(
                null, userDTO.firstName(), userDTO.lastName(), userDTO.document(), userDTO.email(),
                userDTO.password(), userDTO.balance(), userDTO.userType()
        );
        UserModel userSaved = userRepository.save(userModel);
    }

    public List<UserDTO> findAll() {
        return userRepository.findAll().stream().map(UserDTO::new).toList();
    }
}