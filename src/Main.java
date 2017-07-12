import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class Main {
    private static final SessionFactory ourSessionFactory;

    static {
        try {
            Configuration configuration = new Configuration();
            configuration.configure();

            ourSessionFactory = configuration.buildSessionFactory();
        } catch (Throwable ex) {
            throw new ExceptionInInitializerError(ex);
        }
    }

    private static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    void addAuthor(String fName, String lName, int age, String dob, String streetNo, String location, String state, List<String> subject) {
        try (Session session = getSession()) {
            Author author = new Author();
            Book b1 = new Book();
            Book b2 = new Book();
            b1.setBookName("Oliver Twist");
            b2.setBookName("Christmas Carol");
            author.getBook().add(b1);
            author.getBook().add(b2);

            author.setFirstName(fName);
            author.setLastName(lName);
            author.setAge(age);

            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd/M/yyyy");
            author.setDateOfBirth(simpleDateFormat.parse(dob));

            Address address = new Address();
            address.setStreetNo(streetNo);
            address.setLocation(location);
            address.setState(state);
            author.setAddress(address);

            author.setSubjects(subject);

            session.beginTransaction();
//            session.save(b1);
//            session.save(b2);
            session.save(author);

            session.getTransaction().commit();
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }

    void updateAuthor() {
        Author author;
        try (Session session = getSession()) {
            session.beginTransaction();
            author = session.get(Author.class, 1);
            author.setAge(45);
            session.update(author);
            session.getTransaction().commit();
        }
    }

    void deleteAuthor() {
        Author author;
        try (Session session = getSession()) {
            session.beginTransaction();
            author = session.get(Author.class, 2);//Record 2 deleted
            session.delete(author);
            session.getTransaction().commit();
        }
    }

    void readAuthor() {
        try (Session session = getSession()) {
            session.beginTransaction();
            Author author = session.get(Author.class, 1);
            System.out.println(author);
        }
    }

    static void queryDB() {
        try (Session session = getSession()) {
            System.out.println("querying all the managed entities...");
            final Metamodel metamodel = session.getSessionFactory().getMetamodel();
            for (EntityType<?> entityType : metamodel.getEntities()) {
                final String entityName = entityType.getName();
                final Query query = session.createQuery("from " + entityName);
                System.out.println("executing: " + query.getQueryString());
                for (Object o : query.list()) {
                    System.out.println("  " + o);
                }
            }
        }
    }

    Book addBook(String bookName) {
        Book book = new Book();
        book.setBookName(bookName);
        return book;
    }

    public static void main(final String[] args) throws Exception {
        Main crud = new Main();

        List<String> subjectList = new ArrayList<>();

        subjectList.add("Maths");
        subjectList.add("English");
        subjectList.add("Hindi");

        queryDB();

        crud.addAuthor("Amit", "Shah", 40, "23/10/1975", "10", "ashok vihar", "delhi", subjectList);
        crud.addAuthor("Sumit", "Singh", 50, "23/10/1965", "10", "ashok vihar", "delhi", subjectList);
        crud.addAuthor("Naman", "Shah", 20, "23/10/1995", "10", "ashok vihar", "delhi", subjectList);

        queryDB();

        crud.updateAuthor();

        crud.readAuthor();

        crud.deleteAuthor();

        queryDB();

        //ourSessionFactory.close();
    }
}