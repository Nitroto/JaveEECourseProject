package bg.softuni.forum.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.forum.entity.UserModel;

/**
 * Session Bean implementation class UserService
 */
@Stateless
@LocalBean
public class UserService implements UserServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> findAllUsers() {
		String query = "select model from UserModel model order by upper(model.username) asc";
		Query q = entityManager.createQuery(query);

		return q.getResultList();
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<UserModel> getAllUsersforDisplay() {
		String query = "select new UserModel(usermodel.id, usermodel.username, usermodel.password, usermodel.firstName, usermodel.lastName, usermodel.email, COUNT(postmodel)) from UserModel usermodel left join usermodel.posts postModel group by usermodel.id order by upper(usermodel.username) asc";
		Query q = entityManager.createQuery(query);

		return q.getResultList();
	}

	@Override
	public UserModel save(UserModel entity) {
		entityManager.persist(entity);
		return entity;
	}

	@Override
	public UserModel update(UserModel entity) {
		entityManager.merge(entity);
		entityManager.flush();
		return entity;
	}

	@Override
	public void delete(UserModel entity) {
		entityManager.remove(entity);
	}

	@Override
	public UserModel findById(Long id) {
		try {
			UserModel instance = entityManager.find(UserModel.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public UserModel loginUser(String aUsername, String aPassword) {
		StringBuffer query = new StringBuffer(
				"select model from UserModel model where model.username = :em and model.password = :p");

		Query q = entityManager.createQuery(query.toString());
		q.setParameter("em", aUsername);
		q.setParameter("p", aPassword);

		try {
			return (UserModel) q.getSingleResult();
		} catch (NoResultException nre) {
			// the user doesn't exist
			return null;
		}
	}

	@Override
	public UserModel checkUserExists(String username, Long id) {
		StringBuffer query = new StringBuffer(
				"select model from UserModel model where upper(model.username) = upper(:em)");

		if (id != null) {
			query.append(" and model.id <> ").append(id);
		}

		Query q = entityManager.createQuery(query.toString());
		q.setParameter("em", username);

		try {
			return (UserModel) q.getSingleResult();
		} catch (NoResultException nre) {
			// the user doesn't exist
			return null;
		}
	}
}
