package com.app.model;

import java.util.Date;

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
//https://stackoverflow.com/questions/67378688/how-do-i-create-a-safe-lombok-jpa-entity
@Entity
@Table(name = "users")
@NoArgsConstructor
@AllArgsConstructor
@ToString
@Getter
@Setter
public class UserPerson {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name="user_id")
	private Long userId;

	@Column(name="username")
	private String username;

	@Column(name="password")
	private String password;

	@Column(name="email")
	private String email;
	
	@Column(name="created_date")
	private Date createdDate = new Date();
	
}
