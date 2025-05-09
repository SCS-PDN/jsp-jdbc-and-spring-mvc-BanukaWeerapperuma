package com.university.controller;

import com.university.dao.CourseDAO;
import com.university.model.Course;
import com.university.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
public class CourseController {

    @Autowired
    private CourseDAO courseDAO;

    @GetMapping("/courses")
    public String showCourses(Model model, HttpSession session) {
        List<Course> courses = courseDAO.getAllCourses();
        model.addAttribute("courses", courses);
        return "courses";
    }

    @PostMapping("/register/{courseId}")
    public String registerCourse(@PathVariable int courseId, HttpSession session, Model model) {
        Student student = (Student) session.getAttribute("student");
        if (student == null) {
            return "redirect:/login";
        }

        boolean success = courseDAO.registerStudent(courseId, student.getStudentId());
        if (success) {
            return "success";
        } else {
            model.addAttribute("error", "You have already registered for this course.");
            return "courses";
        }
    }
}
