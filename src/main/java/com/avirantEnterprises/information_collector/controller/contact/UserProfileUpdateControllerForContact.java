package com.avirantEnterprises.information_collector.controller.contact;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.model.personal.User;
import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.personal.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.time.LocalDate;

@Controller
public class UserProfileUpdateControllerForContact {

    @Autowired
    private RegistrationServiceForContact registrationServiceForContact;

    @GetMapping("/profilecontact/update/{id}")
    public String showUpdateProfileForm(@PathVariable Long id, Model model) {
        UserForContact userForContact = registrationServiceForContact.getUserById(id);
        model.addAttribute("userForContact", userForContact);
        return "contact/profile_updateforcontact"; // Reference the correct path to your template
    }

    @PostMapping("/updateProfilecContact")
    public String updateProfile(@RequestParam("id") Long id,
                                @RequestParam("name") String name,

                                @RequestParam("contact") String contact,
                                @RequestParam("email") String email,
                                @RequestParam("social") String social,
                                @RequestParam("profilePic") MultipartFile profilePic) {
        registrationServiceForContact.updateUserProfile(id, name, contact,email,social, profilePic);
        return "redirect:/profilecontact/" + id; // Redirect to the updated profile page
    }
}
