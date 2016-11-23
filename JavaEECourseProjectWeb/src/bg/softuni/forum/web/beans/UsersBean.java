package bg.softuni.forum.web.beans;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.forum.dto.UserDto;

@ManagedBean(name = "usersBean")
@SessionScoped
public class UsersBean {

	private List<UserDto> users;

	@PostConstruct
	public void init() {
		// initialize the users list
		users = new ArrayList<UserDto>();
	}

	public UserDto validateUser(String username, String password) {

		if (StringUtils.isBlank(username) || StringUtils.isBlank(password)) {
			return null;
		}

		for (UserDto currentUser : users) {
			if (username.equals(currentUser.getUsername()) && password.equals(currentUser.getPassword())) {
				return currentUser;
			}
		}

		return null;
	}

	public List<UserDto> getUsers() {
		return users;
	}

	public void setUsers(List<UserDto> users) {
		this.users = users;
	}
}
