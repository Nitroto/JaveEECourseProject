package bg.softuni.forum.service;

import java.util.List;

import javax.ejb.Local;

import bg.softuni.forum.entity.UserModel;

@Local
public interface UserServiceLocal {
	List<UserModel> findAllUsers();

	UserModel save(UserModel entity);

	UserModel update(UserModel entity);

	void delete(UserModel entity);

	UserModel findById(Long id);

	UserModel loginUser(String aUsername, String aPassword);

	UserModel checkUserExists(String username, Long id);

	List<UserModel> getAllUsersforDisplay();
}
