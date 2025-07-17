package dao;

import java.sql.*;
import java.util.ArrayList;

import model.Student;
import util.DBConnector;

public class StudentDAO {
	
	// Add a new student
	public boolean addStudent(Student s) {
		String sql = "INSERT INTO students (id, name, assignment, exam) VALUES (?, ?, ?, ?)";
		try (Connection conn = DBConnector.getConnection();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			stmt.setString(1, s.getId());
			stmt.setString(2, s.getName());
			stmt.setDouble(3, s.getAssignScore());
			stmt.setDouble(4, s.getExamScore());
			
			return stmt.executeUpdate() > 0;
			
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public ArrayList<Student> getAllStudents() {
		ArrayList<Student> students = new ArrayList<>();
		String sql = "SELECT * FROM students";
		
		try (Connection conn = DBConnector.getConnection();
				Statement stmt = conn.createStatement();
				ResultSet rs = stmt.executeQuery(sql)) {
			
			while (rs.next()) {
				Student s = new Student(
						rs.getString("id"),
						rs.getString("name"),
						rs.getDouble("assignment"),
						rs.getDouble("exam")
				);
				students.add(s);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		return students;
	}
}
