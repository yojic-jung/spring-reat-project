package com.yojic.react.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface MembersRepository extends JpaRepository<Members, Long> {

	public Optional<Members> findByEmail(String email);
	
}
