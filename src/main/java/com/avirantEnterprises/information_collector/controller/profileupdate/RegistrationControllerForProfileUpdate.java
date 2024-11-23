package com.avirantEnterprises.information_collector.controller.profileupdate;

import com.avirantEnterprises.information_collector.service.profileupdate.RegistrationServiceForProfileUpdate;
import com.avirantEnterprises.information_collector.service.subscription.RegistrationServiceForSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationControllerForProfileUpdate {

    @Autowired
    private RegistrationServiceForProfileUpdate registrationServiceForProfileUpdate;

    @GetMapping("/profileupdatereg")
    public String showRegistrationForm() {
        return "profileupdate/registrationforprofileupdate";
    }


    @PostMapping("/registrationforprofileupdate")
    public String register(@RequestParam("name") String name,

                           @RequestParam("newemail") String newemail){
        registrationServiceForProfileUpdate.registerUser(name,newemail);
        return "personal/success";
    }
}
