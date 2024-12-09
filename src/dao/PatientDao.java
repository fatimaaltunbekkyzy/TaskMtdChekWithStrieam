package dao;

import models.Doctor;
import models.Patient;

import java.util.List;
import java.util.Map;

public interface PatientDao {

    String addPatientsToHospital(Long id, List<PatientDao> patients);

    PatientDao getPatientById(Long id);

    Map<Integer, PatientDao> getPatientByAge();

    List<PatientDao> sortPatientsByAge(String ascOrDesc);

    String add(Long  hospitalId, Patient patient);

    void removeById(Long id);

    String updateById(Long id,Patient patient );
}
