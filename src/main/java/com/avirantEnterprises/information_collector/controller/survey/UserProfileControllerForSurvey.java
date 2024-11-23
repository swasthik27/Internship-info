package com.avirantEnterprises.information_collector.controller.survey;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.survey.RegistrationServiceForSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Controller
public class UserProfileControllerForSurvey {

    @Autowired
    private RegistrationServiceForSurvey registrationServiceForSurvey;

    @GetMapping("/profilesurvey/{id}")
    public String viewUserProfile(@PathVariable Long id, Model model) {
        UserForSurvey userForSurvey = registrationServiceForSurvey.getUserById(id);
        model.addAttribute("userForSurvey", userForSurvey);
        return "survey/profile_viewforsurvey"; // View template for displaying the user profile
    }
}
