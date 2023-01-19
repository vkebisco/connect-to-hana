package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Objects;

@RestController
@RequestMapping(value = "/user")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${apikey}")
    private String apiKey;

    /**
     * retorna todos os users da base
     * @return lista de users
     */
    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity getAll(@RequestHeader(value="Authorization") String key){
        if (!Objects.equals(key, apiKey)){
            return unauthorized();
        }
        return ResponseEntity.ok(userRepository.findAll());
    }

    /**
     * insere um usuário
     * @param user
     * @return response
     */
    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity insert(@RequestBody User user, @RequestHeader(value="Authorization") String key) {
        if (!Objects.equals(key, apiKey)){
            return unauthorized();
        }
       userRepository.save(user);
       return ResponseEntity.ok().build();
    }

    /**
     * atualiza um usuário
     * @param user
     * @return respose
     */
    @RequestMapping(method = RequestMethod.PUT)
    public ResponseEntity update(@RequestBody User user, @RequestHeader(value="Authorization") String key){
        if (!Objects.equals(key, apiKey)){
            return unauthorized();
        }
        userRepository.save(user);
        return ResponseEntity.ok().build();
    }

    /**
     * delete um usuário
     * @param id
     * @return response
     */
    @RequestMapping(method = RequestMethod.DELETE)
    public ResponseEntity insert(@RequestParam("id") int id, @RequestHeader(value="Authorization") String key) {
        if (!Objects.equals(key, apiKey)){
            return unauthorized();
        }
        userRepository.deleteById(id);
        return ResponseEntity.ok().build();
    }

    private ResponseEntity unauthorized(){
        return ResponseEntity.status(401).build();
    }
}
