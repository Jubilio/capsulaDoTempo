package com.mausse.capsuladotempo.repository;

import com.mausse.capsuladotempo.entity.Capsule;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDateTime;
import java.util.List;

public interface CapsuleRepository extends JpaRepository<Capsule,Long> {
    List<Capsule> findBySendDateBeforeAndSentFalse(LocalDateTime now);
}
