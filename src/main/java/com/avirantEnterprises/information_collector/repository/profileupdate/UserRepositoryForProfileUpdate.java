package com.avirantEnterprises.information_collector.repository.profileupdate;

import com.avirantEnterprises.information_collector.model.profileupdate.UserForProfileUpdate;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryForProfileUpdate extends JpaRepository<UserForProfileUpdate, Long> {
}
