package com.avirantEnterprises.information_collector.controller.subscription;

import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;
import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import com.avirantEnterprises.information_collector.service.subscription.RegistrationServiceForSubscription;
import com.avirantEnterprises.information_collector.service.survey.RegistrationServiceForSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileListControllerForSubscription {

    @Autowired
    private RegistrationServiceForSubscription registrationServiceForSubscription;

    @GetMapping("/profileSubscription")
    public String listUserProfiles(Model model) {
        List<UserForSubscripion> usersForSubscription = registrationServiceForSubscription.getAllUsers();
        model.addAttribute("usersForSubscription", usersForSubscription);
        return "subscription/profile_listforsubscription";
    }
}
