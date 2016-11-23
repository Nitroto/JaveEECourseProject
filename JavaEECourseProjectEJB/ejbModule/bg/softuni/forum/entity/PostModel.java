package bg.softuni.forum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import bg.softuni.forum.entity.base.BaseDomainObject;

@Entity
@Table(name = "POSTS")
public class PostModel extends BaseDomainObject {
	private static final long serialVersionUID = 1L;
	private String title;
	private String content;
	private Date date;
	private UserModel author;
	private TopicModel topic;

	public PostModel() {
	}

	public PostModel(Long id, String title, String content, Long author_id, Long topic_id, Date date) {
		this.id = id;
		this.title = title;
		this.content = content;
/*		this.author = first;
		this.topic = last;*/
		this.date = date;
	}
	
	@Column(name = "title", length = 100, nullable = false)
	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	@Column(name = "content", length = 400, nullable = false)
	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Column(name = "date")
	@Temporal(TemporalType.DATE)
	public Date getDate() {
		return date;
	}

	public void setDate(Date date) {
		this.date = date;
	}

	@JoinColumn(name = "author_id", referencedColumnName = "id")
	@ManyToOne
	public UserModel getAuthor() {
		return author;
	}

	public void setAuthor(UserModel author) {
		this.author = author;
	}
	
	@JoinColumn(name = "topic_id", referencedColumnName = "id")
	@ManyToOne
	public TopicModel getTopic() {
		return topic;
	}

	public void setTopic(TopicModel topic) {
		this.topic = topic;
	}

}
