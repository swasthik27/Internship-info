package com.avirantEnterprises.information_collector.controller.survey;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.survey.RegistrationServiceForSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

@Controller
public class UserProfileListControllerForSurvey {

    @Autowired
    private RegistrationServiceForSurvey registrationServiceForSurvey;

    @GetMapping("/profileSurvey")
    public String listUserProfiles(Model model) {
        List<UserForSurvey> usersForSurvey = registrationServiceForSurvey.getAllUsers();
        model.addAttribute("usersForSurvey", usersForSurvey);
        return "survey/profile_listforsurvey";
    }
}
