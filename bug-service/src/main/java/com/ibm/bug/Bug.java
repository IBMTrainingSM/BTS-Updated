package com.ibm.bug;

import java.util.Date;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Id;

public class Bug {
	@Id
	private String id;
	@NotNull
	@NotBlank
	private String name;
	@NotNull
	private PRIORITY priority;
	@NotNull
	private TYPE type;
	@NotNull
	private STATUS status;
	@NotNull
	@NotBlank
	private String module;
	@NotBlank
	private String buildVersion;
	@NotNull
	private SEVERITY severity;
	@NotNull
	@NotBlank
	private String projectId;
	private String developerId;
	@NotNull
	@NotBlank
	private String testerId;
	@NotNull
	@NotBlank
	private String product;
	@NotNull
	@NotBlank
	@Size(min=10, max=50, message="Synopsis should be between 10 and 50 characters")
	private String synopsis;
	@NotNull
	@NotBlank
	@Size(min = 10, max = 100, message = "description must be between 10 to 100 character")
	private String description;
	@NotNull
	private Date submittedOn;
	@NotNull
	private Date etaDate;
	
	public Date getEtaDate() {
		return etaDate;
	}
	public void setEtaDate(Date etaDate) {
		if(etaDate.compareTo(new Date())<0) {
			throw new IllegalArgumentException("etaDate cannot be a past date");
		}
		this.etaDate = etaDate;
	}
	public PRIORITY getPriority() {
		return priority;
	}
	public void setPriority(PRIORITY priority) {
		this.priority = priority;
	}
	public TYPE getType() {
		return type;
	}
	public void setType(TYPE type) {
		this.type = type;
	}
	public STATUS getStatus() {
		return status;
	}
	public void setStatus(STATUS status) {
		this.status = status;
	}
	public SEVERITY getSeverity() {
		return severity;
	}
	public void setSeverity(SEVERITY severity) {
		this.severity = severity;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	public String getModule() {
		return module;
	}
	public void setModule(String module) {
		this.module = module;
	}
	public String getBuildVersion() {
		return buildVersion;
	}
	public void setBuildVersion(String buildVersion) {
		this.buildVersion = buildVersion;
	}
	
	public String getProjectId() {
		return projectId;
	}
	public void setProjectId(String projectId) {
		this.projectId = projectId;
	}
	public String getDeveloperId() {
		return developerId;
	}
	public void setDeveloperId(String developerId) {
		this.developerId = developerId;
	}
	public String getTesterId() {
		return testerId;
	}
	public void setTesterId(String testerId) {
		this.testerId = testerId;
	}
	public String getProduct() {
		return product;
	}
	public void setProduct(String product) {
		this.product = product;
	}
	public String getSynopsis() {
		return synopsis;
	}
	public void setSynopsis(String synopsis) {
		this.synopsis = synopsis;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description.trim();
	}
	public Date getSubmittedOn() {
		return submittedOn;
	}
	public void setSubmittedOn(Date submittedOn) {
		this.submittedOn = submittedOn;
	}
	
}
