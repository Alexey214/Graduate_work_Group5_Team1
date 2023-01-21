package pro.sky.graduate_work_group5_team1.exeption;


public class AdsCommentNotFoundException extends RuntimeException {

    public AdsCommentNotFoundException() {
        System.out.printf("%s. Комментарий не найден%n", getClass().getSimpleName());
    }

    public AdsCommentNotFoundException(String message) {
        super(message);
    }
}
