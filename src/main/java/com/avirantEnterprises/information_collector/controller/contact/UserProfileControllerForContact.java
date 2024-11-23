package com.avirantEnterprises.information_collector.controller.contact;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.model.personal.User;
import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.personal.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileControllerForContact {

    @Autowired
    private RegistrationServiceForContact registrationServiceForContact;

    @GetMapping("/profilecontact/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        UserForContact userForContact = registrationServiceForContact.getUserById(id);
        System.out.println("Profile Picture Path: " + userForContact.getProfilePicPath());
        model.addAttribute("userForContact", userForContact);
        return "contact/profile_viewforcontact"; // View template for displaying the user profile
    }
}
