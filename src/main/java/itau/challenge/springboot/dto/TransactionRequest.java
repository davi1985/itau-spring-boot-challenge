package itau.challenge.springboot.dto;

import jakarta.validation.constraints.NotNull;

import java.time.OffsetDateTime;

public record TransactionRequest(@NotNull Double value, @NotNull OffsetDateTime dateTime) {
}
