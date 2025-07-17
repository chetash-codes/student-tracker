package model;

public class Student {
	private String id;
	private String name;
	private double assignScore;
	private double examScore;
	
	public Student(String id, String name, double assignScore, double examScore) {
		this.setId(id);
		this.setName(name);
		this.setAssignScore(assignScore);
		this.setExamScore(examScore);
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public double getAssignScore() {
		return assignScore;
	}

	public void setAssignScore(double assignScore) {
		this.assignScore = assignScore;
	}

	public double getExamScore() {
		return examScore;
	}

	public void setExamScore(double examScore) {
		this.examScore = examScore;
	}
}
