package dto;

import com.github.javafaker.Faker;

public class AccountFactory {

    public static Account getAccount(String type, String industry) {
        Faker faker = new Faker();
        return new Account(
                faker.company().name(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().domainName(),
                faker.phoneNumber().phoneNumber(),
                faker.internet().emailAddress(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                faker.address().streetAddress(),
                faker.address().city(),
                faker.address().state(),
                faker.address().zipCode(),
                faker.address().country(),
                faker.lorem().paragraph(),
                type,
                industry);
    }
}
