

import java.io.Serializable;
/**
 *  @authors
 *  1.Asimbonge Mbende(221090754)
 *  2.Thandolwethu Zamasiba Khoza(221797289)
 *  3.Sbonga Shweni(219143188)
 */

public class Subject implements Serializable {

    private String subjectCode;
    private String subjectTitle;

    public Subject() {
    }
 public Subject(String subjectCode) {
        this.subjectCode = subjectCode;
    }
    public Subject(String subjectCode, String subjectTitle) {
        this.subjectCode = subjectCode;
        this.subjectTitle = subjectTitle;
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
        return "Subject{" + "subjectCode=" + subjectCode + ", subjectTitle=" + subjectTitle + '}';
    }
    
}
