package com.avirantEnterprises.information_collector.controller.contact;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.model.personal.User;
import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.personal.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileListControllerForContact {

    @Autowired
    private RegistrationServiceForContact registrationServiceForContact;

    @GetMapping("/profileContact")
    public String listUserProfiles(Model model) {
        List<UserForContact> usersForContact = registrationServiceForContact.getAllUsers();
        model.addAttribute("usersForContact", usersForContact);
        return "contact/profile_listforcontact";
    }
}
