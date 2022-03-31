package repository;

import entity.User;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.List;
import java.util.UUID;

public class UserRepo {
	
	private final EntityManagerFactory entityManagerFactory =
			Persistence.createEntityManagerFactory("ro.tutorial.lab.SD");
	
	public void insertNewUser(User user) {
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.persist(user);
		em.getTransaction().commit();
		em.close();
	}

	public List<User> findByEmail(String email){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		List<User> users = em.createQuery(
				"SELECT u FROM User u WHERE u.email = :email", User.class)
				.setParameter("email", email)
				.getResultList();
		return users;
	}

	/** return a List of Users:
		* size = 0 -> didn't find the user with the given email and password
		* size = 1 -> found the user with the given email and password.
	 * Using .getSingleResult() is bad practice in this case because
	 * it throws an Exception if no User is found and there is no need for that.**/
	public List<User> findByEmailAndPassword(String email, String password){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		List<User> users = em.createQuery(
				"SELECT u FROM User u WHERE u.email = :email AND u.password = :password", User.class)
				.setParameter("email", email)
				.setParameter("password", password)
				.getResultList();
		return users;
	}

	public List<User> findAll(){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		List<User> users = em.createQuery(
				"SELECT u FROM User u", User.class)
				.getResultList();
		return users;
	}

	public void insertNewUser(String email, String username, String password){
		EntityManager em = entityManagerFactory.createEntityManager();
		em.getTransaction().begin();
		em.createNativeQuery("INSERT INTO USER (id, email, username, password) " + " VALUES(?, ?, ?, ?)")
				.setParameter(1, UUID.randomUUID().toString())
				.setParameter(2, email)
				.setParameter(3, username)
				.setParameter(4, password)
				.executeUpdate();
		em.getTransaction().commit();
		em.close();
	}
}
