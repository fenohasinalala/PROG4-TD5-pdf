package com.hei.project2p1.service;

import com.hei.project2p1.exception.BadRequestException;
import com.hei.project2p1.model.Employee;
import com.hei.project2p1.model.Phone;
import com.hei.project2p1.model.Validator.PhoneValidator;
import com.hei.project2p1.repository.EmployeeConnectorRepository;
import com.hei.project2p1.service.utils.AgeCalculator.AgeCalculator;
import com.hei.project2p1.utils.PaginationUtils;
import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Objects;

@Service
@AllArgsConstructor
public class EmployeeService {
    private final AgeCalculator ageCalculator;
    private final String REGISTRATION_PREFIX = "EMP";
    private final RegistrationNoTrackerService registrationNoTrackerService;

    private final EmployeeConnectorRepository employeeConnectorRepository;
    private final PhoneService phoneService;
    private final PhoneValidator phoneValidator;

    public Integer getAgeOfEmployee(Employee employee, String precision, Integer minInterval){
        enum Mode{
            BIRTHDAY,
            YEAR_ONLY,
            CUSTOM_DELAY
        }

        AgeCalculator.Precision agePrecision;
        if (Objects.equals(precision, Mode.BIRTHDAY.toString())){
            agePrecision = AgeCalculator.Precision.DAY;
        } else if (Objects.equals(precision, Mode.YEAR_ONLY.toString())) {
            agePrecision = AgeCalculator.Precision.YEAR;
        } else if (Objects.equals(precision, Mode.CUSTOM_DELAY.toString())) {
            agePrecision = AgeCalculator.Precision.CUSTOM;
        } else {
            throw new IllegalArgumentException("Precision [" + precision + "] not recognized.");
        }

        LocalDate birthDate = employee.getBirthDate();
        if (birthDate==null){
            return null;
        }
        return ageCalculator.getAge(birthDate,agePrecision,minInterval);
    }

    public Employee getEmployeeById(String id){
        return employeeConnectorRepository.findById(id);
    }

    public long getTotalPages(int pageSize){
        double totalCount = employeeConnectorRepository.count();
        return PaginationUtils.getTotalPages(totalCount,pageSize);
    }

    @Transactional
    public Employee save(Employee employee,List<String> countryCode, List<String> phonesNo) {
        Employee toSave = autoSetRegNo(employee);
        Employee saved = employeeConnectorRepository.save(toSave);
        List<Phone> phones = phoneService.addPhonesToOwner(saved,countryCode,phonesNo);
        phoneValidator.accept(phones);

        phones.forEach(phone -> {
            List<Phone> alreadyExist = phoneService.getByCodeAndNumber(phone.getCountryCode(),phone.getNumber());
            alreadyExist.forEach(p -> {
                if (!Objects.equals(p.getEmployeeId(), saved.getId())){
                    throw new BadRequestException("Phone with number "+p.getCountryCode()+p.getNumber()+" already exist");
                }
            });
        });
        phoneService.deletePhonesOfOwner(saved);
        phoneService.savePhones(saved,countryCode,phonesNo);
        return saved;
    }


    private Employee autoSetRegNo(Employee employee){
        if (employee.getRegistrationNo()==null){
            Long last = registrationNoTrackerService.getLastRegistrationNo();
            Long updatedNo = last + 1;
            employee.setRegistrationNo(REGISTRATION_PREFIX + (updatedNo));
            registrationNoTrackerService.updateLastNo(updatedNo);
        }
        return employee;
    }

    private List<Employee> autoSetRegNo(List<Employee> employeeList){
        Long last = registrationNoTrackerService.getLastRegistrationNo();
        for (Employee e : employeeList){
            if (e.getRegistrationNo()==null){
                e.setRegistrationNo(REGISTRATION_PREFIX + last);
                last += 1L;
            }
        }
        registrationNoTrackerService.updateLastNo(last);
        return employeeList;
    }

    @Transactional
    public List<Employee> findEmployeesByCriteria(String firstName,
                                                  String lastName,
                                                  String function,
                                                  String countryCode,
                                                  String gender,
                                                  LocalDate entranceDateAfter,LocalDate entranceDateBefore,
                                                  LocalDate leaveDateAfter, LocalDate leaveDateBefore,
                                                  int pageNo,
                                                  int pageSize,
                                                  String sortBy,  String sortOrder) {

        Sort.Direction direction = sortOrder.equalsIgnoreCase("DESC") ? Sort.Direction.DESC : Sort.Direction.ASC;

        // Define sorting criteria
        Sort sort = Sort.by(direction, sortBy);
        PaginationUtils.paginationValidator(pageNo,pageSize);
        // Create a Pageable object for pagination and sorting
        Pageable pageable = PageRequest.of(pageNo-1, pageSize, sort);

        // Perform the search using the EmployeeRepository
        return employeeConnectorRepository.findByCriteria(
                firstName, lastName,function,countryCode, gender,
                entranceDateAfter, entranceDateBefore, leaveDateAfter, leaveDateBefore, pageable);
    }
}
