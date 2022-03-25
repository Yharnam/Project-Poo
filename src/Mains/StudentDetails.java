/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mains;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tutu5
 */
@Entity
@Table(name = "student_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "StudentDetails.findAll", query = "SELECT s FROM StudentDetails s"),
    @NamedQuery(name = "StudentDetails.findByStudentId", query = "SELECT s FROM StudentDetails s WHERE s.studentId = :studentId"),
    @NamedQuery(name = "StudentDetails.findByName", query = "SELECT s FROM StudentDetails s WHERE s.name = :name"),
    @NamedQuery(name = "StudentDetails.findByCourse", query = "SELECT s FROM StudentDetails s WHERE s.course = :course"),
    @NamedQuery(name = "StudentDetails.findByBranch", query = "SELECT s FROM StudentDetails s WHERE s.branch = :branch")})
public class StudentDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "name")
    private String name;
    @Column(name = "course")
    private String course;
    @Column(name = "branch")
    private String branch;

    public StudentDetails() {
    }

    public StudentDetails(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getBranch() {
        return branch;
    }

    public void setBranch(String branch) {
        this.branch = branch;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (studentId != null ? studentId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof StudentDetails)) {
            return false;
        }
        StudentDetails other = (StudentDetails) object;
        if ((this.studentId == null && other.studentId != null) || (this.studentId != null && !this.studentId.equals(other.studentId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mains.StudentDetails[ studentId=" + studentId + " ]";
    }
    
}
