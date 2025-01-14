package com.jpacourse.persistence.dao;

import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.Gender;

import javax.transaction.Transactional;
import java.time.LocalDateTime;
import java.util.List;

public interface PatientDao extends Dao<PatientEntity, Long>
{
    @Transactional
    void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description);
    @Transactional
    List<PatientEntity> findPatientsByLastName(String lastname);
    @Transactional
    List<PatientEntity> findPatientsWithMoreThanXVisits(int visits);
    @Transactional
    List<PatientEntity> findPatientsByGender(Gender gender);
}