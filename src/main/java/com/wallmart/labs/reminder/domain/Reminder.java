package com.wallmart.labs.reminder.domain;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import javax.persistence.Version;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "REMINDERS")
@SequenceGenerator(name="REMINDERS_SEQ",sequenceName="REMINDERS_SEQ", allocationSize=1)
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reminder {

	@Id
	@GeneratedValue(strategy=GenerationType.SEQUENCE, generator="REMINDERS_SEQ")
	@Column(name="REMINDER_ID")
	private Long reminderId;

	@Version
	@Column(name="VERSION")
	private Integer version;

	@Column(name="NAME")
	private String name;

	@Column(name="DESCRIPTION")
	private String description;

	@Column(name="DUE_DATE")
	private LocalDate dueDate;

	@Column(name="STATUS")
	@Enumerated(EnumType.STRING)
	private ReminderStatus status;

	public Long getReminderId() {
		return reminderId;
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
		return String.format("Reminder [reminderId=%s, version=%s, name=%s, description=%s, dueDate=%s, status=%s]", 
				reminderId, version, name, description, dueDate, status);
	}

}
