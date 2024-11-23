package com.avirantEnterprises.information_collector.service.profileupdate;

import com.avirantEnterprises.information_collector.model.profileupdate.UserForProfileUpdate;
import com.avirantEnterprises.information_collector.model.subscription.UserForSubscripion;
import com.avirantEnterprises.information_collector.repository.profileupdate.UserRepositoryForProfileUpdate;
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
public class RegistrationServiceForProfileUpdate {

    @Autowired
    private UserRepositoryForProfileUpdate userRepositoryForProfileUpdate;

    private final Path rootLocation = Paths.get("upload-dir");

    public void registerUser(String name,String newemail) {
        UserForProfileUpdate userForProfileUpdate = new UserForProfileUpdate();
        userForProfileUpdate.setName(name);
        userForProfileUpdate.setNewemail(newemail);

        userRepositoryForProfileUpdate.save(userForProfileUpdate);
    }

    public void updateUserProfile(Long id,String name,String newemail){
        Optional<UserForProfileUpdate> optionalUser = userRepositoryForProfileUpdate.findById(id);
        if (optionalUser.isPresent()) {
            UserForProfileUpdate userForProfileUpdate = optionalUser.get();
            userForProfileUpdate.setName(name);
            userForProfileUpdate.setNewemail(newemail);


            userRepositoryForProfileUpdate.save(userForProfileUpdate);
        }
    }

    public UserForProfileUpdate getUserById(Long id) {
        return userRepositoryForProfileUpdate.findById(id).orElse(null);
    }

    public List<UserForProfileUpdate> getAllUsers() {
        return userRepositoryForProfileUpdate.findAll();
    }

    public void deleteUserById(Long id) {
        userRepositoryForProfileUpdate.deleteById(id);
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
