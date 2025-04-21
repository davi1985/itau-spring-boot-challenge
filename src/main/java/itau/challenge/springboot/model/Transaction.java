package itau.challenge.springboot.model;

import java.time.OffsetDateTime;

public record Transaction(Double value, OffsetDateTime dateTime) {
}
