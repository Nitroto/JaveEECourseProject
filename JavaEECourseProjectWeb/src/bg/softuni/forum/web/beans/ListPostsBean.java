package bg.softuni.forum.web.beans;

import java.util.List;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.forum.entity.PostModel;
import bg.softuni.forum.service.PostServiceLocal;

@ManagedBean(name = "listPostsBean")
@ViewScoped
public class ListPostsBean {
	@Inject
	HttpServletRequest request;

	@EJB
	PostServiceLocal postService;

	@PostConstruct
	public void init() {
	}

	public String createAction() {
		return "/pages/createPost";
	}

	public String viewAction() {
		return "/pages/viewPost";
	}

	public String editAction() {
		return "/pages/editPost";
	}

	public String deleteAction() {
		String id = request.getParameter("id");
		PostModel post=new PostModel();
		System.out.println(id);
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			post = postService.getPostById(Long.valueOf(id));
		}
		
		postService.delete(post);
		
		return "/pages/listPosts?faces-redirect=true";
	}

	public List<PostModel> getAllPosts() {
		List<PostModel> posts = postService.getAllPost();
		return posts;
	}
}
