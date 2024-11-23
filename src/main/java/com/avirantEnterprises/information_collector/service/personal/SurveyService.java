package com.avirantEnterprises.information_collector.service.personal;

import com.avirantEnterprises.information_collector.model.personal.Survey;
import com.avirantEnterprises.information_collector.repository.personal.SurveyRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Service
public class SurveyService {

    private final SurveyRepository surveyRepository;

    @Value("${file.upload-dir}") // Inject the file upload directory path
    private String uploadDir;

    public SurveyService(SurveyRepository surveyRepository) {
        this.surveyRepository = surveyRepository;
    }

    /**
     * Saves the survey details submitted by the user.
     *
     * @param feedback   The feedback provided by the user.
     * @param preferences The preferences selected by the user.
     * @param additionalDocs Optional file uploaded by the user.
     * @return A success message or an error message in case of failure.
     */
    public String saveSurvey(String feedback, String[] preferences, MultipartFile additionalDocs) {
        try {
            // Create a new Survey object
            Survey survey = new Survey();
            survey.setFeedback(feedback);

            // Convert the preferences array to a List and set it
            List<String> preferenceList = (preferences != null) ? Arrays.asList(preferences) : null;
            survey.setPreferences(preferenceList);

            // Save the file if it's provided
            if (additionalDocs != null && !additionalDocs.isEmpty()) {
                String filePath = saveFile(additionalDocs, "survey_documents");
                survey.setAdditionalDocsPath(filePath);
            }

            // Save the Survey object in the database
            surveyRepository.save(survey);

            // Return a success message
            return "Survey submitted successfully!";
        } catch (IOException e) {
            // Return an error message in case of exceptions
            return "An error occurred while saving the survey: " + e.getMessage();
        }
    }

    /**
     * Helper method to save uploaded files to the designated directory.
     *
     * @param file        The file uploaded by the user.
     * @param subDirectory Subdirectory where the file should be stored.
     * @return The file path where the file is saved.
     * @throws IOException if file transfer fails.
     */
    private String saveFile(MultipartFile file, String subDirectory) throws IOException {
        File dir = new File(uploadDir + subDirectory);
        if (!dir.exists()) dir.mkdirs(); // Create the directory if it doesn't exist

        // Save the file to the directory
        String filePath = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));

        return filePath; // Return the saved file path
    }
}
