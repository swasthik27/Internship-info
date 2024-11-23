package com.avirantEnterprises.information_collector.controller.profileupdate;

import com.avirantEnterprises.information_collector.model.profileupdate.UserForProfileUpdate;
import com.avirantEnterprises.information_collector.service.profileupdate.RegistrationServiceForProfileUpdate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileListControllerForProfileUpdate {

    @Autowired
    private RegistrationServiceForProfileUpdate registrationServiceForProfileUpdate;

    @GetMapping("/profileUpdate")
    public String listUserProfiles(Model model) {
        List<UserForProfileUpdate> usersForProfileUpdate = registrationServiceForProfileUpdate.getAllUsers();
        model.addAttribute("usersForProfileUpdate", usersForProfileUpdate);
        return "profileupdate/profile_listforprofileupdate";
    }
}
