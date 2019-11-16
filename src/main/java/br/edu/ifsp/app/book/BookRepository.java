package br.edu.ifsp.app.book;

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

public class BookRepository {

	EntityManager manager = null;
	EntityManagerFactory factory = null;

	public BookRepository() {
		factory = Persistence.createEntityManagerFactory("library");
		manager = factory.createEntityManager();
	}

	public List<Book> getBooks() {
		List<Book> books = new ArrayList<>();
		String query = "SELECT b FROM Book b WHERE b.id IS NOT NULL";
		TypedQuery<Book> tq = manager.createQuery(query, Book.class);

		try {
			books = tq.getResultList();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return books;

	}

	public Book getBook(long id) {
		Book book = new Book();
		String query = "SELECT b FROM Book b WHERE b.id = :id";

		TypedQuery<Book> typedQuery = manager.createQuery(query, Book.class);
		typedQuery.setParameter("id", id);

		try {
			book = typedQuery.getSingleResult();
		} catch (NoResultException ex) {
			ex.printStackTrace();
		}

		return book;

	}

	public Book create(Book book) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.persist(book);
			entityTransaction.commit();
			//manager.refresh(book);
		} catch (Exception ex) {
			// Se ocorrer uma exceção, dê rollback nas mudanças
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}
		
		System.out.print(manager);
		return book;
	}

	public Book update(Book book) {
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			manager.merge(book);
			entityTransaction.commit();
		} catch (Exception ex) {
			if (entityTransaction != null) {
				entityTransaction.rollback();
			}
			ex.printStackTrace();
		}

		return book;
	}

	public Boolean delete(long id) {
		Book book;
		EntityTransaction entityTransaction = null;

		try {
			entityTransaction = manager.getTransaction();
			entityTransaction.begin();
			book = manager.find(Book.class, id);
			manager.remove(book);
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
