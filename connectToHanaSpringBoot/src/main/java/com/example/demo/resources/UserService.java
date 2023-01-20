package com.example.demo.resources;

import com.example.demo.domain.User;
import com.example.demo.repo.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.http.MediaType;
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

        //pega todos os users
        var findAll = userRepository.findAll();

        return ResponseEntity.ok(findAll);
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

        //insere user (se não possuir campo ID)
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

        //faz update do user (se possuir campo ID)
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

        //deleta user por ID
        try {
            userRepository.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            return ResponseEntity.status(400)
                    .contentType(MediaType.APPLICATION_JSON).body("{\"mensagem\": \"id inexistente\"}");
        }

        return ResponseEntity.ok().build();
    }

    private ResponseEntity unauthorized(){
        return ResponseEntity.status(401).build();
    }


}
