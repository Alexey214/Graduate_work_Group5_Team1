package pro.sky.graduate_work_group5_team1.exeption;


public class AdsNotFoundException extends RuntimeException {

    public AdsNotFoundException() {
        System.out.printf("%s. Объявление не найдено%n", getClass().getSimpleName());
    }

    public AdsNotFoundException(String message) {
        super(message);
    }
}
