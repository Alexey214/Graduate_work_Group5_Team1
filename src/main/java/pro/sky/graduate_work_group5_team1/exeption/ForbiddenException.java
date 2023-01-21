package pro.sky.graduate_work_group5_team1.exeption;

public class ForbiddenException extends RuntimeException {

    public ForbiddenException() {
        System.out.printf("%s. Неверные данные%n", getClass().getSimpleName());
    }

}
