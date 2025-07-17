package service;

public class GradeUtil {
	public static String calculateGrade(double assignScore, double examScore) {
		double weightedScore = assignScore * 0.4 + examScore * 0.6;
		
		if (weightedScore >= 90) return "A";
		else if (weightedScore >= 75) return "B";
		else if (weightedScore >= 60) return "C";
		else return "D";
	}
}
