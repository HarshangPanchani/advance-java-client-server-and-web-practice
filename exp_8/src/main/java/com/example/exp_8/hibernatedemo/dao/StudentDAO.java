package com.example.exp_8.hibernatedemo.dao;

import com.example.exp_8.hibernatedemo.entity.Student;
import com.example.exp_8.hibernatedemo.util.HibernateUtil;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class StudentDAO {
    
    // Create a new student
    public void saveStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();
            
            // Save the student
            session.persist(student);
            
            // Commit the transaction
            transaction.commit();
            System.out.println("Student saved successfully, Student Details: " + student);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error saving student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Get student by ID
    public Student getStudentById(int id) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Student student = session.get(Student.class, id);
            if (student != null) {
                System.out.println("Student found: " + student);
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
            return student;
        } catch (Exception e) {
            System.err.println("Error retrieving student: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Get all students
    public List<Student> getAllStudents() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            List<Student> students = session.createQuery("FROM Student", Student.class).list();
            System.out.println("Total students: " + students.size());
            return students;
        } catch (Exception e) {
            System.err.println("Error retrieving all students: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Update student
    public void updateStudent(Student student) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();
            
            // Update the student
            session.merge(student);
            
            // Commit the transaction
            transaction.commit();
            System.out.println("Student updated successfully, Updated Student Details: " + student);
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error updating student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Delete student
    public void deleteStudent(int id) {
        Transaction transaction = null;
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            // Start the transaction
            transaction = session.beginTransaction();
            
            // Get the student
            Student student = session.get(Student.class, id);
            if (student != null) {
                session.remove(student);
                System.out.println("Student deleted successfully, Student Details: " + student);
            } else {
                System.out.println("Student with ID " + id + " not found.");
            }
            
            // Commit the transaction
            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            System.err.println("Error deleting student: " + e.getMessage());
            e.printStackTrace();
        }
    }
    
    // Get students by age greater than
    public List<Student> getStudentsByAgeGreaterThan(int age) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "FROM Student WHERE age > :age", Student.class);
            query.setParameter("age", age);
            List<Student> students = query.list();
            System.out.println("Students with age > " + age + ": " + students.size());
            return students;
        } catch (Exception e) {
            System.err.println("Error retrieving students by age: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Get average age
    public Double getAverageAge() {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Double> query = session.createQuery(
                "SELECT AVG(s.age) FROM Student s", Double.class);
            Double avgAge = query.uniqueResult();
            System.out.println("Average age of students: " + avgAge);
            return avgAge;
        } catch (Exception e) {
            System.err.println("Error calculating average age: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
    
    // Get students by name containing
    public List<Student> getStudentsByNameContaining(String namePart) {
        try (Session session = HibernateUtil.getSessionFactory().openSession()) {
            Query<Student> query = session.createQuery(
                "FROM Student WHERE name LIKE :namePart", Student.class);
            query.setParameter("namePart", "%" + namePart + "%");
            List<Student> students = query.list();
            System.out.println("Students with name containing '" + namePart + "': " + students.size());
            return students;
        } catch (Exception e) {
            System.err.println("Error retrieving students by name: " + e.getMessage());
            e.printStackTrace();
            return null;
        }
    }
}