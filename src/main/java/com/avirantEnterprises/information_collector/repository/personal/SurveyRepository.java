package com.avirantEnterprises.information_collector.repository.personal;
import com.avirantEnterprises.information_collector.model.personal.Survey;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface SurveyRepository extends JpaRepository<Survey, Long> {
}