package pro.sky.graduate_work_group5_team1.exception;


public class AdsCommentNotFoundException extends RuntimeException {

    public AdsCommentNotFoundException() {
        System.out.printf("%s. Комментарий не найден%n", getClass().getSimpleName());
    }

}
