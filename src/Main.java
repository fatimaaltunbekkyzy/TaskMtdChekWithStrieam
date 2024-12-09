import dao.HospitalDao;
import daoImpl.HospitalDaoImpl;
import database.Database;
import database.GeneratorId;
import enams.Gender;
import models.Department;
import models.Doctor;
import models.Hospital;
import models.Patient;
import org.w3c.dom.css.Counter;
import servise.*;
import serviseImpl.DepartmentImpl;
import serviseImpl.DoctorImpl;
import serviseImpl.HospitalImpl;
import serviseImpl.PatientImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

//TIP To <b>Run</b> code, press <shortcut actionId="Run"/> or
// click the <icon src="AllIcons.Actions.Execute"/> icon in the gutter.
public class Main {
    public static void main(String[] args) {

        PatientImpl patientService = new PatientImpl();
        DoctorImpl doctorService = new DoctorImpl();
        HospitalImpl hospitalService = new HospitalImpl();
        DepartmentImpl departmentService = new DepartmentImpl();
        Scanner scanner = new Scanner(System.in);
        Scanner scanner1 = new Scanner(System.in);

        while (true) {
            System.out.println("1. addHospital");
            System.out.println("2. findHospitalById");
            System.out.println("3. getAllHospital");
            System.out.println("4. getAllPatientFromHospital");
            System.out.println("5. deleteHospitalById");
            System.out.println("6. getAllHospitalByAddress");
            System.out.println("~~~~~~~~~~~~~Doctor~~~~~~~~~~~~~");
            System.out.println("7. add doctor");
            System.out.println("8. findDoctorById");
            System.out.println("9. getAllDoctorsByHospitalId");
            System.out.println("10.getAllDoctorsByDepartmentId");
            System.out.println("11.delete Doctor By Id");
            System.out.println("~~~~~~~~~~~~~~Patient~~~~~~~~~~~~~~");
            System.out.println("12. add patient");
            System.out.println("13. getPatientById");
            System.out.println("14. getPatientByAge");
            System.out.println("15. sortPatientsByAge ");
            System.out.println("16. updated patient by id");
            System.out.println("17. delete patient by id");
            System.out.println("~~~~~~~~~~~~Department~~~~~~~~~~~~~~~~~");
            System.out.println("18. Add department");
            System.out.println("19. getAllDepartmentByHospital");
            System.out.println("20. findDepartmentByName");
            System.out.println("21. findDoctorById");
            System.out.println("22. assignDoctorToDepartment");
            System.out.println("23. getAllDoctorsByHospitalId");
            System.out.println("24. getAllDoctorsByDepartmentId");
            System.out.println("25. addPatientsToHospital");


            int choice1 = scanner1.nextInt();
            switch (choice1) {
                case 1:
                    try {
                        scanner.nextLine();
                        System.out.print("Writer hospital name: ");
                        String hospitalName = scanner.nextLine();
                        System.out.print("Writer hospital address: ");
                        String address = scanner.nextLine();
                        Hospital hospital = new Hospital(GeneratorId.hospitalId, hospitalName, address, new ArrayList<>(), new ArrayList<>(), new ArrayList<>());
                        System.out.println(hospitalService.addHospital(hospital));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.println("Writer hospital id: ");
                    Long id = scanner1.nextLong();
                    System.out.println(hospitalService.findHospitalById(id));
                    break;
                case 3:
                    System.out.println( hospitalService.getAllHospital());
                    break;
                case 4:
                    System.out.println("Writer hospital id: ");
                    Long id1 = scanner1.nextLong();
                    System.out.println(hospitalService.getAllPatientFromHospital(id1));
                    break;
                case 5:
                    System.out.println("Writer hospital id: ");
                    Long id2 = scanner1.nextLong();
                    System.out.println(hospitalService.deleteHospitalById(id2));
                    break;
                case 6:
                    System.out.println("Write hospital address: ");
                    String address = scanner.nextLine();
                    System.out.println(hospitalService.getAllHospitalByAddress(address));
                    break;
                case 7:
                    try{
                        System.out.print("Writer doctor's first name: ");
                        String doctorFirstName = scanner.nextLine();
                        System.out.print("Writer doctor's last name: ");
                        String doctorLastName = scanner.nextLine();
                        System.out.print("Writer doctor's gender (MALE/FEMALE): ");
                        String genderInput = scanner.nextLine().toUpperCase();
                        Gender doctorGender = Gender.valueOf(genderInput);
                        System.out.print("Writer doctor's experience years: ");
                        int experienceYears = scanner.nextInt();
                        Doctor doctor = new Doctor(GeneratorId.doctorId(), doctorFirstName, doctorLastName, doctorGender, experienceYears);
                        System.out.print("Writer hospital ID to add this doctor: ");
                        Long hospitalIdForDoctor = scanner.nextLong();
                        System.out.println(doctorService.add(hospitalIdForDoctor, doctor));

                    } catch (IllegalArgumentException e) {
                        System.out.println("Ошибка: введено не число. Попробуйте снова.");
                        scanner.nextLine();
                    }catch (Exception e){
                        System.out.println(e.getMessage());
                    }
                    break;
                case 8:
                    System.out.println("Writer doctor id: ");
                    Long id4 = scanner1.nextLong();
                    System.out.println(doctorService.findDoctorById(id4));
                    break;
                case 9:
                    System.out.println("Writer hospital id");
                    Long id5 = scanner1.nextLong();
                    System.out.println(doctorService.getAllDoctorsByHospitalId(id5));
                    break;
                case 10:
                    System.out.println("Writer doctor id: ");
                    Long id6 = scanner1.nextLong();
                    doctorService.removeById(id6);
                    break;
                case 11:
                    try {
                        System.out.println("Write id doctor to update: ");
                        Long id7 = scanner1.nextLong();
                        System.out.println("Write name for update:");
                        String name = scanner.nextLine();
                        for (int i = 0; i < name.length(); i++) {
                            if (!Character.isLetter(name.charAt(i))) {
                                throw new IllegalArgumentException("Сан эмес соз жазыныз");
                            }
                        }
                        Doctor doctor = new Doctor();
                        doctor.setFirstName(name);
                        String doctor1 = doctorService.updateById(id7, doctor);
                        System.out.println("Successfully updated" + doctor1);
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error " + e.getMessage());
                    }
                    break;
                case 12:
                    try {
                        System.out.print("Writer department ID: ");
                        Long departmentIdForDoctors = scanner.nextLong();
                        System.out.println(doctorService.getAllDoctorsByDepartmentId(departmentIdForDoctors));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 13:
                    try{
                        scanner.nextLine();
                        System.out.print("Writer patient's first name: ");
                        String patientFirstName = scanner.nextLine();
                        System.out.print("Writer patient's last name: ");
                        String patientLastName = scanner.nextLine();
                        System.out.print("Writer patient's age: ");
                        int patientAge = scanner.nextInt();
                        System.out.print("Writer patient's gender (MALE/FEMALE): ");
                        scanner.nextLine();
                        String patientGenderInput = scanner.nextLine().toUpperCase();
                        Gender patientGender = Gender.valueOf(patientGenderInput);
                        Patient patient = new Patient(GeneratorId.patientId(), patientFirstName, patientLastName, patientAge, patientGender);
                        System.out.print("Writer hospital ID to add this patient: ");
                        Long hospitalIdForPatient = scanner.nextLong();
                        System.out.println(patientService.add(hospitalIdForPatient, patient));
                    } catch (Exception e) {
                        System.out.println(e.getMessage());
                    }
                case 14:
                    System.out.println("Writer patient id: ");
                    Long id8 = scanner1.nextLong();
                    System.out.println(patientService.getPatientById(id8));
                    break;
                case 15: System.out.println(patientService.getPatientByAge());
                    break;
                case 16:
                    System.out.println("asc or desc: ");
                    String asc = scanner.nextLine();
                    System.out.println(patientService.sortPatientsByAge(asc));
                    break;
                case 17:
                    System.out.println("Writer patient id: ");
                    Long id9 = scanner1.nextLong();
                    patientService.removeById(id9);
                    break;
                case 18:
                    try {
                        System.out.println("Writer hospital id: ");
                        Long id10= scanner.nextLong();
                        System.out.println(" Writer Department name: ");
                        String name = scanner.nextLine();
                        for (int i = 0; i < name.length(); i++) {
                            if (!Character.isLetter(name.charAt(i))) {
                                throw new IllegalArgumentException("Сан эмес соз жазыныз:");
                            }
                        }
                        List<Doctor> doctors1 = new ArrayList<>();
                        Department department1 = new Department(id10, name, doctors1);
                        System.out.println(departmentService.add(id10,department1));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 19:
                    System.out.println("hospital id:");
                    Long id11 = scanner1.nextLong();
                    System.out.println(departmentService.getAllDepartmentByHospital(id11));
                    break;
                case 20:
                    try {
                        System.out.println("Writer department name:");
                        String name = scanner.nextLine();
                        for (int i = 0; i < name.length(); i++) {
                            if (!Character.isLetter(name.charAt(i))) {
                                throw new IllegalArgumentException("Сан эмес соз жазыныз:");
                            }
                        }
                        System.out.println(departmentService.findDepartmentByName(name));
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 21:
                    System.out.println("Writer department ID to delete: ");
                    Long id12 = scanner1.nextLong();
                    departmentService.removeById(id12);
                    break;
                case 22:
                    try {
                        System.out.println("Write id department to update: ");
                        Long id13 = scanner1.nextLong();
                        System.out.println("Write name for update:");
                        String name = scanner.nextLine();

                        for (int i = 0; i < name.length(); i++) {
                            if (!Character.isLetter(name.charAt(i))) {
                                throw new IllegalArgumentException("Сан эмес соз жазыныз");
                            }
                        }
                        String result = departmentService.updateById(id13, new Department(null, name, null));
                        System.out.println(result);
                        if (result.equals("Successfully updated")) {
                            for (Hospital hospital : Database.hospitals) {
                                for (Department department : hospital.getDepartments()) {
                                    if (department.getId().equals(id13)) {
                                        System.out.println("Updated department: " + department);
                                    }
                                }
                            }
                        }
                    } catch (IllegalArgumentException e) {
                        System.out.println(e.getMessage());
                    } catch (Exception e) {
                        System.out.println("Error: " + e.getMessage());
                    }
                    break;
                case 23:
                    System.out.print("Writer department ID: ");
                    Long departmentIdForAssignment = scanner.nextLong();
                    System.out.print("Writer doctor ID  (утур менен жазыныз): ");
                    String[] doctorIdsInput = scanner.nextLine().split(",");
                    List<Long> doctorIds = new ArrayList<>();
                    for (String id14 : doctorIdsInput) {
                        doctorIds.add(Long.parseLong(id14.trim()));
                    }
                    System.out.println(doctorService.assignDoctorToDepartment(departmentIdForAssignment, doctorIds));
                    break;

}}}}