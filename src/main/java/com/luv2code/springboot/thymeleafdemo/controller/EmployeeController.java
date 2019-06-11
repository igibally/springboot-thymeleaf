package com.luv2code.springboot.thymeleafdemo.controller;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.websocket.server.PathParam;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.luv2code.springboot.thymeleafdemo.entity.Employee;
import com.luv2code.springboot.thymeleafdemo.service.EmployeeService;

@Controller
@RequestMapping("employees")
public class EmployeeController {

	private List<Employee> employees;

	private EmployeeService employeeService;

	@Autowired
	public EmployeeController(EmployeeService theEmployeeService) {
		employeeService = theEmployeeService;
	}

	@GetMapping("/list")
	public String getEmployees(Model theModel) {

		this.employees=employeeService.findAll();
		theModel.addAttribute("Employeeslist", this.employees);
		return "employees/list-employees";
	}

	@GetMapping("/showFormForAdd")
	public String showFormForAdd(Model theModel) {

		Employee employee = new Employee();
		theModel.addAttribute("employee", employee);

		return "employees/employee-form";

	}

	@PostMapping("/save")
	public String saveEmployee(@ModelAttribute("employee") Employee theEmployee) {

		employeeService.save(theEmployee);

		return "redirect:/employees/list";

	}
	@GetMapping("/showFormForUpdate")
	public String showFormForUpdate(@RequestParam("employeeId") int employeeId,Model theModel) {
		
		Employee employee = employeeService.findById(employeeId);
		theModel.addAttribute("employee", employee);
		
		return "employees/employee-form";
		
		
	}
	
	@GetMapping("delete")
	public String delete(@RequestParam("employeeId") int employeeId) {
		employeeService.deleteById(employeeId);	
		return "redirect:/employees/list";
	}

}
