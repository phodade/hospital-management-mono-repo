package org.dnyanyog.repo;

import org.dnyanyog.entity.Patients;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

@Repository
@Component
public interface PatientsRepository extends JpaRepository<Patients, Long> {}
