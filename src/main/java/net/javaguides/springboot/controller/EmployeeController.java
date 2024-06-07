package net.javaguides.springboot.controller;

import net.javaguides.springboot.model.Employee;
import net.javaguides.springboot.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	// Mapping to show the form for adding a new employee
	@GetMapping("/add")
	public String showAddEmployeeForm(Model model) {
		model.addAttribute("employee", new Employee());
		return "add-employee-form";
	}

	// Mapping to handle the submission of the form for adding a new employee
	@PostMapping("/add")
	public String addEmployee(@ModelAttribute("employee") Employee employee) {
		employeeService.createEmployee(employee);
		return "redirect:/employees/list";
	}

	// Mapping to show the form for updating an employee
	@GetMapping("/update/{id}")
	public String showUpdateEmployeeForm(@PathVariable Long id, Model model) {
		Employee employee = employeeService.getEmployeeById(id);
		model.addAttribute("employee", employee);
		return "update-employee-form";
	}

	// Mapping to handle the submission of the form for updating an employee
	@PostMapping("/update/{id}")
	public String updateEmployee(@PathVariable Long id, @ModelAttribute("employee") Employee employeeDetails) {
		Employee employee = employeeService.getEmployeeById(id);
		employee.setFirstName(employeeDetails.getFirstName());
		employee.setLastName(employeeDetails.getLastName());
		employee.setEmail(employeeDetails.getEmail());
		employee.setSkills(employeeDetails.getSkills());
		employee.setProjects(employeeDetails.getProjects());
		employeeService.updateEmployee(employee);
		return "redirect:/employees/list";
	}

	// Mapping to handle deleting an employee
	@GetMapping("/delete/{id}")
	public String deleteEmployee(@PathVariable Long id) {
		employeeService.deleteEmployee(id);
		return "redirect:/employees/list";
	}

	// Mapping to list all employees
	@GetMapping("/list")
	public String listEmployees(Model model) {
		List<Employee> employees = employeeService.getAllEmployees();
		model.addAttribute("employees", employees);
		return "list-employees";
	}
}
