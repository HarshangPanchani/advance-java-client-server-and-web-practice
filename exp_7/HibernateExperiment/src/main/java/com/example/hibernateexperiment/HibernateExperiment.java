package com.example.hibernateexperiment;

import com.example.model.Student;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import java.util.List;

public class HibernateExperiment {
    public static void main(String[] args) {
        // Create SessionFactory
        SessionFactory sessionFactory = new Configuration()
                .configure("hibernate.cfg.xml")
                .buildSessionFactory();

        // Create Session
        Session session = sessionFactory.openSession();

        try {
            // Begin transaction
            session.beginTransaction();

            // Save a new student
            Student student = new Student(4, "David", 21);
            session.persist(student);
            System.out.println("Saved student: " + student);

            // HQL query (replacing SQL: SELECT * FROM STUDENT WHERE AGE > 18)
            String hql = "FROM Student WHERE age > :age";
            Query<Student> query = session.createQuery(hql, Student.class);
            query.setParameter("age", 18);
            List<Student> students = query.list();

            // Print results
            System.out.println("Students with age > 18:");
            for (Student s : students) {
                System.out.println(s);
            }

            // Commit transaction
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            session.getTransaction().rollback();
        } finally {
            session.close();
            sessionFactory.close();
        }
    }
}