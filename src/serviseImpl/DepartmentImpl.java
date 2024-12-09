package serviseImpl;

import dao.DepartmentDao;
import dao.GenericDao;
import daoImpl.DepartmentDaoImpl;
import daoImpl.HospitalDaoImpl;
import models.Department;
import servise.DepartmentService;

import java.util.List;

public class DepartmentImpl implements DepartmentService, GenericDao<Department> {
 private final DepartmentDao departmentDao = new DepartmentDaoImpl();
    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }

    @Override
    public String add(Long hospitalId, Department department) {
        return departmentDao.add(hospitalId,department);
    }

    @Override
    public void removeById(Long id) {
    departmentDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Department department) {
        return  departmentDao.updateById(id,department);
    }
}

