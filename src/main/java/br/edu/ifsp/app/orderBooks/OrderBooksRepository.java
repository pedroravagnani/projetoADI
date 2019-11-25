package br.edu.ifsp.app.orderBooks;

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

public class OrderBooksRepository {

	EntityManager manager = null;
	EntityManagerFactory factory = null;

	public OrderBooksRepository() {
		factory = Persistence.createEntityManagerFactory("library");
		manager = factory.createEntityManager();
	}

	public List<OrderBooks> getOrders() {
		List<OrderBooks> orderBooks = new ArrayList<>();
		String query = "SELECT o FROM OrderBooks o WHERE o.id IS NOT NULL";
		TypedQuery<OrderBooks> tq = manager.createQuery(query, OrderBooks.class);

		try {
			orderBooks = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return orderBooks;

	}

	public OrderBooks getOrder(long id) {
		OrderBooks orderBooks = new OrderBooks();
		String query = "SELECT o FROM OrderBooks o WHERE o.id = :id";

		TypedQuery<OrderBooks> typedQuery = manager.createQuery(query, OrderBooks.class);
		typedQuery.setParameter("id", id);

		try {
			orderBooks = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return orderBooks;
	}

	public OrderBooks create(OrderBooks orderBooks) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.persist(orderBooks);
			entityTransaction.commit();
			//manager.refresh(order);
		} catch (Exception ex) {
			// Se ocorrer uma exceção, dê rollback nas mudanças
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}
		
		System.out.print(manager);
		return orderBooks;
	}

	public OrderBooks update(OrderBooks orderBooks) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.merge(orderBooks);
			entityTransaction.commit();
		} catch (Exception ex) {
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}

		return orderBooks;
	}

	public Boolean delete(long id) {
		OrderBooks orderBooks;
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			orderBooks = manager.find(OrderBooks.class, id);
			manager.remove(orderBooks);
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
