package pro.sky.graduate_work_group5_team1.exeption;

public class AdsCommentNotFoundException extends RuntimeException {

    public AdsCommentNotFoundException() {
        System.out.println("Комментарий не найден");
    }

    public AdsCommentNotFoundException(String message) {
        super(message);
    }
}
