package com.example.assessment.repositories;

import com.example.assessment.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
@Repository
public interface UsersRepo extends JpaRepository<Users, String> {
}

