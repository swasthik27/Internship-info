package com.avirantEnterprises.information_collector.controller.profileupdate;

import com.avirantEnterprises.information_collector.model.profileupdate.UserForProfileUpdate;
import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;
import com.avirantEnterprises.information_collector.service.profileupdate.RegistrationServiceForProfileUpdate;
import com.avirantEnterprises.information_collector.service.subscription.RegistrationServiceForSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileControllerForProfileUpdate {

    @Autowired
    private RegistrationServiceForProfileUpdate registrationServiceForProfileUpdate;

    @GetMapping("/profileupdate/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        UserForProfileUpdate userForProfileUpdate = registrationServiceForProfileUpdate.getUserById(id);
        model.addAttribute("userForProfileUpdate", userForProfileUpdate);
        return "profileupdate/profile_viewforprofileupdate"; // View template for displaying the user profile
    }
}
