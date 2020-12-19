package com.lhs.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.lhs.dao.EmployeeRepository;
import com.lhs.model.TestTraineeEmpMast;

@Service
public class EmployeeService {

	@Autowired
	EmployeeRepository employeeRepository;

	public boolean findByUsernameAndPassword(String username, String password) {
		return employeeRepository.findByUsernameAndPassword(username, password);
	}

	public List<TestTraineeEmpMast> showAllEmployee() {
		List<TestTraineeEmpMast> users = new ArrayList<TestTraineeEmpMast>();
		for (TestTraineeEmpMast testTraineeEmpMast : employeeRepository.showAllEmployee()) {
			users.add(testTraineeEmpMast);
		}

		return users;
	}

	public void saveMyUser(TestTraineeEmpMast testTraineeEmpMast) {
		employeeRepository.saveUser(testTraineeEmpMast);
	}

	public TestTraineeEmpMast editUser(int id) {
		return employeeRepository.findOneEmployee(id);
	}

	public void updateUser(TestTraineeEmpMast testTraineeEmpMast) {
		employeeRepository.updateUser(testTraineeEmpMast);

	}

	public List<TestTraineeEmpMast> showFilter(String code, String ename, String mobile, String reg_date) {
		List<TestTraineeEmpMast> users = null;
		users = employeeRepository.showFilter(code, ename, mobile, reg_date);
		return users;
	}

	public void deleteMyUser(int id) {
		employeeRepository.deleteEmployee(id);
	}
}
