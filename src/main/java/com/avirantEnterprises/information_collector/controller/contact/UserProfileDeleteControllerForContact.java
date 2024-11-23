package com.avirantEnterprises.information_collector.controller.contact;

import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.personal.RegistrationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileDeleteControllerForContact {

    @Autowired
    private RegistrationServiceForContact registrationServiceForContact;

    @GetMapping("/profilecontact/delete/{id}")
    public String deleteUserProfile(@PathVariable Long id) {
        registrationServiceForContact.deleteUserById(id);
        return "redirect:/profileContact"; // Redirect to a list of profiles or another appropriate page
    }
}
