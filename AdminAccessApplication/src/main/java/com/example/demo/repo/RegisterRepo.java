package com.example.demo.repo;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import com.example.demo.entity.UserDetails;

@Repository
public interface RegisterRepo extends JpaRepository<UserDetails, Integer> {
	Optional<UserDetails>  findByUserName(String name);
}
