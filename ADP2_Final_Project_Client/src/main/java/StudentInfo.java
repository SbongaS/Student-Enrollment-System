
import java.io.Serializable;

/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class StudentInfo implements Serializable {
    private String studentNumber,name,surname,subjectCode,subjectTitle;

    public StudentInfo(String studentNumber, String name, String surname, String subjectCode, String subjectTitle) {
        this.studentNumber = studentNumber;
        this.name = name;
        this.surname = surname;
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public String getName() {
        return name;
    }

    public String getSurname() {
        return surname;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }
    
    
}
