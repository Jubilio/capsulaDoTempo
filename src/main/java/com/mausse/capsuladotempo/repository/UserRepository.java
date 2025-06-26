package com.mausse.capsuladotempo.repository;
import com.mausse.capsuladotempo.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
}
