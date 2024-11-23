package com.avirantEnterprises.information_collector.controller.personal;

import com.avirantEnterprises.information_collector.model.personal.ContactInfo;
import com.avirantEnterprises.information_collector.service.personal.ContactInfoService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@Controller
public class ContactInfoController {

    private final ContactInfoService service;

    public ContactInfoController(ContactInfoService service) {
        this.service = service;
    }

    @GetMapping("/contact-info")
    public String showContactInfoForm() {
        return "personal/contact-info";  // Points to contact-info.html template
    }

    @PostMapping("/contact-info")
    public String submitContactInfo(String fullName, String email, String phone,
                                    MultipartFile idProof, Model model) {
        try {
            service.saveContactInfo(fullName, email, phone, idProof); // Use service to save contact info
            model.addAttribute("success", "Contact information submitted successfully!");
        } catch (Exception e) {
            model.addAttribute("error", "An error occurred: " + e.getMessage());
        }

        return "redirect:/contact-success";  // Redirect to the success page
    }

    @GetMapping("/contact-success")
    public String showContactSuccessPage(Model model) {
        model.addAttribute("message", "Your contact information has been submitted successfully.");
        return "personal/contact-success";  // Return a success page template
    }

    // Endpoint to list all contact information
    @GetMapping("/contact-info/list")
    public String listContactInfo(Model model) {
        model.addAttribute("contactInfos", service.getAllContactInfo());
        return "personal/contact-info-list"; // Points to contact-info-list.html template
    }

    // Endpoint to view contact info by ID
    @GetMapping("/contact-info/{id}")
    public String viewContactInfo(@PathVariable Long id, Model model) {
        try {
            model.addAttribute("contactInfo", service.getContactInfoById(id));
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "personal/contact-info-detail"; // Points to contact-info-detail.html template
    }

    // Endpoint to delete contact info by ID
    @PostMapping("/contact-info/delete/{id}")
    public String deleteContactInfo(@PathVariable Long id, Model model) {
        try {
            service.deleteContactInfoById(id);
            model.addAttribute("success", "Contact information deleted successfully.");
        } catch (Exception e) {
            model.addAttribute("error", "Error: " + e.getMessage());
        }
        return "redirect:/contact-info/list";
    }
}
