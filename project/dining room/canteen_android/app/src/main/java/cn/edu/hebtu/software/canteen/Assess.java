package cn.edu.hebtu.software.canteen;

public class Assess {
    private String assessContent;
    private int studentName;
    private int foodName;

    public String getAssessContent() {
        return assessContent;
    }

    public void setAssessContent(String assessContent) {
        this.assessContent = assessContent;
    }

    public int getStudentName() {
        return studentName;
    }

    public void setStudentName(int studentName) {
        this.studentName = studentName;
    }

    public int getFoodName() {
        return foodName;
    }

    public void setFoodName(int foodName) {
        this.foodName = foodName;
    }

    @Override
    public String toString() {
        return "Assess{" +
                "assessContent='" + assessContent + '\'' +
                ", studentName=" + studentName +
                ", foodName=" + foodName +
                '}';
    }
}
