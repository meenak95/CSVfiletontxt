package net.meena.spring.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.file.LineMapper;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.item.file.transform.LineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

/**
 * This configuration class configures the Spring Batch job that
 * is used to demonstrate that our item reader reads the correct
 * information from the CSV file.
 */
@Configuration
public class SpringBatchExampleJobConfig {

    @Bean
    public ItemReader<SimCardDTO> itemReader() {
        LineMapper<SimCardDTO> simcardLineMapper = createSimcardLineMapper();

        return new FlatFileItemReaderBuilder<SimCardDTO>()
                .name("simcardReader")
                .resource(new ClassPathResource("data/simcard.csv"))
                .linesToSkip(1)
                .lineMapper(simcardLineMapper)
                .build();
    }

    private LineMapper<SimCardDTO> createSimcardLineMapper() {
        DefaultLineMapper<SimCardDTO> simcardLineMapper = new DefaultLineMapper<>();

        LineTokenizer simcardLineTokenizer = createSimcardLineTokenizer();
        simcardLineMapper.setLineTokenizer(simcardLineTokenizer);

        FieldSetMapper<SimCardDTO> studentInformationMapper = createSimcardInformationMapper();
        simcardLineMapper.setFieldSetMapper(studentInformationMapper);

        return simcardLineMapper;
    }
    private LineTokenizer createSimcardLineTokenizer() {
        DelimitedLineTokenizer studentLineTokenizer = new DelimitedLineTokenizer();
        studentLineTokenizer.setDelimiter(";");
        studentLineTokenizer.setNames(new String[]{"MSISDN", "simType", "name","dateOfBirth","gender","address","idNumber"});
        return studentLineTokenizer;
    }

    private FieldSetMapper<SimCardDTO> createSimcardInformationMapper() {
        BeanWrapperFieldSetMapper<SimCardDTO> studentInformationMapper = new BeanWrapperFieldSetMapper<>();
        studentInformationMapper.setTargetType(SimCardDTO.class);
        return studentInformationMapper;
    }

    @Bean
    public ItemWriter<SimCardDTO> itemWriter() {
        return new LoggingItemWriter();
    }

    /**
     * Creates a bean that represents the only step of our batch job.
     * @param reader
     * @param writer
     * @param stepBuilderFactory
     * @return
     */
    @Bean
    public Step exampleJobStep(ItemReader<SimCardDTO> reader,
                               ItemWriter<SimCardDTO> writer,
                               StepBuilderFactory stepBuilderFactory) {
        return stepBuilderFactory.get("exampleJobStep")
                .<SimCardDTO, SimCardDTO>chunk(100)
                .reader(reader)
                .writer(writer)
                .build();
    }

    /**
     * Creates a bean that represents our example batch job.
     * @param exampleJobStep
     * @param jobBuilderFactory
     * @return
     */
    @Bean
    public Job exampleJob(Step exampleJobStep,
                          JobBuilderFactory jobBuilderFactory) {
        return jobBuilderFactory.get("exampleJob")
                .incrementer(new RunIdIncrementer())
                .flow(exampleJobStep)
                .end()
                .build();
    }
}
