package com.mausse.capsuladotempo.controller;

import com.mausse.capsuladotempo.dto.CapsuleRequest;
import com.mausse.capsuladotempo.entity.Capsule;
import com.mausse.capsuladotempo.entity.User;
import com.mausse.capsuladotempo.repository.CapsuleRepository;
import com.mausse.capsuladotempo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/capsules")
@RequiredArgsConstructor
public class CapsuleController {

    private final CapsuleRepository repo;
    private final UserRepository userRepo;

    @PostMapping
    public ResponseEntity<Capsule> create(@RequestBody CapsuleRequest dto) {
        // 0) Validação de entrada
        if (dto.ownerId() == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "ownerId é obrigatório");
        }

        // 1) Busca o usuário no banco
        User owner = userRepo.findById(dto.ownerId())
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Usuário não encontrado"));

        // 2) Monta a entidade
        Capsule c = new Capsule();
        c.setOwner(owner);
        c.setMessage(dto.message());
        c.setSendDate(dto.sendDate());
        c.setRecipients(dto.recipients());
        // `sent` já inicializa como false por default

        // 3) Salva e retorna CREATED (201)
        Capsule saved = repo.save(c);
        return ResponseEntity.status(HttpStatus.CREATED).body(saved);
    }

    @GetMapping
    public List<Capsule> all() { return repo.findAll(); }

    @GetMapping("/{id}")
    public ResponseEntity<Capsule> byId(@PathVariable Long id) {
        return repo.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Capsule> update(@PathVariable Long id, @RequestBody Capsule c) {
        return repo.findById(id).map(existing -> {
            existing.setMessage(c.getMessage());
            existing.setSendDate(c.getSendDate());
            existing.setRecipients(c.getRecipients());
            return ResponseEntity.ok(repo.save(existing));
        }).orElse(ResponseEntity.notFound().build());
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) { repo.deleteById(id); }
}
