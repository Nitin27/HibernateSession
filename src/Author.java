import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
//Automatic Id generation using table generation strategy as identity
    private int id;
    private String firstName;
    private String lastName;
    private int age;
    @ElementCollection
    private List<String> subjects;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "book_ID")
    private Book book;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public void setId(int id) {

        this.id = id;
    }

    public Address getAddress() {
        return address;
    }

    public void setAddress(Address address) {
        this.address = address;
    }

    @Temporal(TemporalType.DATE)//Temporal annotation used for date type
    private Date dateOfBirth;
    @Embedded
    private Address address;

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

    public int getId() {
        return id;
    }

    @Override
    public String toString() {
        return "Id: " + id + "  FirstName: " + firstName +
                "    LastName: " + lastName + "  Age: " + age +
                "    DateOfBirth: " + dateOfBirth +
                "    \nAddress: "+address+ "  Subject: "+subjects+
                "    Book: "+book;
    }
}
