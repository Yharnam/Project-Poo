/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Mains;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author tutu5
 */
@Entity
@Table(name = "issue_book_details")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "IssueBookDetails.findAll", query = "SELECT i FROM IssueBookDetails i"),
    @NamedQuery(name = "IssueBookDetails.findById", query = "SELECT i FROM IssueBookDetails i WHERE i.id = :id"),
    @NamedQuery(name = "IssueBookDetails.findByBookId", query = "SELECT i FROM IssueBookDetails i WHERE i.bookId = :bookId"),
    @NamedQuery(name = "IssueBookDetails.findByBookName", query = "SELECT i FROM IssueBookDetails i WHERE i.bookName = :bookName"),
    @NamedQuery(name = "IssueBookDetails.findByStudentId", query = "SELECT i FROM IssueBookDetails i WHERE i.studentId = :studentId"),
    @NamedQuery(name = "IssueBookDetails.findByStudentName", query = "SELECT i FROM IssueBookDetails i WHERE i.studentName = :studentName"),
    @NamedQuery(name = "IssueBookDetails.findByIssueDate", query = "SELECT i FROM IssueBookDetails i WHERE i.issueDate = :issueDate"),
    @NamedQuery(name = "IssueBookDetails.findByDueDate", query = "SELECT i FROM IssueBookDetails i WHERE i.dueDate = :dueDate"),
    @NamedQuery(name = "IssueBookDetails.findByStatus", query = "SELECT i FROM IssueBookDetails i WHERE i.status = :status")})
public class IssueBookDetails implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "book_id")
    private Integer bookId;
    @Column(name = "book_name")
    private String bookName;
    @Column(name = "student_id")
    private Integer studentId;
    @Column(name = "student_name")
    private String studentName;
    @Column(name = "issue_date")
    @Temporal(TemporalType.DATE)
    private Date issueDate;
    @Column(name = "due_date")
    @Temporal(TemporalType.DATE)
    private Date dueDate;
    @Column(name = "status")
    private String status;

    public IssueBookDetails() {
    }

    public IssueBookDetails(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getBookId() {
        return bookId;
    }

    public void setBookId(Integer bookId) {
        this.bookId = bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Date getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(Date issueDate) {
        this.issueDate = issueDate;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof IssueBookDetails)) {
            return false;
        }
        IssueBookDetails other = (IssueBookDetails) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Mains.IssueBookDetails[ id=" + id + " ]";
    }
    
}
