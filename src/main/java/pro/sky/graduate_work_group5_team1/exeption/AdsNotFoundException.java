package pro.sky.graduate_work_group5_team1.exeption;

public class AdsNotFoundException extends RuntimeException {

    public AdsNotFoundException() {
        System.out.println("Объявление не найдено");
    }

    public AdsNotFoundException(String message) {
        super(message);
    }
}
