package com.paulina.backenduserservice.controllers;
 
import com.paulina.backenduserservice.models.request.UserDetailsRequestModel;
import com.paulina.backenduserservice.models.responses.UserRest;
import com.paulina.backenduserservice.repositories.UserRepository;
import com.paulina.backenduserservice.service.UserService;
import com.paulina.backenduserservice.shared.dtos.UserDto;
import org.springframework.beans.BeanUtils;
import org.springframework.web.bind.annotation.*;


@RestController
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    public UserController(UserRepository userRepository, UserService userService) {
        this.userRepository = userRepository;
        this.userService = userService;
    }

    /*@GetMapping //("/users")
    public String getUsers(){
        return "user";
    	//return userRepository.findAll();
    }*/

    //    @GetMapping("/users/{id}")
    //    public UserDetailsRequestModel getUser(@PathVariable String id){
    //       return userRepository.findById(id).orElseThrow(() -> new RuntimeException());
    //   }

    @PostMapping("/users")
    public UserRest post(@RequestBody UserDetailsRequestModel userDetails){
        UserRest userToReturn = new UserRest();
        UserDto userDto = new UserDto();

        BeanUtils.copyProperties(userDetails, userDto); // copia as informações do userDetail pra userDto
        
        UserDto createdUser = userService.createUser(userDto);

        BeanUtils.copyProperties(createdUser, userToReturn);


        return userToReturn;
    }

    //   @PutMapping("/users/{id}")
    //   public UserDetailsRequestModel updateUser(@RequestBody UserDetailsRequestModel updatedUser, @PathVariable String id){
    //      if(!userExists(id)){
    //          throw new RuntimeException();
    //      }

    //      return userRepository.save(updatedUser);
    //  }

    //   @DeleteMapping("/users/{id}")
    //   public void delete(@PathVariable String id){
    //      if(!userExists(id)){
    //          throw new RuntimeException();
    //      }

    //      userRepository.deleteById(id);
    //  }
    
    @DeleteMapping("/users")
     public void deleteAll(){
         userRepository.deleteAll();
     }

    private boolean userExists(final String id) {
        return userRepository.existsById(id);
    }


}
