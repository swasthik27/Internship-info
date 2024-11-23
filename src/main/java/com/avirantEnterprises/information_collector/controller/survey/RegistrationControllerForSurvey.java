package com.avirantEnterprises.information_collector.controller.survey;

import com.avirantEnterprises.information_collector.service.contact.RegistrationServiceForContact;
import com.avirantEnterprises.information_collector.service.survey.RegistrationServiceForSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class RegistrationControllerForSurvey {

    @Autowired
    private RegistrationServiceForSurvey registrationServiceForSurvey;

    @GetMapping("/surveyreg")
    public String showRegistrationForm() {
        return "survey/registrationforsurvey";
    }


    @PostMapping("/registerforsurvey")
    public String register(@RequestParam("name") String name,

                           @RequestParam("satisfactionlevel") String satisfactionlevel,
                           @RequestParam("feedback") String feedback){
        registrationServiceForSurvey.registerUser(name,satisfactionlevel,feedback );
        return "personal/success";
    }
}
