package br.edu.ifsp.app.facadeBuy;


import br.edu.ifsp.app.*;
import br.edu.ifsp.app.order.Order;
import br.edu.ifsp.app.orderBooks.OrderBooks;
import br.edu.ifsp.app.book.Book;


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

public class FacadeBuyRepository {

	EntityManager manager = null;
	EntityManagerFactory factory = null;

	public FacadeBuyRepository() {
		factory = Persistence.createEntityManagerFactory("library");
		manager = factory.createEntityManager();
	}

	public List<Order> getOrders() {
		List<Order> orders = new ArrayList<>();
		String query = "SELECT o FROM OrderBooks o WHERE o.id IS NOT NULL";
		TypedQuery<Order> tq = manager.createQuery(query, Order.class);

		try {
			orders = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return orders;

	}

	public Order getOrder(long id) {
		Order order = new Order();
		String query = "SELECT o FROM OrderBooks o WHERE o.id = :id";

		TypedQuery<Order> typedQuery = manager.createQuery(query, Order.class);
		typedQuery.setParameter("id", id);

		try {
			order = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return order;

	}

	public Order create(Order order) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.persist(order);
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
		return order;
	}

	public Order update(Order order) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.merge(order);
			entityTransaction.commit();
		} catch (Exception ex) {
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}

		return order;
	}

	public Boolean delete(long id) {
		Order order;
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			order = manager.find(Order.class, id);
			manager.remove(order);
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
