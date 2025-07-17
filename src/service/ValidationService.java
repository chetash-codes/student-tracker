package service;

import model.Student;

public class ValidationService {
	public static boolean isValidStudent(Student s) {
		if (s == null) return false;
		
		boolean validId = s.getId() != null && !s.getId().trim().isEmpty();
		boolean validName = s.getName() != null && !s.getName().trim().isEmpty();
		boolean validAssignment = s.getAssignScore() >= 0 && s.getAssignScore() <= 100;
		boolean validExam = s.getExamScore() >= 0 && s.getExamScore() <= 100;
		
		return validId && validName && validAssignment && validExam;
	}
}
