package com.yojic.react.entity;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Members {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id; // PK	
	private String email;
	private String password;
	private String authority;
	
	@Builder
    public Members(String email, String password, String authority) {
        this.email = email;
        this.password = password;
        this.authority = authority;
    }
	
}
