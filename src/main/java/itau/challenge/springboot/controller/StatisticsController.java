package itau.challenge.springboot.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import itau.challenge.springboot.dto.StatsResponse;
import itau.challenge.springboot.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.DoubleSummaryStatistics;

@RestController
@RequestMapping("/statistics")
public class StatisticsController {

    private static final Logger log = LoggerFactory.getLogger(StatisticsController.class);
    private final TransactionService transactionService;

    public StatisticsController(TransactionService transactionService) {
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<StatsResponse> getStats() {
        log.info("Received request for transaction statistics.");

        DoubleSummaryStatistics stats = transactionService.getStatistics();
        var response = new StatsResponse(stats);

        log.info("Returning statistics: count={}, sum={}, avg={}, min={}, max={}",
                response.count(), response.sum(), response.avg(), response.min(), response.max());

        return ResponseEntity.ok(response);
    }
}
