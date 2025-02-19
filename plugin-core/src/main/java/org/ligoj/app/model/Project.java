/*
 * Licensed under MIT (https://github.com/ligoj/ligoj/blob/master/LICENSE)
 */
package org.ligoj.app.model;

import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

import org.hibernate.validator.constraints.Length;
import org.ligoj.bootstrap.core.IDescribableBean;
import org.ligoj.bootstrap.core.model.AbstractNamedAuditedEntity;
import org.ligoj.bootstrap.core.validation.LowerCase;
import org.ligoj.bootstrap.core.validation.SafeHtml;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Getter;
import lombok.Setter;

/**
 * A managed project.
 */
@Getter
@Setter
@Entity
@Table(uniqueConstraints = { @UniqueConstraint(columnNames = "name"),
		@UniqueConstraint(columnNames = "pkey") }, name = "LIGOJ_PROJECT")
public class Project extends AbstractNamedAuditedEntity<Integer> implements IDescribableBean<Integer> {

	/**
	 * SID, for Hazelcast
	 */
	private static final long serialVersionUID = 1L;

	/**
	 * Project PKEY pattern.
	 */
	public static final String PKEY_PATTERN = "([a-z]|[0-9]+-?[a-z])[a-z0-9\\-]*";

	/**
	 * Project PKEY pattern.
	 */
	public static final String PKEY_PATTERN_WRAPPER = "^" + PKEY_PATTERN + "$";

	/**
	 * Unique technical and yet readable name.
	 */
	@NotBlank
	@LowerCase
	@Size(max = 100)
	@Pattern(regexp = PKEY_PATTERN_WRAPPER)
	private String pkey;

	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	private List<Subscription> subscriptions;

	/**
	 * Human readable description.
	 */
	@Length(max = 1024)
	@SafeHtml
	private String description;

	/**
	 * Team Leader username
	 */
	private String teamLeader;

	@JsonIgnore
	@OneToMany(mappedBy = "project", cascade = CascadeType.REMOVE)
	private List<CacheProjectGroup> cacheGroups;

}
