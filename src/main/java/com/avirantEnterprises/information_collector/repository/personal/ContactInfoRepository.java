package com.avirantEnterprises.information_collector.repository.personal;

import com.avirantEnterprises.information_collector.model.personal.ContactInfo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ContactInfoRepository extends JpaRepository<ContactInfo, Long> {
}