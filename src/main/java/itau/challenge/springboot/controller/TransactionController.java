package itau.challenge.springboot.controller;

import itau.challenge.springboot.dto.TransactionRequest;
import itau.challenge.springboot.model.Transaction;
import itau.challenge.springboot.service.TransactionService;
import jakarta.validation.Valid;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.OffsetDateTime;

@RestController
@RequestMapping("/transaction")
public class TransactionController {

    private static final Logger log = LoggerFactory.getLogger(TransactionController.class);
    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @PostMapping
    public ResponseEntity<Void> createTransaction(@Valid @RequestBody TransactionRequest request) {
        log.info("Received transaction request: {}", request);

        if (request.dateTime().isAfter(OffsetDateTime.now()) || request.value() <= 0) {
            log.warn("Invalid transaction: must not be in the future and value must be greater than 0. Request={}", request);

            return ResponseEntity.unprocessableEntity().build();
        }

        transactionService.addTransaction(new Transaction(request.value(), request.dateTime()));
        log.info("Transaction successfully recorded: value={}, datetime={}", request.value(), request.dateTime());

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @DeleteMapping
    public ResponseEntity<Void> clearTransaction() {
        transactionService.clearTransactions();
        log.info("All transactions have been successfully cleared.");
        return ResponseEntity.ok().build();
    }
}
