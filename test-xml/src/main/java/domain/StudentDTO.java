package domain;

public class StudentDTO {

    private String school;
    private int score;

    public String getSchool() {
        return school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }


    @Override
    public String toString() {
        return "StudentDTO{" +
                "school='" + school + '\'' +
                ", score=" + score +
                '}';
    }
}
