package bg.softuni.forum.web.beans;

import java.util.Date;
import java.util.Iterator;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.commons.lang3.StringUtils;

import bg.softuni.forum.entity.PostModel;
import bg.softuni.forum.entity.UserModel;
import bg.softuni.forum.service.PostServiceLocal;
import bg.softuni.forum.web.utils.MessageUtils;

@ManagedBean(name = "createPostBean")
@ViewScoped
public class CreatePostBean {
	@Inject
	HttpServletRequest request;

	@EJB
	PostServiceLocal postService;

	private PostModel post;

	@PostConstruct
	public void init() {
		post = new PostModel();
	}

	public PostModel getPost() {
		return post;
	}

	public void setPost(PostModel post) {
		this.post = post;
	}

	public String createAction() {

		this.post.setDate(new Date());
		this.post.setAuthor((UserModel) request.getSession().getAttribute("LOGGED_USER"));
		
		if (!validate()) {
			return null;
		}

		postService.save(post);

		return "/pages/listPosts?faces-redirect=true";
	}

	private boolean validate() {
		boolean hasErrors = false;
		if (StringUtils.isBlank(post.getTitle())) {
			MessageUtils.addErrorMessage("error.required.title");
			hasErrors = true;
		}

		if (StringUtils.isBlank(post.getContent())) {
			MessageUtils.addErrorMessage("error.required.content");
			hasErrors = true;
		}

/*		if (post.getTopic() != null) {
			MessageUtils.addErrorMessage("error.required.topic");
			hasErrors = true;
		}*/

		if (hasErrors) {
			return false;
		}

		return true;
	}
	
	/**
	 * Verifies if a error messages are present in the context
	 */
	public boolean hasErrors() {
		Iterator<FacesMessage> messages = FacesContext.getCurrentInstance().getMessages();
		for (; messages.hasNext();) {
			FacesMessage message = messages.next();
			if (message.getSeverity().compareTo(FacesMessage.SEVERITY_ERROR) == 0) {
				return true;
			}
		}

		return false;
	}
}
