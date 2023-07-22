package com.pragma.boulevard_microservice.infrastructure.out.jpa.adapter;

import com.pragma.boulevard_microservice.domain.model.EmployeeModel;
import com.pragma.boulevard_microservice.domain.spi.IEmployeePersistencePort;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.mapper.IEmployeeEntityMapper;
import com.pragma.boulevard_microservice.infrastructure.out.jpa.repository.IEmployeeRepository;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public class EmployeeJpaAdapter implements IEmployeePersistencePort {

    private final IEmployeeRepository iEmployeeRepository;
    private final IEmployeeEntityMapper iEmployeeEntityMapper;
    @Override
    public EmployeeModel findByIdEmployee(Long employeeId) {
        return iEmployeeEntityMapper.toModel(iEmployeeRepository.findByIdEmployee(employeeId));
    }

}
