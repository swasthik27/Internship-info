package com.avirantEnterprises.information_collector.service.survey;

import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import com.avirantEnterprises.information_collector.repository.survey.UserRepositoryForSurvey;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceForSurvey {

    @Autowired
    private UserRepositoryForSurvey userRepositoryForSurvey;

    private final Path rootLocation = Paths.get("upload-dir");

    public void registerUser(String name,String satisfactionlevel,String feedback) {
        UserForSurvey userForSurvey = new UserForSurvey();
        userForSurvey.setName(name);
        userForSurvey.setSatisfactionlevel(satisfactionlevel);
        userForSurvey.setFeedback(feedback);

        userRepositoryForSurvey.save(userForSurvey);
    }

    public void updateUserProfile(Long id,String name,String satisfactionlevel,String feedback){
        Optional<UserForSurvey> optionalUser = userRepositoryForSurvey.findById(id);
        if (optionalUser.isPresent()) {
            UserForSurvey userForSurvey = optionalUser.get();
            userForSurvey.setName(name);
            userForSurvey.setSatisfactionlevel(satisfactionlevel);
            userForSurvey.setFeedback(feedback);

            userRepositoryForSurvey.save(userForSurvey);
        }
    }

    public UserForSurvey getUserById(Long id) {
        return userRepositoryForSurvey.findById(id).orElse(null);
    }

    public List<UserForSurvey> getAllUsers() {
        return userRepositoryForSurvey.findAll();
    }

    public void deleteUserById(Long id) {
        userRepositoryForSurvey.deleteById(id);
    }

//    private String saveFile(MultipartFile file) {
//        try {
//            Files.createDirectories(rootLocation);
//            Path destinationFile = rootLocation.resolve(Paths.get(file.getOriginalFilename()))
//                    .normalize().toAbsolutePath();
//            file.transferTo(destinationFile);
//            return file.getOriginalFilename();  // Store only the file name
//        } catch (IOException e) {
//            throw new RuntimeException("Failed to store file.", e);
//        }
//    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-]", "_");
    }

    private String saveFile(MultipartFile file) {
        try {
            Files.createDirectories(rootLocation);
            String sanitizedFileName = sanitizeFileName(file.getOriginalFilename());
            Path destinationFile = rootLocation.resolve(Paths.get(sanitizedFileName))
                    .normalize().toAbsolutePath();
            file.transferTo(destinationFile);
            return sanitizedFileName;  // Store only the sanitized file name
        } catch (IOException e) {
            throw new RuntimeException("Failed to store file.", e);
        }
    }
}
