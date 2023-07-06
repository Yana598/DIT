package user;


import net.datafaker.Faker;

public class UserGenerator {
    public User getFakerUser() {
        Faker faker = new Faker();
        String email = faker.internet().emailAddress();
        String password = faker.internet().password();
        String firstName = faker.name().firstName();
        String lastName = faker.name().lastName();
        String userName = faker.name().username();
        return new User(email, password, userName,lastName,firstName);
    }
}
