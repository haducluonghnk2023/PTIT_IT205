package com.data.session03.controller;

import com.data.session03.model.entity.Employee;
import com.data.session03.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.*;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/employees")
public class EmployeeController {

    @Autowired
    private EmployeeRepository employeeRepository;

    // ✅ Hiển thị danh sách nhân viên (HTML)
    @GetMapping
    public String listEmployees(
            Model model,
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "5") int size,
            @RequestParam(defaultValue = "id") String sort,
            @RequestParam(defaultValue = "asc") String dir,
            @RequestParam(required = false) String phone
    ) {
        Sort.Direction direction = dir.equalsIgnoreCase("desc") ? Sort.Direction.DESC : Sort.Direction.ASC;
        Pageable pageable = PageRequest.of(page, size, Sort.by(direction, sort));

        Page<Employee> employeePage;
        if (phone != null && !phone.isEmpty()) {
            List<Employee> filtered = employeeRepository.findByPhoneNumber(phone);
            employeePage = new PageImpl<>(filtered, pageable, filtered.size());
        } else {
            employeePage = employeeRepository.findAll(pageable);
        }

        model.addAttribute("employees", employeePage.getContent());
        model.addAttribute("totalPages", employeePage.getTotalPages());
        model.addAttribute("currentPage", page);
        model.addAttribute("param", new ParamWrapper(phone, sort, dir)); // giữ filter

        return "list-employees"; // trả về Thymeleaf view list.html
    }

    // ✅ Trang form thêm nhân viên
    @GetMapping("/new")
    public String showAddForm(Model model) {
        model.addAttribute("employee", new Employee());
        return "form";
    }

    // ✅ Xử lý thêm nhân viên (form HTML)
    @PostMapping("/save")
    public String saveEmployee(@ModelAttribute Employee employee) {
        employeeRepository.save(employee);
        return "redirect:/employees";
    }

    // ✅ Hiển thị chi tiết nhân viên
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable Long id, Model model) {
        Employee employee = employeeRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Không tìm thấy nhân viên"));
        model.addAttribute("employee", employee);
        return "form";
    }

    // ✅ Xóa nhân viên theo ID (form HTML)
    @GetMapping("/delete/{id}")
    public String deleteEmployeeById(@PathVariable Long id) {
        if (!employeeRepository.existsById(id)) {
            throw new RuntimeException("Không tìm thấy nhân viên có ID: " + id);
        }
        employeeRepository.deleteById(id);
        return "redirect:/employees";
    }

    // Wrapper giữ filter
    public static class ParamWrapper {
        public String phone, sort, dir;
        public ParamWrapper(String phone, String sort, String dir) {
            this.phone = phone;
            this.sort = sort;
            this.dir = dir;
        }
        public String getPhone() { return phone; }
        public String getSort() { return sort; }
        public String getDir() { return dir; }
    }
}
