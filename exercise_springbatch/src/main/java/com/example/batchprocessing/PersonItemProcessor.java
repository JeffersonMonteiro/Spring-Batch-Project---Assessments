package com.example.batchprocessing;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;


public class PersonItemProcessor implements ItemProcessor<Person, Person> {

    private static final Logger log = LoggerFactory.getLogger(PersonItemProcessor.class);

    @Override
    public Person process(final Person person) throws Exception {
        final Long userId = person.getId();
        final String userName = person.getName().toUpperCase();
        final String dept = person.getDepartment().toUpperCase();
        final String account = person.getNumberAccount();

        final Person transformedPerson = new Person(userId, userName, dept, account);

        log.info("Converting (" + person + ") into (" + transformedPerson + ")");

        return transformedPerson;
    }


}
