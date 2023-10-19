
import java.io.Serializable;

/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */
public class EnrolmentPOJO implements Serializable {
    private String studentNumber;
    private String subjectCode;
    private String subjectTitle;

    public EnrolmentPOJO() {

    }

    public EnrolmentPOJO(String subjectCode, String subjectTitle) {
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
    }

    public EnrolmentPOJO(String studentNumber, String subjectCode, String subjectTitle) {
        this.studentNumber = studentNumber;
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
    }

    public String getStudentNumber() {
        return studentNumber;
    }

    public void setStudentNumber(String studentNumber) {
        this.studentNumber = studentNumber;
    }

    public String getSubjectCode() {
        return subjectCode;
    }

    public void setSubjectCode(String subjectCode) {
        this.subjectCode = subjectCode;
    }

    public String getSubjectTitle() {
        return subjectTitle;
    }

    public void setSubjectTitle(String subjectTitle) {
        this.subjectTitle = subjectTitle;
    }

    @Override
    public String toString() {
        return  studentNumber + " " + subjectCode + " " + subjectTitle + "\n";
    }

}
