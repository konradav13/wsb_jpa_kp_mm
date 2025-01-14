package com.jpacourse.mapper;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.entity.DoctorEntity;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;

public final class VisitMapper {

    public static VisitTO mapToTO(final VisitEntity entity) {
        if (entity == null) {
            return null;
        }
        VisitTO visitTO = new VisitTO();
        visitTO.setTime(entity.getTime());
        visitTO.setDoctorName(DoctorMapper.mapToTO(entity.getDoctorEntity()).getFirstName() + " " + DoctorMapper.mapToTO(entity.getDoctorEntity()).getLastName());
        return visitTO;
    }

    public static VisitEntity mapToEntity(final VisitTO visitTO, DoctorEntity doctor, PatientEntity patient) {
        if (visitTO == null) {
            return null;
        }
        VisitEntity visitEntity = new VisitEntity();
        visitEntity.setTime(visitTO.getTime());
        visitEntity.setDoctorEntity(doctor);
        visitEntity.setPatientEntity(patient);
        return visitEntity;
    }
}
