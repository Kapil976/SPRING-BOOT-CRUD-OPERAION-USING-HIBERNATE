package com.lhs.controller;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.lhs.model.TestTraineeEmpMast;
import com.lhs.model.TestTraineeUserMast;
import com.lhs.service.EmployeeService;

@Controller
@MultipartConfig
public class HomeController {

	@Autowired
	EmployeeService employeeService;
	@Autowired
	SessionFactory sessionFactory;

	@GetMapping(value = "/")
	public String home() {
		System.out.println("called login");
		return "Login";
	}

	@PostMapping("/login-user")
	public String loginUser(@ModelAttribute TestTraineeUserMast testTraineeUserMast, HttpServletRequest request) {
		System.out.println("called login user");
		if (employeeService.findByUsernameAndPassword(testTraineeUserMast.getUSER_NAME(),
				testTraineeUserMast.getPASSWORD())) {
			HttpSession session = request.getSession();
			request.setAttribute("users", employeeService.showAllEmployee());
			session.setAttribute("Login", testTraineeUserMast.getUSER_NAME());
			return "DashBoard";
		} else {
			request.setAttribute("error", "Invalid Username or Password");
			return "Login";

		}
	}

	@GetMapping(value = "/register")
	public String registerHome(HttpServletRequest request) {
		System.out.println("called register");
		request.setAttribute("register", "register");
		return "Register";
	}

	@PostMapping("/save-user")
	public String registerUser(@ModelAttribute TestTraineeEmpMast testTraineeEmpMast, BindingResult bindingResult,
			HttpServletRequest request, @RequestParam MultipartFile EMP_PHOTO, HttpSession session)
			throws IOException, ServletException {

		String path = session.getServletContext().getRealPath("/");
		String filename = EMP_PHOTO.getOriginalFilename();

		System.out.println(path + " " + filename);
		byte[] barr = null;
		try {
			barr = EMP_PHOTO.getBytes();

			BufferedOutputStream bout = new BufferedOutputStream(new FileOutputStream(path + "/" + filename));
			System.out.println("path:"+bout);
			bout.write(barr);
			bout.flush();
			bout.close();

		} catch (Exception e) {
			System.out.println(e);
		}
		String BIRTH_DATE = request.getParameter("BIRTH_DATE");
		// convert file into array of bytes
		Date date = null;
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			date = simpleDateFormat.parse(BIRTH_DATE);
		} catch (ParseException e) {
			date = new Date();
			e.printStackTrace();
		}

		System.out.println("Date--->" + testTraineeEmpMast.getBIRTH_DATE());
		session = request.getSession(false);
		String login = (String) session.getAttribute("Login");
		System.out.println("value of login" + login);
		testTraineeEmpMast.setLOGIN_USER(login);
		testTraineeEmpMast.setBIRTH_DATE(date);
		testTraineeEmpMast.setEMP_PHOTO(barr);
		testTraineeEmpMast.setREG_DATE(new Date());
		employeeService.saveMyUser(testTraineeEmpMast);
		request.setAttribute("users", employeeService.showAllEmployee());
		return "DashBoard";
	}

	@RequestMapping("/edit-user")
	public String editUser(@RequestParam int id, HttpServletRequest request, TestTraineeEmpMast testTraineeEmpMast) {
		request.setAttribute("user", employeeService.editUser(id));
//		request.setAttribute("hideImage", "image");
		request.setAttribute("edit", "edit");
		return "Register";
	}

	@PostMapping("/update")
	public String updateEmployee(@RequestParam int id, TestTraineeEmpMast testTraineeEmpMast,
			BindingResult bindingResult, HttpServletRequest request) {
		String BIRTH_DATE = request.getParameter("BIRTH_DATE");
		Date bdate = null;
		System.out.println("BIRTH_DATE--------" + BIRTH_DATE);
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			bdate = simpleDateFormat.parse(BIRTH_DATE);
		} catch (ParseException e) {
			bdate = new Date();
			e.printStackTrace();
		}
		String REG_DATE = request.getParameter("REG_DATE");
		Date rdate = null;
		System.out.println("REG_DATE--------" + BIRTH_DATE);
		try {
			SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
			rdate = simpleDateFormat.parse(REG_DATE);
		} catch (ParseException e) {
			rdate = new Date();
			e.printStackTrace();
		}
		HttpSession session = request.getSession(false);
		String login = (String) session.getAttribute("Login");
		testTraineeEmpMast.setBIRTH_DATE(bdate);
		testTraineeEmpMast.setREG_DATE(rdate);
		testTraineeEmpMast.setLOGIN_USER(login);
		employeeService.updateUser(testTraineeEmpMast);
		request.setAttribute("users", employeeService.showAllEmployee());
		return "DashBoard";
	}

	@RequestMapping("/filter")
	public String editEmployeer(HttpServletRequest request, ModelMap map) {
		String DESIG_CODE = request.getParameter("DESIG_CODE");
		String EMP_NAME = request.getParameter("EMP_NAME");
		String MOBILE = request.getParameter("MOBILE");
		String REG_DATE = request.getParameter("REG_DATE");
		if (DESIG_CODE != null || EMP_NAME != "" || MOBILE != "" || REG_DATE != "") {
			List<TestTraineeEmpMast> list = employeeService.showFilter(DESIG_CODE, EMP_NAME, MOBILE, REG_DATE);
			if (list.size() > 0 && list != null) {
				map.addAttribute("users", list);
				return "DashBoard";
			} else {
				request.setAttribute("noFilterFound", "**No Data Found");
				return "DashBoard";
			}
		} else

		{
			request.setAttribute("filter", "**Please select one or more Filter");
			return "DashBoard";
		}
	}

	@RequestMapping("/showAll")
	public String showAllEmployee(HttpServletRequest request, ModelMap map) {
		request.setAttribute("users", employeeService.showAllEmployee());
		return "DashBoard";
	}

	@RequestMapping("/delete-user")
	public String deleteUser(@RequestParam int id, HttpServletRequest request) {
		employeeService.deleteMyUser(id);
		request.setAttribute("users", employeeService.showAllEmployee());
		request.setAttribute("delete", "**Delete data Successfully");
		request.setAttribute("users", employeeService.showAllEmployee());
		return "DashBoard";
	}

}
