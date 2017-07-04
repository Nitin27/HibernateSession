import org.hibernate.*;
import org.hibernate.query.Query;
import org.hibernate.cfg.Configuration;

import javax.persistence.metamodel.EntityType;
import java.text.ParseException;
import java.text.SimpleDateFormat;


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

    static Session getSession() throws HibernateException {
        return ourSessionFactory.openSession();
    }

    void addAuthor() {
        try (Session session = getSession()) {
            Author author = new Author();
            author.setFirstName("Amit");
            author.setLastName("Shah");
            author.setAge(40);
            SimpleDateFormat simpleDateFormat=new SimpleDateFormat("dd/M/yyyy");
            author.setDateOfBirth(simpleDateFormat.parse("23/10/1945"));
            session.beginTransaction();
            session.save(author);
            session.getTransaction().commit();
        }catch (ParseException e){e.printStackTrace();}
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
            author = session.get(Author.class, 1);
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

    public static void main(final String[] args) throws Exception {
        Main crud = new Main();
        queryDB();
        crud.addAuthor();
        crud.readAuthor();
        crud.updateAuthor();
        crud.readAuthor();
        crud.deleteAuthor();
        crud.readAuthor();
        ourSessionFactory.close();
    }
}