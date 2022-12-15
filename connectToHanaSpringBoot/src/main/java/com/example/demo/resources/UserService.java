package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value = "/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    /**
     * retorna todos os users da base
     * @return lista de users
     */
    @RequestMapping(method = RequestMethod.GET)
    public List<User> getAll(){
        return userRepository.findAll();
    }

    /**
     * insere um usuário
     * @param user
     * @return response
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity insert(@RequestBody User user){
       userRepository.save(user);
       return ResponseEntity.ok().build();
    }

    /**
     * atualiza um usuário
     * @param user
     * @return respose
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody User user){
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    /**
     * delete um usuário
     * @param id
     * @return response
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity insert(@RequestParam("id") int id){
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }
}
