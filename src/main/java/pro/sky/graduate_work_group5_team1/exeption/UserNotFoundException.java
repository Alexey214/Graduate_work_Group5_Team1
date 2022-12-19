package pro.sky.graduate_work_group5_team1.exeption;

public class UserNotFoundException extends RuntimeException {

    public UserNotFoundException() {
        System.out.println("Пользователь не найден");
    }

}
