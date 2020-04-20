package com.uxpsystems.assignment.model;

import java.time.ZonedDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "PROFILES")
public class Profile {
	
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "USER_ID")
	private Long userId;
	@Column(name = "USER_NAME", unique = true, nullable = false)
	private String userName;
	@Column(name = "FIRST_NAME")
	private String firstName;
	@Column(name = "LAST_NAME")
	private String lastName;
    @Column(name = "USER_ADDRESS")
	private String address;
    @Column(name = "USER_PHONE")
	private String phone;
	@Column(name = "UPDATED_ON")
	private ZonedDateTime updatedOn;
	@Column(name = "CREATED_ON")
	private ZonedDateTime createdOn;
	

}
