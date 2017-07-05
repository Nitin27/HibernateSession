import javax.persistence.*;
import java.util.Date;

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "AuthorID")
    private int id;
    @Column(name = "First_Name")
    private String firstName;
    @Transient
    @Column(name="Last_Name")
    private String lastName;
    @Column(name = "Age")
    private int age;
    @Column(name = "Date_Of_Birth")
    @Temporal(TemporalType.DATE)//Temporal annotation used for date type
    private Date dateOfBirth;

    public Author() {

    }

    public Date getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(Date dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getId(){
        return id;
    }

    @Override
    public String toString() {
        return "Id: "+id+"  FirstName: "+firstName+"    LastName: "+lastName+"  Age: "+age+"    DateOfBirth: "+dateOfBirth;
    }
}
