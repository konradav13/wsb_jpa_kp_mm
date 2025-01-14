package com.jpacourse.persistance.dao;

import com.jpacourse.persistence.dao.PatientDao;
import com.jpacourse.persistence.dao.VisitDao;
import com.jpacourse.persistence.entity.PatientEntity;
import com.jpacourse.persistence.enums.Gender;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.orm.ObjectOptimisticLockingFailureException;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientDaoTest {

    @Autowired
    private PatientDao patientDao;
    @Autowired
    private VisitDao visitDao;

    @Transactional
    @Test
    public void testShouldAddVisitToPatient() {
        // given
        assertThat(patientDao.findOne(6L).getVisits()).isEmpty();

        // when
        patientDao.addVisitToPatient(6L, 1L, LocalDateTime.now(), "test");

        // then
        PatientEntity patientEntity = patientDao.findOne(6L);
        assertThat(patientEntity.getVisits().size()).isEqualTo(1);
        assertThat(visitDao.findOne(patientEntity.getVisits().get(0).getId())).isNotNull();
    }

    @Transactional
    @Test
    public void testShouldFindPatientsByLastName() {
        // given
        String lastname = "Taylor";

        // when
        // then
        assertThat(patientDao.findPatientsByLastName(lastname)).isNotEmpty();
        assertThat(patientDao.findPatientsByLastName(lastname)).allMatch(patientEntity -> patientEntity.getLastName().equals(lastname));
    }

    @Transactional
    @Test
    public void testShouldFindPatientsWithMoreThanXVisits() {
        // given
        int visits = 1;

        // when
        // then
        assertThat(patientDao.findPatientsWithMoreThanXVisits(visits)).isNotEmpty();
        assertThat(patientDao.findPatientsWithMoreThanXVisits(visits)).allMatch(patientEntity -> patientEntity.getVisits().size() > visits);
    }

    @Transactional
    @Test
    public void testShouldFindPatientsByGender() {
        // given
        Gender gender = Gender.MALE;

        // when
        // then
        assertThat(patientDao.findPatientsByGender(gender)).isNotEmpty();
        assertThat(patientDao.findPatientsByGender(gender)).allMatch(patientEntity -> patientEntity.getGender().equals(gender));
    }

    @Test
    public void testShouldHandleOptimisticLockingWithSpringException() {
        // given
        Long patientId = 5L;

        PatientEntity patientEntity1 = patientDao.findOne(patientId);
        PatientEntity patientEntity2 = patientDao.findOne(patientId);

        int initialVersion = patientEntity1.getVersion();
        assertThat(patientEntity2.getVersion()).isEqualTo(initialVersion);

        patientEntity1.setFirstName("Lamine");
        patientDao.update(patientEntity1);

        PatientEntity updatedEntity = patientDao.findOne(patientId);
        assertThat(updatedEntity.getVersion()).isEqualTo(initialVersion + 1);

        // when & then
        assertThatThrownBy(() -> {
            patientEntity2.setFirstName("Yamal");
            patientDao.update(patientEntity2);
        }).isInstanceOf(ObjectOptimisticLockingFailureException.class);

        assertThat(patientDao.findOne(patientId).getFirstName()).isEqualTo("Lamine");
    }
}
