package itau.challenge.springboot.service;

import itau.challenge.springboot.model.Transaction;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;
import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

@Service
public class TransactionService {

    private static final Logger log = LoggerFactory.getLogger(TransactionService.class);
    private final Queue<Transaction> transactions = new ConcurrentLinkedQueue<>();

    public void addTransaction(Transaction transaction) {
        transactions.add(transaction);
        log.info("Transaction added: value={}, dateTime={}", transaction.value(), transaction.dateTime());
    }

    public void clearTransactions() {
        transactions.clear();
        log.info("All transactions cleared.");
    }

    public DoubleSummaryStatistics getStatistics() {
        OffsetDateTime now = OffsetDateTime.now();

        var statistics = transactions.stream()
                .filter(transaction -> transaction.dateTime().isAfter(now.minusSeconds(60)))
                .mapToDouble(Transaction::value)
                .summaryStatistics();

        log.info("Statistics calculated for last 60 seconds: count={}, sum={}, avg={}, min={}, max={}",
                statistics.getCount(), statistics.getSum(), statistics.getAverage(),
                statistics.getMin(), statistics.getMax());

        return statistics;
    }
}
