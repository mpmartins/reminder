package com.mariopmartins.reminder.domain;

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
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.data.domain.Persistable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Entity
@Table(name = "REMINDERS")
@SequenceGenerator(name = "REMINDERS_SEQ", sequenceName = "REMINDERS_SEQ", allocationSize = 1)
@JsonIgnoreProperties({ "hibernateLazyInitializer", "handler", "new" })
public class Reminder implements Persistable<Long> {

	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "REMINDERS_SEQ")
	private Long id;

	@Version
	private Integer version;

	@NotEmpty
	@Size(max=255)
	private String name;

	@Size(max=1000000)
	private String description;

	private LocalDate dueDate;

	@Column(columnDefinition = "CHAR(10)")
	@Enumerated(EnumType.STRING)
	@NotNull
	private ReminderStatus status;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
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
				id,	version, name, description, dueDate, status);
	}

	@Override
	public boolean isNew() {
		return null == getId();
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((id == null) ? 0 : id.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Reminder other = (Reminder) obj;
		if (id == null) {
			if (other.id != null)
				return false;
		} else if (!id.equals(other.id))
			return false;
		return true;
	}
	
}
