package com.example.trenAlSur.service;

import com.example.trenAlSur.dto.LoginUserDto;
import com.example.trenAlSur.dto.UserDto;
import com.example.trenAlSur.model.User;
import com.example.trenAlSur.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;


@Service
public class UserService {


    @Autowired
    private UserRepository userRepository;


    public UserDto createUser (UserDto userDto){
        User user = new User();
        user.setNombre(userDto.getNombre());
        user.setApellido(userDto.getApellido());
        user.setFenaci(userDto.getFenaci());
        user.setPais(userDto.getPais());
        user.setEmail(userDto.getEmail());
        user.setUsuario(userDto.getUsuario());
        user.setContraseña(userDto.getContraseña());
        User created = userRepository.save(user);

        return UserDto.builder()
                .id(created.getId())
                .nombre(created.getNombre())
                .apellido(created.getApellido())
                .fenaci(created.getFenaci())
                .pais(created.getPais())
                .email(created.getEmail())
                .usuario(created.getUsuario())
                .contraseña(created.getContraseña())
                .build();

    }

    public List<User> getAllUsers (){
        return userRepository.findAll();
    }

    public Optional<User> findUserById(Integer id) {
        return userRepository.findById(id);
    }

    public void deleteUserById(Integer id){
        userRepository.deleteById(id);
    }

    public UserDto updateUser(Integer id, UserDto userDto) {
        Optional<User> userOptional = userRepository.findById(id);

        if (userOptional.isPresent()) {
            User user = userOptional.get();
            user.setNombre(userDto.getNombre());
            user.setApellido(userDto.getApellido());
            user.setFenaci(userDto.getFenaci());
            user.setPais(userDto.getPais());
            user.setEmail(userDto.getEmail());
            user.setUsuario(userDto.getUsuario());
            user.setContraseña(userDto.getContraseña());

            User updated = userRepository.save(user);

            return UserDto.builder()
                    .id(updated.getId())
                    .nombre(updated.getNombre())
                    .apellido(updated.getApellido())
                    .fenaci(updated.getFenaci())
                    .pais(updated.getPais())
                    .email(updated.getEmail())
                    .usuario(updated.getUsuario())
                    .contraseña(updated.getContraseña())
                    .build();
        } else {
            throw new RuntimeException("User not found with id " + id);
        }
    }

    public UserDto login(LoginUserDto loginUserDto) {
        Optional<User> byUsuario = userRepository.findByUsuario(loginUserDto.getUsuario());
        if (byUsuario.isPresent()) {
            User user = byUsuario.get();
            if (loginUserDto.getContraseña().equals(user.getContraseña())){
                return UserDto.builder()
                        .id(user.getId())
                        .nombre(user.getNombre())
                        .apellido(user.getApellido())
                        .fenaci(user.getFenaci())
                        .pais(user.getPais())
                        .email(user.getEmail())
                        .usuario(user.getUsuario())
                        .contraseña(user.getContraseña())
                        .build();
            }
        }
        return null;
    }

}
