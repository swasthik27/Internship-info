package com.avirantEnterprises.information_collector.service.contact;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import com.avirantEnterprises.information_collector.repository.contact.UserRepositoryForContact;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class RegistrationServiceForContact {

    @Autowired
    private UserRepositoryForContact userRepositoryForContact;

    private final Path rootLocation = Paths.get("upload-dir");

    public void registerUser(String name, String contact,String email,String social, MultipartFile profilePic) {
        UserForContact userForContact = new UserForContact();
        userForContact.setName(name);
        userForContact.setContact(contact);
        userForContact.setEmail(email);
        userForContact.setSocial(social);

        String profilePicPath = saveFile(profilePic);
        userForContact.setProfilePicPath(profilePicPath);
        userRepositoryForContact.save(userForContact);
    }

    public void updateUserProfile(Long id, String name, String contact,String email,String social, MultipartFile profilePic) {
        Optional<UserForContact> optionalUser = userRepositoryForContact.findById(id);
        if (optionalUser.isPresent()) {
            UserForContact userForContact = optionalUser.get();
            userForContact.setName(name);
            userForContact.setEmail(email);
            userForContact.setSocial(social);
            if (!profilePic.isEmpty()) {
                String profilePicPath = saveFile(profilePic);
                userForContact.setProfilePicPath(profilePicPath);
            }
            userRepositoryForContact.save(userForContact);
        }
    }

    public UserForContact getUserById(Long id) {
        return userRepositoryForContact.findById(id).orElse(null);
    }

    public List<UserForContact> getAllUsers() {
        return userRepositoryForContact.findAll();
    }

    public void deleteUserById(Long id) {
        userRepositoryForContact.deleteById(id);
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
