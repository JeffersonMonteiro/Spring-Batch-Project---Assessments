package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.item.ItemProcessor;

import java.math.BigDecimal;


public class UserItemProcessor implements ItemProcessor<Users, Users> {

    private static final Logger log = LoggerFactory.getLogger(UserItemProcessor.class);

    @Override
    public Users process(final Users users) throws Exception {
        final String name = users.getName().toUpperCase();
        final String dept = users.getDept().toUpperCase();
        final BigDecimal account = users.getAccount();

        final Users transformedUser = new Users(name, dept, account);

        log.info("Converting (" + users + ") into (" + transformedUser + ")");

        return transformedUser;
    }
}