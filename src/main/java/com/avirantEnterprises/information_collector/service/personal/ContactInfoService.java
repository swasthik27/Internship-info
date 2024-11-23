package com.avirantEnterprises.information_collector.service.personal;

import com.avirantEnterprises.information_collector.model.personal.ContactInfo;
import com.avirantEnterprises.information_collector.repository.personal.ContactInfoRepository;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Optional;

@Service
public class ContactInfoService {

    private final ContactInfoRepository repository;

    @Value("${file.upload-dir}")
    private String uploadDir;

    public ContactInfoService(ContactInfoRepository repository) {
        this.repository = repository;
    }

    // Save or update contact information
    public ContactInfo saveContactInfo(String fullName, String email, String phone, MultipartFile idProof) throws IOException {
        ContactInfo contactInfo = new ContactInfo();
        contactInfo.setFullName(fullName);
        contactInfo.setEmail(email);
        contactInfo.setPhone(phone);

        // Handle file upload
        if (idProof != null && !idProof.isEmpty()) {
            String filePath = saveFile(idProof, "id_proofs");
            contactInfo.setIdProofPath(filePath);
        }

        return repository.save(contactInfo); // Save to database
    }

    // Fetch all contact information records
    public List<ContactInfo> getAllContactInfo() {
        return repository.findAll();
    }

    // Fetch a single contact info by ID
    public ContactInfo getContactInfoById(Long id) {
        return repository.findById(id).orElseThrow(() ->
                new RuntimeException("Contact information not found for ID: " + id));
    }

    // Delete a contact info record by ID
    public void deleteContactInfoById(Long id) {
        if (repository.existsById(id)) {
            repository.deleteById(id);
        } else {
            throw new RuntimeException("Contact information not found for ID: " + id);
        }
    }

    // Helper method to save uploaded files
    private String saveFile(MultipartFile file, String subDirectory) throws IOException {
        File dir = new File(uploadDir + File.separator + subDirectory);
        if (!dir.exists()) dir.mkdirs(); // Create directories if they don't exist

        String filePath = dir.getAbsolutePath() + File.separator + file.getOriginalFilename();
        file.transferTo(new File(filePath));
        return filePath;
    }
}
