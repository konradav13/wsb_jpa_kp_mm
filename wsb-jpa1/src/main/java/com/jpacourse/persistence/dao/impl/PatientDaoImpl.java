package com.jpacourse.persistence.dao.impl;

import com.jpacourse.persistence.dao.AddressDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.AddressEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.springframework.stereotype.Repository;
import com.jpacourse.persistence.enums.Gender;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public class PatientDaoImpl extends AbstractDao<PatientEntity, Long> implements PatientDao
{
    @Override
    public void addVisitToPatient(Long patientId, Long doctorId, LocalDateTime visitDate, String description)
    {

        PatientEntity patient = entityManager.find(PatientEntity.class, patientId);
        DoctorEntity doctor = entityManager.find(DoctorEntity.class, doctorId);

        if (patient == null || doctor == null)
        {
            return;
        }

        VisitEntity visit = new VisitEntity();
        visit.setPatientEntity(patient);
        visit.setDoctorEntity(doctor);
        visit.setTime(visitDate);
        visit.setDescription(description);

        patient.getVisits().add(visit);
        entityManager.merge(patient);
    }

    @Override
    public List<PatientEntity> findPatientsByLastName(String lastname) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.lastName = :lastname", PatientEntity.class)
                .setParameter("lastname", lastname)
                .getResultList();
    }

    @Override
    public List<PatientEntity> findPatientsWithMoreThanXVisits(int visits) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE SIZE(p.visits) > :visits", PatientEntity.class)
                .setParameter("visits", visits)
                .getResultList();
    }
    @Override
    public List<PatientEntity> findPatientsByGender(Gender gender) {
        return entityManager.createQuery("SELECT p FROM PatientEntity p WHERE p.gender = :gender", PatientEntity.class)
                .setParameter("gender", gender)
                .getResultList();
    }
}