package daoImpl;

import dao.GenericDao;
import dao.HospitalDao;
import database.Database;
import database.GeneratorId;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        Database.hospitals.add(hospital);
        return "Hospital added success full";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return Database.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .map(Hospital::getPatients)
                .findFirst()
                .orElseGet(ArrayList::new);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return Database.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(id))
                .findFirst()
                .map(hospital -> {
                    Database.hospitals.remove(hospital);
                    return "Hospital deleted successfully";
                })
                .orElse("Hospital not found");
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
         return Database.hospitals.stream()
                .filter(hospital -> hospital.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toMap(Hospital::getHospitalName,hospital -> hospital));
    }

//    @Override
//    public String add(Long hospitalId, Hospital hospital) {
//        Database.hospitals.add(hospital);
//        return "Hospital added successfully";
//    }
//
//    @Override
//    public void removeById(Long id) {
//        Database.hospitals.removeIf(hospital -> hospital.getId().equals(id));
//        System.out.println("Hospital removed successfully if found.");
//    }
//
//    @Override
//    public String updateById(Long id, Hospital hospital) {
// Hospital hospital1 = Database.hospitals.stream().filter(h -> h.getId()==id).findFirst().get();
// hospital1.setHospitalName(hospital.getHospitalName());
// hospital1.setAddress(hospital.getAddress());
//        return "update success full";
//    }
}
