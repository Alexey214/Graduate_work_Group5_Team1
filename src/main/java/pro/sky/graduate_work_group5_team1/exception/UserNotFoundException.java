package pro.sky.graduate_work_group5_team1.exception;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        System.out.printf("%s. Пользователь не найден%n", getClass().getSimpleName());
    }

    public UserNotFoundException(String message) {
        super(message);
    }
}
