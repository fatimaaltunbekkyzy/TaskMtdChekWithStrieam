package serviseImpl;

import dao.GenericDao;
import dao.HospitalDao;
import daoImpl.HospitalDaoImpl;
import models.Hospital;
import models.Patient;
import servise.GenericService;
import servise.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalImpl implements HospitalService  {
   private  final HospitalDao hospitalDao = new HospitalDaoImpl();

    @Override
    public String addHospital(Hospital hospital) {
        return hospitalDao.addHospital(hospital);
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        return hospitalDao.deleteHospitalById(id);
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDao.getAllHospitalByAddress(address);
    }

//    @Override
//    public String add(Long hospitalId, Hospital hospital) {
//        return  genericDao.add(hospitalId,hospital);
//    }
//
//    @Override
//    public void removeById(Long id) {
//     genericDao.removeById(id);
//    }
//
//    @Override
//    public String updateById(Long id, Hospital hospital) {
//        return genericDao.updateById(id,hospital);
//    }
}

