package ui;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.util.List;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;

import dao.StudentDAO;
import model.Student;
import service.GradeUtil;
import service.ValidationService;

public class MainFrame extends JFrame {
	private static final long serialVersionUID = 1L;
	private String role;
	private JTable table;
	private DefaultTableModel tableModel;
	private JTextField idField, nameField, assignField, examField;
	private JButton addButton;
	
	public MainFrame(String role) {
		this.role = role;
		setTitle("Student Grading & Attendance Tracker - Logged in as " + role);
		setSize(600, 400);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);
		
		setupUI();
		loadTableData();
	}
	
	private void setupUI() {
		String[] columns = {"ID", "Name", "Assignment", "Exam", "Grade"};
		tableModel = new DefaultTableModel(columns, 0);
		table = new JTable(tableModel);
		
		JScrollPane scrollPane = new JScrollPane(table);
		
		JPanel inputPanel = new JPanel(new GridLayout(2, 5));
		idField = new JTextField(); nameField = new JTextField();
		assignField = new JTextField(); examField = new JTextField();
		addButton = new JButton("Add");
		
		inputPanel.add(new JLabel("ID")); inputPanel.add(new JLabel("Name"));
		inputPanel.add(new JLabel("Assignment")); inputPanel.add(new JLabel("Exam"));
		inputPanel.add(new JLabel());
		inputPanel.add(idField); inputPanel.add(nameField);
		inputPanel.add(assignField); inputPanel.add(examField);
		inputPanel.add(addButton);
		
		if (!"ADMIN".equals(role)) {
			addButton.setEnabled(false); // or setVisible(false)
		}
		
		add(scrollPane, BorderLayout.CENTER);
		add(inputPanel, BorderLayout.SOUTH);
		
		addButton.addActionListener(e -> handleAddStudent());
	}
	
	private void handleAddStudent() {
		Student s = new Student(
				idField.getText(),
				nameField.getText(),
				parseDouble(assignField.getText()),
				parseDouble(examField.getText())
				);
		
		if (!ValidationService.isValidStudent(s)) {
			JOptionPane.showMessageDialog(this, "Invalid input!", "Error", JOptionPane.ERROR_MESSAGE);
			return;
		}
		
		boolean added = new StudentDAO().addStudent(s);
		if (added) {
			loadTableData();
			clearFields();
		} else {
			JOptionPane.showMessageDialog(this, "Failed to add student.", "Error", JOptionPane.ERROR_MESSAGE);
		}
	}
	
	private void loadTableData() {
		tableModel.setRowCount(0);
		List<Student> students = new StudentDAO().getAllStudents();
		
		for (Student s : students) {
			String grade = GradeUtil.calculateGrade(s.getAssignScore(), s.getExamScore());
			tableModel.addRow(new Object[] {
					s.getId(), s.getName(), s.getAssignScore(), s.getExamScore(), grade
			});
		}
	}
	
	private double parseDouble(String value) {
		try { return Double.parseDouble(value); }
		catch (NumberFormatException e) { return -1; }
	}
	
	private void clearFields() {
		idField.setText(""); nameField.setText("");
		assignField.setText(""); examField.setText("");
	}
}
