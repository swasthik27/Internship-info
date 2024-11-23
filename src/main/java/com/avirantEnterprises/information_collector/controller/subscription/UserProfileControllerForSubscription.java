package com.avirantEnterprises.information_collector.controller.subscription;

import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;
import com.avirantEnterprises.information_collector.service.subscription.RegistrationServiceForSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileControllerForSubscription {

    @Autowired
    private RegistrationServiceForSubscription registrationServiceForSubscription;

    @GetMapping("/profilesubscription/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        UserForSubscripion userForSubscripion = registrationServiceForSubscription.getUserById(id);
        model.addAttribute("userForSubscription", userForSubscripion);
        return "subscription/profile_viewforsubscription"; // View template for displaying the user profile
    }
}
