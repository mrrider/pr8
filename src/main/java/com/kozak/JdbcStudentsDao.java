package com.kozak;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class JdbcStudentsDao implements StudentsDao {

	private static final String SQL_INSERT_STUDENT = "insert into students (pib,course) values (?,?)";

	@Autowired
	private SimpleJdbcTemplate jdbcTemplate;

	public void addStudent(Student student) {
		jdbcTemplate.update(SQL_INSERT_STUDENT, student.getPib(), student.getCourse());
	}

	@Override
	public Student getStudentById(int id) {
		// TODO Auto-generated method stub
		return null;
	}
}