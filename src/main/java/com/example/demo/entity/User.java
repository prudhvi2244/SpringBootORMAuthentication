package com.example.demo.entity;

import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name="users_tab")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer uid;
	@Column
	private String name;
	@Column
	private String username;
	@Column
	private String password;
	@Column
	private String email;
	@Column		
	private String gender;
	@ElementCollection(fetch = FetchType.EAGER)
	@CollectionTable(name = "roles_tab",joinColumns = @JoinColumn(name="uid"))
	@Column(name="role")
	private Set<String> urole;	

}
