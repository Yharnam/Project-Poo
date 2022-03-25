package Mains;

import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2022-03-25T16:36:14")
@StaticMetamodel(IssueBookDetails.class)
public class IssueBookDetails_ { 

    public static volatile SingularAttribute<IssueBookDetails, Integer> studentId;
    public static volatile SingularAttribute<IssueBookDetails, String> studentName;
    public static volatile SingularAttribute<IssueBookDetails, Date> dueDate;
    public static volatile SingularAttribute<IssueBookDetails, Integer> id;
    public static volatile SingularAttribute<IssueBookDetails, Date> issueDate;
    public static volatile SingularAttribute<IssueBookDetails, String> bookName;
    public static volatile SingularAttribute<IssueBookDetails, Integer> bookId;
    public static volatile SingularAttribute<IssueBookDetails, String> status;

}