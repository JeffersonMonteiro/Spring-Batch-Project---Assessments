package com.example.batchprocessing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;

class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final Long userId = person.getUserId();
        final String userName = person.getUserName().toUpperCase();
        final String dept = person.getDept().toUpperCase();
        final BigDecimal account = person.getAccount();

        final Person transformedPerson = new Person(userId, userName, dept, account);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }


}
