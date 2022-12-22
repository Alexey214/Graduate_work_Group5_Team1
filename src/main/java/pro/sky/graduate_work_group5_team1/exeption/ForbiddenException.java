package pro.sky.graduate_work_group5_team1.exeption;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        System.out.println("Неверные данные");
    }

    public ForbiddenException(String message) {
        super(message);
    }
}
