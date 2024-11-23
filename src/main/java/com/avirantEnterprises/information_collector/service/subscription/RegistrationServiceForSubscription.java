package com.avirantEnterprises.information_collector.service.subscription;

import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;
import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import com.avirantEnterprises.information_collector.repository.subscription.UserRepositoryForSubscription;
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
public class RegistrationServiceForSubscription {

    @Autowired
    private UserRepositoryForSubscription userRepositoryForSubscription;

    private final Path rootLocation = Paths.get("upload-dir");

    public void registerUser(String name,String plan) {
        UserForSubscripion userForSubscription = new UserForSubscripion();
        userForSubscription.setName(name);
        userForSubscription.setPlan(plan);

        userRepositoryForSubscription.save(userForSubscription);
    }

    public void updateUserProfile(Long id,String name,String plan){
        Optional<UserForSubscripion> optionalUser = userRepositoryForSubscription.findById(id);
        if (optionalUser.isPresent()) {
            UserForSubscripion userForSubscripion = optionalUser.get();
            userForSubscripion.setName(name);
            userForSubscripion.setPlan(plan);


            userRepositoryForSubscription.save(userForSubscripion);
        }
    }

    public UserForSubscripion getUserById(Long id) {
        return userRepositoryForSubscription.findById(id).orElse(null);
    }

    public List<UserForSubscripion> getAllUsers() {
        return userRepositoryForSubscription.findAll();
    }

    public void deleteUserById(Long id) {
        userRepositoryForSubscription.deleteById(id);
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
