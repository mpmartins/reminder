package com.wallmart.labs.reminder.domain;

import java.time.LocalDate;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;

@Entity
public class Reminder {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;

	@Version
	private Integer version;

	private String name;

	private String description;

	private LocalDate dueDate;

	private ReminderStatus status;

	public Reminder() {
		super();
	}
	
	public Reminder(String name, String description, LocalDate dueDate) {
		this(name, description, dueDate, ReminderStatus.NOTDONE);
	}
	
	public Reminder(String name, String description, LocalDate dueDate, ReminderStatus status) {
		this();
		this.name = name;
		this.description = description;
		this.dueDate = dueDate;
		this.status = status;
	}

	public Long getId() {
		return id;
	}

	public Integer getVersion() {
		return version;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public LocalDate getDueDate() {
		return dueDate;
	}

	public void setDueDate(LocalDate dueDate) {
		this.dueDate = dueDate;
	}

	public ReminderStatus getStatus() {
		return status;
	}

	public void setStatus(ReminderStatus status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return String.format("Reminder [id=%s, version=%s, name=%s, description=%s, dueDate=%s, status=%s]", 
				id, version, name, description, dueDate, status);
	}

}
