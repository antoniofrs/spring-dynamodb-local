package it.antoniofrisenda.springdynamodblocal.config;

import com.amazonaws.services.dynamodbv2.AmazonDynamoDB;
import com.amazonaws.services.dynamodbv2.datamodeling.DynamoDBMapper;
import com.amazonaws.services.dynamodbv2.model.CreateTableRequest;
import com.amazonaws.services.dynamodbv2.model.ListTablesResult;
import com.amazonaws.services.dynamodbv2.model.ProvisionedThroughput;
import com.amazonaws.services.dynamodbv2.util.TableUtils;
import it.antoniofrisenda.springdynamodblocal.model.Employee;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
@Slf4j
@AllArgsConstructor
public class ApplicationReadyEvent {

    private final AmazonDynamoDB amazonDynamoDB;

    @EventListener(org.springframework.boot.context.event.ApplicationReadyEvent.class)
    public void initializeTables() {

        DynamoDBMapper dynamoDBMapper = new DynamoDBMapper(amazonDynamoDB);

        // Add model classes here
        List<Class<?>> modelClasses = new ArrayList<>();
        modelClasses.add(Employee.class);

        modelClasses.forEach(cls -> {
            log.info("Creating DynamoDB table for " + cls.getSimpleName());
            CreateTableRequest tableRequest = dynamoDBMapper.generateCreateTableRequest(cls);
            tableRequest.setProvisionedThroughput(new ProvisionedThroughput(1L, 1L));
            boolean created = TableUtils.createTableIfNotExists(amazonDynamoDB, tableRequest);
            if (created) {
                log.info("Created table: " + cls.getSimpleName());
            } else {
                log.info("Table already exists:" + cls.getSimpleName());
            }
        });

        ListTablesResult tablesResult = amazonDynamoDB.listTables();

        log.info("Current DynamoDB tables are: ");
        for (String name : tablesResult.getTableNames()) {
            log.info("\t" + name);
        }

    }

}
