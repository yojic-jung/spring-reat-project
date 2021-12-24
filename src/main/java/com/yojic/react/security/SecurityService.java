package com.yojic.react.security;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.yojic.react.dto.MembersDTO;
import com.yojic.react.entity.Members;
import com.yojic.react.entity.MembersRepository;

@Service
public class SecurityService implements UserDetailsService {
		
		@Autowired
	    private MembersRepository membersRepository;

	    @Override
	    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
	        Optional<Members> memberEntityWrapper = membersRepository.findByEmail(email);
	        Members memberEntity = memberEntityWrapper.orElse(null);

	        List<GrantedAuthority> authorities = new ArrayList<>();

	        authorities.add(new SimpleGrantedAuthority(memberEntity.getAuthority()));
	        
	        return new User(memberEntity.getEmail(), memberEntity.getPassword(), authorities);
	    }

	    @Transactional
	    public Integer save(MembersDTO memberDTO) {
	    	BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
	    	memberDTO.setPassword(passwordEncoder.encode(memberDTO.getPassword()));
	    	Members members = Members.builder()
	    			.email(memberDTO.getEmail())
	    			.password(memberDTO.getPassword())
	    			.authority("USER")
	    			.build();
	       
	        return membersRepository.save(members).getId();
	    }
}
