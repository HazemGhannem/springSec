package com.example.springsecurity.Controller;

import com.example.springsecurity.Entity.EtudDTO;
import com.example.springsecurity.Entity.Tache;
import com.example.springsecurity.service.ITache;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;

@RestController
@AllArgsConstructor
@RequestMapping("/api/user")
public class TacheController {
    private ITache tach;
    @PostMapping("/addtach")
    public ResponseEntity<?> createtach(@RequestBody Tache T){
        tach.CreateTache(T);
        return new ResponseEntity<>(tach.CreateTache(T), HttpStatus.OK);
    }

    @GetMapping("/getalltach")
    public ResponseEntity<?> gettach(){
        tach.GetAllTaches();
        return new ResponseEntity<>(tach.GetAllTaches(), HttpStatus.OK);
    }
    @GetMapping("/gettach/{id}")
    public ResponseEntity<?> gettach(@PathVariable("id") Long id){
        tach.GeTache(id);
        return new ResponseEntity<>(tach.GeTache(id), HttpStatus.OK);
    }
    @PutMapping("/updatetache/{id}")
    public ResponseEntity<?> update(@RequestBody Tache T,@PathVariable("id") Long id){
        tach.UpdateTache(T,id);
        return new ResponseEntity<>(tach.UpdateTache(T,id), HttpStatus.OK);
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> delete(@PathVariable("id") Long id){
        tach.DeleteTache(id);
        return new ResponseEntity<>("done", HttpStatus.OK);
    }


    @PutMapping("/settach/{id}/{idt}")
    public ResponseEntity<?> update(@PathVariable("id") Long id,@PathVariable("idt") Long idt){
        tach.settachtostudiant(id,idt);
        return new ResponseEntity<>(tach.settachtostudiant(id,idt), HttpStatus.OK);
    }
}
