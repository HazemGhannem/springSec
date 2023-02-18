package com.example.springsecurity.Controller;

import com.example.springsecurity.Entity.*;
import com.example.springsecurity.service.AuthenticationService;

import com.example.springsecurity.service.ChatService;
import com.example.springsecurity.service.ITache;
import com.example.springsecurity.service.UserService;
import com.example.springsecurity.util.FileUploadUtil;
import com.example.springsecurity.util.SmsTwillio;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.json.JsonParseException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessageHeaderAccessor;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletContext;
import javax.websocket.server.PathParam;
import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.Optional;

import org.apache.commons.io.FilenameUtils;


@RestController
@RequestMapping("/api/authentication")
@CrossOrigin("*")
public class AuthenticationController {
    @Autowired
    private AuthenticationService authenticationService;
    @Autowired
    private ITache tach;
    @Autowired
    private UserService userService;
    @Autowired
    ServletContext context;

    @Autowired
    private ChatService chatService;

    @GetMapping("/showuser/{id}")
    public ResponseEntity<?> ShowUser(@PathVariable("id") Long id){
        userService.FindbyId(id);
        return new ResponseEntity<>(userService.FindbyId(id), HttpStatus.OK);
    }
    @PostMapping("/sign-up")
    public ResponseEntity<?> signUp(@RequestParam("file") MultipartFile file, @RequestParam("user") String user)throws IOException
    {
        Etudiant e =  new ObjectMapper().readValue(user, Etudiant.class);
        if (userService.findByEmail(e.getEmail()).isPresent()){
            return new ResponseEntity<>(HttpStatus.CONFLICT);
        }
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());
        e.setImage(fileName);
        e.setActive(false);
        e.setUsername(e.getPrenomE()+" "+e.getNomE());
        Etudiant Suser = userService.saveUser(e);
        String uploadDir = "user-photos/" + Suser.getIdEtudiant();
        System.out.println(uploadDir);
        FileUploadUtil.saveFile(uploadDir, fileName, file);
        userService.sendSimpleMail(Suser.getEmail(), Suser.getUsername());
        userService.SendSms(Suser.getPhonenumber(),
                "Hello we send you you're Credeniel , please check you're email: "+ Suser.getEmail()+ " to reach you're data");
        return new ResponseEntity<>(Suser, HttpStatus.CREATED);
    }

    @PutMapping("/updateuser/{id}")
    public ResponseEntity<?> UpdateUser(@RequestBody Etudiant e , @PathVariable Long id){
        userService.UpdatEtudiant(e,id);
        return new ResponseEntity<>(userService.UpdatEtudiant(e,id), HttpStatus.OK);
    }
    @PostMapping("/sign-in")
    public ResponseEntity<?> signIn(@RequestBody Etudiant user){
        Etudiant Suser = authenticationService.SignInAndReturnJWT(user);
        userService.IsUserActive(user.getEmail());
        return new ResponseEntity<>(Suser, HttpStatus.OK);
    }

    @PutMapping("/useroff")
    public ResponseEntity<?> Desconict(@RequestBody String email){
        userService.UserOff(email);
        return new ResponseEntity<>("Done", HttpStatus.OK);
    }


    //test post only pic

    @GetMapping("/show")
    public ResponseEntity<?> ShowAllUsers(){
        userService.show();
        return new ResponseEntity<>(userService.show(), HttpStatus.OK);
    }
    @GetMapping(path="/image/{id}")
    public byte[] getPhoto(@PathVariable("id") Long id) throws Exception{
        Etudiant e   = userService.findById(id);
        return Files.readAllBytes(Paths.get("user-photos/"+e.getIdEtudiant()+"/"+e.getImage()));
    }


    @MessageMapping("/resume")
    @SendTo("/start/initial")
    public ChatMessage chat(@Payload ChatMessage chat) {
        chatService.add(chat);
        System.out.println(chat.getContent());
        return chat;
    }
    @PutMapping("/updatechat/{ids}/{id}")
    public ResponseEntity<?> getchat(@RequestBody ChatMessage chat,@PathParam("ids") Long ids,@PathParam("id") Long id){
         chatService.update(chat,ids,id);
        return new ResponseEntity<>(chatService.update(chat,ids,id), HttpStatus.OK);
    }
    @GetMapping("/getchatbetwinsandr/{ids}/{idr}")
    public ResponseEntity<?> getchat(@PathVariable("ids") Long ids,@PathVariable("idr") Long idr){
        chatService.getbetwinsenderandreciver(ids,idr);
        return new ResponseEntity<>(chatService.getbetwinsenderandreciver(ids,idr), HttpStatus.OK);
    }

    @GetMapping("/find/{ids}")
    public ResponseEntity<?> getchat(@PathVariable("ids") Long ids){
        userService.findByCreatedate_Month(ids);
        return new ResponseEntity<>(userService.findByCreatedate_Month(ids), HttpStatus.OK);
    }




}
