package com.university.dao;

import com.university.model.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository
public class CourseDAO {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Course> getAllCourses() {
        String sql = "SELECT * FROM courses";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            Course course = new Course();
            course.setCourseId(rs.getInt("course_id"));
            course.setName(rs.getString("name"));
            course.setInstructor(rs.getString("instructor"));
            course.setCredits(rs.getInt("credits"));
            return course;
        });
    }

    public boolean registerStudent(int courseId, int studentId) {
        // check if already registered
        String checkSql = "SELECT COUNT(*) FROM registrations WHERE course_id = ? AND student_id = ?";
        Integer count = jdbcTemplate.queryForObject(checkSql, new Object[]{courseId, studentId}, Integer.class);
        if (count != null && count > 0) {
            return false; // already registered
        }

        // register student
        String insertSql = "INSERT INTO registrations (student_id, course_id, date) VALUES (?, ?, ?)";
        int rows = jdbcTemplate.update(insertSql, studentId, courseId, new Timestamp(System.currentTimeMillis()));
        return rows > 0;
    }
}
