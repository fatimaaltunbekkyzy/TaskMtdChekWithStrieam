package serviseImpl;

import dao.DepartmentDao;
import dao.PatientDao;
import daoImpl.DepartmentDaoImpl;
import daoImpl.PatientDaoImpl;
import models.Patient;
import servise.GenericService;
import servise.PatientService;

import java.util.List;
import java.util.Map;

public class PatientImpl implements PatientService, GenericService<Patient> {
    private final PatientDao patientDao = new PatientDaoImpl();
    @Override
    public String addPatientsToHospital(Long id, List<PatientDao> patients) {
        return  patientDao.addPatientsToHospital(id,patients);
    }

    @Override
    public PatientDao getPatientById(Long id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public Map<Integer, PatientDao> getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<PatientDao> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        return patientDao.add(hospitalId,patient);
    }

    @Override
    public void removeById(Long id) {
        patientDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return   patientDao.updateById(id,patient);
    }
}
