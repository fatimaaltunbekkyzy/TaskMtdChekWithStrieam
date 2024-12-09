package daoImpl;

import dao.DoctorDao;
import dao.GenericDao;
import database.Database;
import models.Department;
import models.Doctor;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {
    @Override
    public Doctor findDoctorById(Long id) {
        return Database.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(doctor -> doctor.getId() == id)
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return Database.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream()
                        .filter(department -> department.getId() == (departmentId))
                        .map(department -> {
                            List<Doctor> doctorsToAssign = hospital.getDoctors().stream()
                                    .filter(doctor -> doctorsId.contains(doctor.getId()))

                                    .toList();
                            department.getDoctors().addAll(doctorsToAssign);
                            hospital.getDoctors().removeAll(doctorsToAssign);

                            return "Доктора успешно назначены в департамент!";
                        })
                )
                .findFirst()
                .orElse("Департамент с ID " + departmentId + " не найден!");
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId() == id)
                .findFirst()
                .map(hospital -> hospital.getDoctors())
                .orElse(null);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        try {
            return Database.hospitals.stream()
                    .flatMap(hospital -> hospital.getDepartments().stream())
                    .filter(department -> department.getId() == id)
                    .findFirst()
                    .map(Department::getDoctors)
                    .orElseThrow(() -> new RuntimeException("Айди туура эмес"));
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        return null;

    }

    @Override
    public String add(Long hospitalId, Doctor doctor) {
        try {
            Hospital hospital = Database.hospitals.stream()
                    .filter(hospital1 -> hospital1.getId() == hospitalId)
                    .findFirst().orElse(null);

            hospital.getDoctors().add(doctor);
            return "кошулду";
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return "Мындай Hospital жок";
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            Hospital hospital = Database.hospitals.stream()
                    .filter(hospital1 -> hospital1.getDoctors().stream()
                            .anyMatch(doctor -> doctor.getId() == (id)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Айди туура эмес"));
            hospital.getDoctors().removeIf(doctor -> doctor.getId() == id);
            System.out.println("Очурулду");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
                }
    @Override
    public String updateById(Long id, Doctor doctor) {
        return Database.hospitals.stream()
                .flatMap(hospital -> hospital.getDoctors().stream())
                .filter(existingDoctor -> existingDoctor.getId().equals(id))
                .findFirst()
                .map(existingDoctor -> {
                    existingDoctor.setFirstName(doctor.getFirstName());
                    existingDoctor.setLastName(doctor.getLastName());
                    existingDoctor.setGender(doctor.getGender());
                    existingDoctor.setExperienceYear(doctor.getExperienceYear());
                    return "Successfully updated";
                })
                .orElse("Try again");
    }}

