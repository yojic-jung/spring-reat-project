package com.yojic.react.dto;

import com.yojic.react.entity.Members;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MembersDTO {
	
	private int id;
	private String email;
	private String password;
	private String authority;

	public Members toEntity() {
	        return new Members(email, password, authority);
	}
}
