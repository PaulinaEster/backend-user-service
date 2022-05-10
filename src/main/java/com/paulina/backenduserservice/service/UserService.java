package com.paulina.backenduserservice.service;

import com.paulina.backenduserservice.entities.UserEntity;
import com.paulina.backenduserservice.repositories.UserRepository;
import com.paulina.backenduserservice.shared.dtos.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList; 
import java.util.UUID;

@Service
@Component
public class UserService implements UserServiceInterface{

    private final UserRepository userRepository;

	
	private final BCryptPasswordEncoder bCryptPasswordEncoder;
	

    public UserService(UserRepository userRepository, BCryptPasswordEncoder bCryptPasswordEncoder) {
        this.userRepository = userRepository;
        this.bCryptPasswordEncoder = bCryptPasswordEncoder;
    }

    public UserDto createUser(UserDto userDto) {
        if(userRepository.findByEmail(userDto.getEmail()) != null) throw new RuntimeException("O email já esta sendo usado.");

        UserEntity userEntity = new UserEntity();
        BeanUtils.copyProperties(userDto, userEntity);

        UUID userId = UUID.randomUUID();
        userEntity.setUserId(userId.toString());

        userEntity.setEncryptedPassword(bCryptPasswordEncoder.encode(userDto.getPassword()));
        
        UserEntity storedToReturn = userRepository.save(userEntity);

        UserDto userToReturn = new UserDto();
        BeanUtils.copyProperties(storedToReturn, userToReturn);

        return userToReturn;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        UserEntity user = userRepository.findByEmail(email);
        if(user == null) {
            throw new UsernameNotFoundException("Usuario não encontrado");
        }   
        
        return new User(user.getEmail(), user.getEncryptedPassword(), new ArrayList<>());
    }
}






