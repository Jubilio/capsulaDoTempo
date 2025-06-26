package com.mausse.capsuladotempo.dto;

import java.time.LocalDateTime;
import java.util.List;

public record CapsuleRequest(Long ownerId,
                             String message,
                             LocalDateTime sendDate,
                             List<String> recipients) {
}
