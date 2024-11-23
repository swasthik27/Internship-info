package com.avirantEnterprises.information_collector.controller.subscription;

import com.avirantEnterprises.information_collector.service.subscription.RegistrationServiceForSubscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RegistrationControllerForSubscription {

    @Autowired
    private RegistrationServiceForSubscription registrationServiceForSubscription;

    @GetMapping("/subscriptionreg")
    public String showRegistrationForm() {
        return "subscription/registrationforsubscription";
    }


    @PostMapping("/registrationforsubscription")
    public String register(@RequestParam("name") String name,

                           @RequestParam("plan") String plan){
        registrationServiceForSubscription.registerUser(name,plan);
        return "personal/success";
    }
}
