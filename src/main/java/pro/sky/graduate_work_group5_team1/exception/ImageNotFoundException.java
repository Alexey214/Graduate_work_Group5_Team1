package pro.sky.graduate_work_group5_team1.exception;

public class ImageNotFoundException extends RuntimeException {

    public ImageNotFoundException() {
        System.out.println("Изображение не найдено");
    }

    public ImageNotFoundException(String message) {
        super(message);
    }
}
