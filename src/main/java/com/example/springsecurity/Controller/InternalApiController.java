package com.example.springsecurity.Controller;



import com.example.springsecurity.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/internal")
public class InternalApiController {


    @Autowired
    private UserService userService;

    @PutMapping("/make-admin/{email}")
    public ResponseEntity<?> makeAdmin(@PathVariable String email){
        userService.makeAdmin(email);
        return new ResponseEntity<>("You Set new Admin",HttpStatus.OK);
    }

    @PutMapping ("/make-user/{email}")
    public ResponseEntity<?> makeUser(@PathVariable String email){
        userService.MakeUserRole(email);
        return new ResponseEntity<>("Changed to User",HttpStatus.OK);
    }
}
