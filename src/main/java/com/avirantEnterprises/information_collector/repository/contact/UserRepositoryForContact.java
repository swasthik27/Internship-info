package com.avirantEnterprises.information_collector.repository.contact;

import com.avirantEnterprises.information_collector.model.contact.UserForContact;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryForContact extends JpaRepository<UserForContact, Long> {
}
