package bg.softuni.forum.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import bg.softuni.forum.entity.base.BaseDomainObject;

@Entity
@Table(name = "TOPICS")
public class TopicModel extends BaseDomainObject  {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	private String title;
	private Date date;
	
	@Column(name = "title", length = 255, nullable = false)
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	
	@Column(name = "date")
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}

}
