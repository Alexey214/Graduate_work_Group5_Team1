package pro.sky.graduate_work_group5_team1.exeption;

public class UnauthorizedException extends RuntimeException {

    public UnauthorizedException() {
        System.out.println("Неверный пароль");
    }

    public UnauthorizedException(String message) {
        super(message);
    }
}
