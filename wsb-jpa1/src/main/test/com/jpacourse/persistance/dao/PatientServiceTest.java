package com.jpacourse.service;

import com.jpacourse.dto.VisitTO;
import com.jpacourse.persistence.dao.DoctorDao;
import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.entity.VisitEntity;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceTest {

    @Autowired
    private PatientDao patientDao;

    @Autowired
    private PatientService patientService;

    @Autowired
    private VisitDao visitDao;

    @Autowired
    private DoctorDao doctorDao;

    @Transactional
    @Test
    public void testShouldRemovePatientsAndVisitsButNotDoctors() {
        // given
        final PatientEntity patientEntity = patientDao.findOne(1L);
        assertThat(patientEntity.getVisits()).hasSize(1);
        final VisitEntity visitEntity = patientEntity.getVisits().get(0);

        Long patientId = patientEntity.getId();
        Long visitId = visitEntity.getId();
        Long doctorId = visitEntity.getDoctorEntity().getId();

        // when
        patientService.deleteById(patientId);

        // then
        assertThat(patientDao.findOne(patientId)).isNull();
        assertThat(visitDao.findOne(visitId)).isNull();
        assertThat(doctorDao.findOne(doctorId)).isNotNull();
    }

    @Transactional
    @Test
    public void testShouldFindVisitsByPatientId() {
        // given
        Long patientId = 1L;
        PatientEntity patientEntity = patientDao.findOne(patientId);

        // when
        List<VisitTO> visits = patientService.findByPatientId(patientId);

        // then
        assertThat(visits).isNotNull();
        assertThat(visits).hasSize(patientEntity.getVisits().size());

        for (int i = 0; i < visits.size(); i++) {
            VisitTO visitTO = visits.get(i);
            VisitEntity visitEntity = patientEntity.getVisits().get(i);
            assertThat(visitTO.getTime()).isEqualTo(visitEntity.getTime());
            assertThat(visitTO.getDoctorName()).isEqualTo(visitEntity.getDoctorEntity().getFirstName() + " " + visitEntity.getDoctorEntity().getLastName());
        }
    }

}