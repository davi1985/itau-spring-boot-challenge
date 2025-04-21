package itau.challenge.springboot.service;

import itau.challenge.springboot.model.Transaction;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.OffsetDateTime;
import java.util.DoubleSummaryStatistics;

public class TransactionServiceTest {

    private TransactionService transactionService;

    @BeforeEach
    public void setUp(){
        transactionService = new TransactionService();
    }

    @Test
    public void testAddTransactionAndGetStatistics() {
        Transaction transaction = new Transaction(100.0, OffsetDateTime.now().minusSeconds(30));

        transactionService.addTransaction(transaction);

        DoubleSummaryStatistics statistics = transactionService.getStatistics();
        Assertions.assertEquals(1, statistics.getCount());
        Assertions.assertEquals(100.0, statistics.getSum());
        Assertions.assertEquals(100.0, statistics.getAverage());
        Assertions.assertEquals(100.0, statistics.getMin());
        Assertions.assertEquals(100.0, statistics.getMax());
    }

    @Test
    public void testClearTransactions() {
        var transaction1 = new Transaction(100.0, OffsetDateTime.now().minusSeconds(30));
        var transaction2 = new Transaction(100.0, OffsetDateTime.now().minusSeconds(10));

        transactionService.addTransaction(transaction1);
        transactionService.addTransaction(transaction2);
        transactionService.clearTransactions();
        var statistics = transactionService.getStatistics();

        Assertions.assertEquals(0, statistics.getCount());
    }
}
