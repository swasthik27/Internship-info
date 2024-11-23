package com.avirantEnterprises.information_collector.controller.personal;

import com.avirantEnterprises.information_collector.service.personal.SurveyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

@Controller
public class SurveyController {

    private final SurveyService surveyService;

    // Constructor injection for the SurveyService
    public SurveyController(SurveyService surveyService) {
        this.surveyService = surveyService;
    }

    // GET request to display the survey form
    @GetMapping("/survey")
    public String showSurveyForm() {
        return "personal/survey";  // Returns the survey.html template located in /templates/
    }

    // POST request to handle survey submission
    @PostMapping("/submit-survey")
    public String submitSurvey(String feedback, String[] preferences,
                               MultipartFile additionalDocs, Model model) {
        // Use the service method to handle survey saving
        String resultMessage = surveyService.saveSurvey(feedback, preferences, additionalDocs);

        // Add the result message to the model (success or error)
        if (resultMessage.startsWith("An error")) {
            model.addAttribute("error", resultMessage);
        } else {
            model.addAttribute("success", resultMessage);
        }

        return "redirect:/survey-success"; // Redirect to the success page
    }

    // GET request for survey success page
    @GetMapping("/survey-success")
    public String showSurveySuccess(Model model) {
        model.addAttribute("message", "Survey submitted successfully!");
        return "personal/survey-success";  // Returns the survey-success.html template located in /templates/personal/
    }
}
