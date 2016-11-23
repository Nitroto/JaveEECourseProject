package bg.softuni.forum.web.beans;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.forum.entity.PostModel;
import bg.softuni.forum.service.PostServiceLocal;

@ManagedBean(name = "viewPostBean")
@ViewScoped
public class ViewPostBean {

	@Inject
	HttpServletRequest request;

	@EJB
	PostServiceLocal postService;

	private PostModel post;

	@PostConstruct
	public void init() {
		String id = request.getParameter("id");
		System.out.println(id);
		if (StringUtils.isNotBlank(id) && StringUtils.isNumeric(id)) {
			post = postService.getPostById(Long.valueOf(id));
		}
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}

	public String editAction() {
		return "/pages/editPost";
	}


	public String deleteAction() {
		return "/pages/createPost";
	}
}
