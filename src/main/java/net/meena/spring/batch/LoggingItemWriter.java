package net.meena.spring.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemWriter;

import java.util.List;

/**
 * This {@code ItemWriter} writes the received {@link StudentDTO} objects
 * to a log file. The goal of this component is to help us to demonstrate
 * that our item reader reads the correct information from the CSV file.
 */
public class LoggingItemWriter implements ItemWriter<SimCardDTO> {

    private static final Logger LOGGER = LoggerFactory.getLogger(LoggingItemWriter.class);
    TextFileWriting textfilewriting=new TextFileWriting();
    @Override
    public void write(List<? extends SimCardDTO> list) throws Exception {
        LOGGER.info("Writing students: {}", list);
        textfilewriting.TextFileWritingmethod(list);
    }
}
