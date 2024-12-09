package daoImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import database.Database;
import models.Department;
import models.Hospital;

import java.util.ArrayList;
import java.util.List;

import static database.Database.hospitals;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        Hospital hospital = new HospitalDaoImpl().findHospitalById(id);
        if(hospital==null){
            return  new ArrayList<>();
        }
        return hospital.getDepartments();
    }

    @Override
    public Department findDepartmentByName(String name) {
        return hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(department -> department.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    @Override
    public String add(Long hospitalId, Department department) {
        try {
        Hospital hospital = Database.hospitals.stream().
                filter(h -> h.getId() == hospitalId).
                findFirst().
                orElseThrow(() -> new RuntimeException("Айди туура эмес"));
        hospital.getDepartments().add(department);
        return "кошулду";
    }catch (Exception e){
        System.out.println(e.getMessage());
        return "Мындай hospital жок";
    }
    }

    @Override
    public void removeById(Long id) {
        try {

            Hospital hospital = Database.hospitals.stream()
                    .filter(hospital1 -> hospital1.getDepartments().stream().anyMatch(d -> d.getId() == (id)))
                    .findFirst()
                    .orElseThrow(() -> new RuntimeException("Айди туура эмес"));
            hospital.getDepartments().removeIf(d -> d.getId() == (id));
            System.out.println("очурулду");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }
    @Override
    public String updateById(Long id, Department department) {
        Department department1 = Database.hospitals.stream()
                .flatMap(d -> d.getDepartments().stream().filter(de -> de.getId() == (id)))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("Айди туура эмес"));
        department.setDepartmentName(department.getDepartmentName());
        return "updated";
    }
}
