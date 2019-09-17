package br.edu.ifsp.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.Query;
import org.hibernate.service.ServiceRegistry;

public class UserRepository {

	EntityManager manager 	     = null;
	EntityManagerFactory factory = null;

	public UserRepository() {
		factory = Persistence.createEntityManagerFactory("app");
		manager = factory.createEntityManager();
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();
		String query = "SELECT u FROM User u WHERE u.id IS NOT NULL";
		TypedQuery<User> tq = manager.createQuery(query, User.class);

		try {
			users = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		} 

		return users;

	}

	public User getUser(long id) {
		User user = new User();
    	String query = "SELECT u FROM User u WHERE u.id = :id";
    	
    	TypedQuery<User> typedQuery = manager.createQuery(query, User.class);
    	typedQuery.setParameter("id", id);
    	
    	try {
    		user = typedQuery.getSingleResult();
    	}
    	catch(NoResultException ex) {
    		ex.printStackTrace();
    	}
    	
    	return user;
		
	}

	public User create(User user) {
        EntityTransaction entityTransaction = null;
 
        try {
        	entityTransaction = manager.getTransaction();
            entityTransaction.begin(); 
            manager.persist(user);
            entityTransaction.commit();
        } catch (Exception ex) {
            // Se ocorrer uma exceção, dê rollback nas mudanças
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        }
        
        return user;
	}

	public User update(User user) {
		EntityTransaction entityTransaction = null;
				
		try {
            entityTransaction = manager.getTransaction();
            entityTransaction.begin();
            manager.merge(user);
            entityTransaction.commit();
        } catch (Exception ex) {
            if (entityTransaction != null) {
                entityTransaction.rollback();
            }
            ex.printStackTrace();
        } 
		
		return user;		
	}

	public Boolean delete(long id) {
		User user;
		EntityTransaction entityTransaction = null;
			
		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			user = manager.find(User.class, id);
			manager.remove(user);
			entityTransaction.commit();
		} catch (Exception ex) {
			// Caso ocorra uma exceção, um rollback das mudanças será executado
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}
		
		return true;
	}

}
