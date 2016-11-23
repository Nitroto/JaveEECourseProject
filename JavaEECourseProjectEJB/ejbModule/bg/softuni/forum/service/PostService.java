package bg.softuni.forum.service;

import java.util.List;

import javax.ejb.LocalBean;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

import bg.softuni.forum.entity.PostModel;
import bg.softuni.forum.entity.UserModel;

/**
 * Session Bean implementation class PostService
 */
@Stateless
@LocalBean
public class PostService implements PostServiceLocal {

	@PersistenceContext
	protected EntityManager entityManager;

	@SuppressWarnings("unchecked")
	@Override
	public List<PostModel> getAllPostForUser(Long userId) {
		// TODO Auto-generated method stub
		return null;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public List<PostModel> getAllPost() {
		String query = "select model from PostModel model";
		Query q = entityManager.createQuery(query);
		List<PostModel> posts = q.getResultList();
		return posts;
	}

	@Override
	public PostModel getPostById(Long id) {
		try {
			PostModel instance = entityManager.find(PostModel.class, id);
			return instance;
		} catch (RuntimeException re) {
			throw re;
		}
	}

	@Override
	public PostModel save(PostModel post) {
		entityManager.persist(post);
		return post;
	}

	@Override
	public void delete(PostModel post) {
		entityManager.remove(post);
	}

}
