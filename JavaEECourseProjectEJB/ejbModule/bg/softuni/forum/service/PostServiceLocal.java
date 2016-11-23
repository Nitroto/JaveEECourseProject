package bg.softuni.forum.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.forum.entity.PostModel;

@Local
public interface PostServiceLocal {
	List<PostModel> getAllPostForUser(Long userId);

	List<PostModel> getAllPost();

	PostModel getPostById(Long id);

	PostModel save(PostModel post);

	void delete(PostModel post);
}
