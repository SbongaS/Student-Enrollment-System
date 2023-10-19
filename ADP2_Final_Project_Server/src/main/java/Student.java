

import java.io.Serializable;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class Student implements Serializable {

    private String studentNumber;
    private String studentName;
    private String studentSurname;
    private String password;
    private String gender;

    public Student() {
    }

    public Student(String studentNumber, String password) {
        this.studentNumber = studentNumber;
        this.password = password;
    }

    public Student(String studentNumber, String studentName, String studentSurname, String gender) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.gender = gender;
    }

    public Student(String studentNumber, String studentName, String studentSurname, String gender, String password) {
        this.studentNumber = studentNumber;
        this.studentName = studentName;
        this.studentSurname = studentSurname;
        this.gender = gender;
        this.password = password;

    }

   

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentSurname() {
        return studentSurname;
    }

    public void setStudentSurname(String studentSurname) {
        this.studentSurname = studentSurname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "Student{" + "studentNumber=" + studentNumber + ", studentName=" + studentName + ", studentSurname=" + studentSurname + ", gender=" + gender + ", password=" + password + '}';
    }

}
