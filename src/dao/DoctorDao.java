package dao;

import models.Department;
import models.Doctor;

import java.util.List;

public interface DoctorDao {
    Doctor findDoctorById(Long id);

    String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId);

    List<Doctor> getAllDoctorsByHospitalId(Long id);

    List<Doctor> getAllDoctorsByDepartmentId(Long id);

    String add(Long  hospitalId,Doctor doctor);

    void removeById(Long id);

    String updateById(Long id,Doctor doctor );
}
