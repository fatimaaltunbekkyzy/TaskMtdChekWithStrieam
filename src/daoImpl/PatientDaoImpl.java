package daoImpl;

import dao.GenericDao;
import dao.PatientDao;
import database.Database;
import models.Hospital;
import models.Patient;
import servise.GenericService;

import java.util.*;
import java.util.stream.Collectors;

import static database.Database.patients;
import static database.GeneratorId.hospitalId;

public class PatientDaoImpl implements PatientDao , GenericDao<Patient> {
    @Override
    public String addPatientsToHospital(Long id, List<PatientDao> patients) {
        for (Patient patient : Database.patients) {
            if (patient.getId().equals(id)) {
                return "Пациенты успешно добавлены в больницу.";
            }
        }
        return "Больница с таким ID не найдена.";
    }

    @Override
    public PatientDao getPatientById(Long id) {
        for (Patient patient : patients) {
            if (patient.getId().equals(id)) {
                return null;
            }
        }
        return null;
    }

    @Override
    public Map<Integer, PatientDao> getPatientByAge() {
        Map<Integer,Patient> patientMap = new HashMap<>();
        for (Hospital hospital:Database.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                patientMap.put(patient.getAge(), patient);
            }}
        return null;
    }

    @Override
    public List<PatientDao> sortPatientsByAge(String ascOrDesc) {
        List<Patient> patients = new ArrayList<>();
        for (Hospital hospital:Database.hospitals){
            patients.addAll(hospital.getPatients());
        }
        if ("asc".equalsIgnoreCase(ascOrDesc)){
            patients.sort(Comparator.comparingInt(Patient::getAge));
        }else if ("desc".equalsIgnoreCase(ascOrDesc)){
            patients.sort(Comparator.comparingInt(Patient::getAge).reversed());
        }else {
            System.out.println("Кандайдыр бир койгой бар");
        }
        return null;
    }

    @Override
    public String add(Long hospitalId, Patient patient) {
        try {
            Hospital hospital = Database.hospitals.stream().
                    filter(h -> h.getId() == hospitalId).
                    findFirst().
                    orElseThrow(() -> new RuntimeException("Айди туура эмес"));
            hospital.getPatients().add(patient);
            return "кошулду";
        }catch (Exception e){
            System.out.println(e.getMessage());
            return "Мындай Hospital жок";
        }
    }

    @Override
    public void removeById(Long id) {
        try {
            Hospital hospital = Database.hospitals.stream()
                    .filter(hospital1 -> hospital1.getPatients().stream()
                            .anyMatch(patient -> patient.getId() == (id)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Айди туура эмес"));
            hospital.getPatients().removeIf(patient -> patient.getId() == id);
            System.out.println("Очурулду");
        }catch (Exception e){
            System.out.println(e.getMessage());}
    }

    @Override
    public String updateById(Long id, Patient patient) {
        return Database.hospitals.stream()
                .flatMap(hospital -> hospital.getPatients().stream())
                .filter(p -> p.getId().equals(id))
                .findFirst()
                .map(existingPatient -> {
                    existingPatient.setFirstName(patient.getFirstName());
                    existingPatient.setLastName(patient.getLastName());
                    existingPatient.setAge(patient.getAge());
                    existingPatient.setGender(patient.getGender());
                    return "Successfully updated";
                })
                .orElse("Кайрадан арекет кылыныз");
}}
