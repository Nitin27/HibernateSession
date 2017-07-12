import javax.persistence.*;

@Entity
@Table
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int bookId;
    private String bookName;
//    @ManyToOne
//    @JoinColumn(name = "authorId" , referencedColumnName = "id")
//    Author author;

//    public Author getAuthor() {
//        return author;
//    }
//
//    public void setAuthor(Author author) {
//        this.author = author;
//    }

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
