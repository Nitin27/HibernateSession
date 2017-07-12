import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int bookId;
    private String bookName;
    @ManyToMany
    Collection<Author> author =new ArrayList<>();

    public Collection<Author> getAuthor() {
        return author;
    }

    public void setAuthor(Collection<Author> author) {
        this.author = author;
    }

    public Book() {
    }

    public int getBookId() {
        return bookId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    @Override
    public String toString() {
        return "BOOK ID : " + bookId + "    BOOK NAME : "+bookName;
    }
}
