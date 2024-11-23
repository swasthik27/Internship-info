package com.avirantEnterprises.information_collector.controller.contact;

import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class RegistrationControllerForContact {

    @Autowired
    private RegistrationServiceForContact registrationServiceForContact;

    @GetMapping("/contactreg")
    public String showRegistrationForm() {
        return "contact/registrationforcontact";
    }


    @PostMapping("/registerforcontact")
    public String register(@RequestParam("name") String name,

                           @RequestParam("contact") String contact,
                           @RequestParam("email") String email,
                           @RequestParam("social") String social,
                           @RequestParam("profilePic") MultipartFile profilePic) {
        registrationServiceForContact.registerUser(name, contact,email,social, profilePic);
        return "personal/success";
    }
}
