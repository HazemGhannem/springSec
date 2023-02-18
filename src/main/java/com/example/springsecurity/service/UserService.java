package com.example.springsecurity.service;

import com.example.springsecurity.Entity.Etudiant;
import com.example.springsecurity.Entity.Rolee;
import com.example.springsecurity.Repository.EtudiantRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@Slf4j
public class UserService implements IUserService {
    static  String ps;
    @Value("${app.TWILIO_AUTH_TOKEN}")
    private  String Service_TWILIO_AUTH_TOKEN;
    @Value("${app.WILIO_ACCOUNT_SID}")
    private  String Service_TWILIO_ACCOUNT_SID;

    @Autowired
    private EtudiantRepository userRepository;
    @Autowired private JavaMailSender javaMailSender;


    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public Etudiant saveUser(Etudiant user) {
         ps=user.getPassword();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(Rolee.USER);
        user.setBanned(false);
        user.setUsername(user.getPrenomE()+" "+user.getNomE());
        user.setVerified(false);
        user.setCreatedate(LocalDateTime.now());
        return userRepository.save(user);
    }
    @Override
    public Etudiant UserOff(String email){
        Etudiant e= findByEmail(email).get();
        e.setActive(false);
        return userRepository.save(e);
    }

    @Override
    public Etudiant IsUserActive(String email){
        Etudiant e = findByEmail(email).get();
        e.setActive(true);
        return userRepository.save(e);
    }
    @Override
    public Etudiant findById(Long id){
        return userRepository.findById(id).get();
    }

    @Override
    public Optional<Etudiant> findByEmail(String email){
        return userRepository.findByEmail(email);
    }

    @Override
    @Transactional
    public void makeAdmin(String email){
        userRepository.UpdateUserRole(email, Rolee.ADMIN);
    }

    @Override
    @Transactional
    public void MakeUserRole(String email){
        userRepository.UpdateUserRoleUser(email, Rolee.USER);
    }

    @Override
    @Transactional
    public void BannedUser(String email , Boolean bann){
        userRepository.BannUser(email,bann);
    }

    @Override
    @Transactional
    public void VerifyUser(String email , Boolean verif){
        userRepository.VerifyUser(email,verif);
    }

    @Override
    public Page<Etudiant> ShowAllStudent(Optional<Integer> page, Optional<String> sortBy){
        return userRepository.findAll(
                PageRequest.of(
                        page.orElse(1),
                        10,
                        Sort.Direction.ASC, sortBy.orElse("idEtudiant")
                )
        );
    }

    @Override
    public List<Etudiant> show(){
        return userRepository.findAll();
    }

    @Override
    public Etudiant FindbyId(Long id){
        return userRepository.findById(id).orElse(null);
    }
    @Override
    public Etudiant UpdatEtudiant( Etudiant e,  Long id) {
        return userRepository.findById(id)
                .map(Etudiant -> {
                    Etudiant.setPrenomE(e.getPrenomE());
                    Etudiant.setNomE(e.getNomE());
                    Etudiant.setPhonenumber(e.getPhonenumber());
                    Etudiant.setEmail(e.getEmail());
                    Etudiant.setUsername(e.getPrenomE()+" " +e.getNomE());
                    return userRepository.save(Etudiant);
                })
                .orElseGet(() -> {
                    e.setIdEtudiant(id);
                    return userRepository.save(e);
                });
    }
    @Override
    public Etudiant UpdatEtudiantAdmin( Etudiant e,  Long id) {
        return userRepository.findById(id)
                .map(Etudiant -> {
                    Etudiant.setPrenomE(e.getPrenomE());
                    Etudiant.setNomE(e.getNomE());
                    Etudiant.setPhonenumber(e.getPhonenumber());
                    Etudiant.setEmail(e.getEmail());
                    Etudiant.setActive(e.getActive());
                    Etudiant.setVerified(e.getVerified());

                    return userRepository.save(Etudiant);
                })
                .orElseGet(() -> {
                    e.setIdEtudiant(id);
                    return userRepository.save(e);
                });
    }
    @Async
    public String sendSimpleMail(String email,String username)
    {
        // Try block to check for exceptions
        try {
            // Creating a simple mail message
            SimpleMailMessage mailMessage
                    = new SimpleMailMessage();
            // Setting up necessary details
            mailMessage.setFrom("devtestmailer101@gmail.com");
            mailMessage.setTo(email);
            mailMessage.setText("dear "+username+" " +
                    "You're receiving this e-mail because you are now a member of our shcool.\n" +
                    "This is your email and password\n" +
                    "\n" +
                    "email: \n" +email+
                    "\n paswword: \n"+ps);
            mailMessage.setSubject("Website");
            // Sending the mail
            javaMailSender.send(mailMessage);
            log.info("Mail Send");
            return "Mail Sent Successfully...";
        }
        // Catch block to handle the exceptions
        catch (Exception e) {
            return "Error while Sending Mail";
        }
    }


    @Override
    @Async
    public String SendSms(String Phone, String message){
        Twilio.init(Service_TWILIO_ACCOUNT_SID, Service_TWILIO_AUTH_TOKEN);
        Message.creator(new PhoneNumber(Phone),
                new PhoneNumber("+19706958682"), message).create();
        log.info("Sms Send");
        return "Message sent successfully";
    }

    @Override
    public int Statistic(){
        int nbConecting=0;
        for ( Etudiant element:userRepository.findAll()) {
            if (element.getActive()==true){
                nbConecting +=1;
            }
        }
        return nbConecting;
    }
    @Override
    public int getusernumber(){
        return userRepository.findAll().size();
    }

    public int findByCreatedate_Month(Long id){
        return userRepository.finddata(id);
    }

}
