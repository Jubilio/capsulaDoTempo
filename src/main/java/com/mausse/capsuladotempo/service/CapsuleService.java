package com.mausse.capsuladotempo.service;

import com.mausse.capsuladotempo.entity.Capsule;
import com.mausse.capsuladotempo.repository.CapsuleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class CapsuleService {
    private final CapsuleRepository repo;
    // (opcional) JavaMailSender mailSender;

    // a cada minuto checa cápsulas vencidas
    @Scheduled(fixedRate = 60_000)
    public void sendDueCapsules() {
        List<Capsule> due = repo.findBySendDateBeforeAndSentFalse(LocalDateTime.now());
        for (Capsule c : due) {
            // aqui você enviaria e-mail via mailSender; por ora:
            System.out.println("Enviando cápsula #" + c.getId() + " para " + c.getRecipients());
            c.setSent(true);
            repo.save(c);
        }
    }
}
