package br.edu.ifsp.app;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class UserRepository {

	Connection connection = null;

	public UserRepository() {
		
		EntityManagerFactory factory = Persistence.createEntityManagerFactory("app");
		EntityManager manager = factory.createEntityManager();
		String email = "fulano@ifsp.edu.br";
		User usuario = manager.find(User.class, email);
		System.out.println("Buscando via hibernate: " + usuario.getName());
		
//		String url = "jdbc:mysql://localhost:3306/app";
//		String username = "root";
//		String password = "password";
//
//		try {
//			Class.forName("com.mysql.jdbc.Driver");
//			connection = DriverManager.getConnection(url, username, password);
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
	}

	public List<User> getUsers() {
		List<User> users = new ArrayList<>();

		String sql = "select * from user";

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			while (resultSet.next()) {
				User user = new User();
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));

				users.add(user);
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return users;
	}

	public User getUser(int id) {
		User user = new User();

		String sql = "select * from user where id=" + id;

		try {
			Statement statement = connection.createStatement();
			ResultSet resultSet = statement.executeQuery(sql);
			if (resultSet.next()) {
				user.setId(resultSet.getInt(1));
				user.setName(resultSet.getString(2));
				user.setEmail(resultSet.getString(3));
			} else {
				user.setId(0);
				user.setEmail("not found");
				user.setName("not found");
			}
		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}

	public User create(User user) {
		String sql = "insert into user values(?,?,?)";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setLong(1, user.getId());					
			statement.setString(2, user.getName());
			statement.setString(3, user.getEmail());
			statement.executeUpdate();			
		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}
	
	public User update(User user) {
		String sql = "update user set name=?, email=? where id=?";

		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			
			statement.setString(1, user.getName());
			statement.setString(2, user.getEmail());
			statement.setLong(3, user.getId());					
			statement.executeUpdate();			
		} catch (Exception e) {
			System.out.println(e);
		}

		return user;
	}
	
	public Boolean delete(User user) {
		String sql = "delete from user where id=?";
		
		if (user.getName().equals("not found")) {
			return false;
		}
		
		try {
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setLong(1, user.getId());					
			statement.executeUpdate();
			
		} catch (Exception e) {
			System.out.println(e);
		}
		
		return true;
	}

}
