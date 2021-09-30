package in.codegram.ppmapi.domain;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

import com.fasterxml.jackson.annotation.JsonFormat;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
/**
 * This project class is a domain, which represents data and 
 * it will moving layer to layer
 * @author nineve
 *
 */
@Entity
public class Project {
/**
 * Id of the project
 * it is pk & autogenerate
 */
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long Id;
/**
 * name of the peoject
 */
	@NotBlank(message="Project Name is required")
	private String projectName;
	/**
	 * projectIdentifier should be unique
	 */
	@NotBlank(message="Project Identifier Name is required")
	@Size(min = 4, max=5, message="Size must be between 4 and 5")
	@Column(unique=true,updatable=false)
	private String projectIdentifier;
	/**
	 * This is project Discription
	 */
	@NotBlank(message="description is required")
	private String description;
	/**
	 * This is project start date
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date start_date;
	/**
	 * This is project end date
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date end_date;
	/**
	 * This is date when project is created
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date created_At;
	/**
	 * This is date when project is updated
	 */
	@JsonFormat(pattern="yyyy-MM-dd")
	private Date updated_At;
	@OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "project")
	private Backlog backlog;
	public Project() {
		super();
	}
	public Long getId() {
		return Id;
	}
	public void setId(Long id) {
		Id = id;
	}
	public String getProjectName() {
		return projectName;
	}
	public void setProjectName(String projectName) {
		this.projectName = projectName;
	}
	public String getProjectIdentifier() {
		return projectIdentifier;
	}
	public void setProjectIdentifier(String projectIdentifier) {
		this.projectIdentifier = projectIdentifier;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Date getStart_date() {
		return start_date;
	}
	public void setStart_date(Date start_date) {
		this.start_date = start_date;
	}
	public Date getEnd_date() {
		return end_date;
	}
	public void setEnd_date(Date end_date) {
		this.end_date = end_date;
	}
	public Date getCreated_At() {
		return created_At;
	}
	public void setCreated_At(Date created_At) {
		this.created_At = created_At;
	}
	public Date getUpdated_At() {
		return updated_At;
	}
	public void setUpdated_At(Date updated_At) {
		this.updated_At = updated_At;
	}
	@PrePersist
	public void onCreate() {
		this.created_At=new Date();
	}
	@PreUpdate
	public void onUpdate() {
		this.updated_At=new Date();
	}
	public Backlog getBacklog() {
		return backlog;
	}
	public void setBacklog(Backlog backlog) {
		this.backlog = backlog;
	}
	
	
}
