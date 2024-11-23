package com.avirantEnterprises.information_collector.repository.survey;

import com.avirantEnterprises.information_collector.model.survey.UserForSurvey;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepositoryForSurvey extends JpaRepository<UserForSurvey, Long> {
}
