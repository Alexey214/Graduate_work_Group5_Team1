package pro.sky.graduate_work_group5_team1.handler;

import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import pro.sky.graduate_work_group5_team1.exeption.*;

import javax.validation.ConstraintViolationException;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<String> handleConstraintViolationException(ConstraintViolationException e) {
        log.error("Введены неверные данные!");
        return new ResponseEntity<>("ошибка валидации: " + e.getMessage(), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(ForbiddenException.class)
    @ResponseStatus(HttpStatus.FORBIDDEN)
    public ResponseEntity<String> handleForbiddenException(ForbiddenException e) {
        log.error("Введены неверные данные!");
        return ResponseEntity.status(HttpStatus.FORBIDDEN)
                .body("Введены неверные данные!");
    }

    @ExceptionHandler(AdsCommentNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAdsCommentNotFoundException(AdsCommentNotFoundException e) {
        log.error("Комментарий не найден!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Комментарий не найден!");
    }

    @ExceptionHandler(AdsNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleAdsNotFoundException(AdsNotFoundException e) {
        log.error("Объявление не найдено!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Объявление не найдено!");
    }

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<String> handleUserNotFoundException(UserNotFoundException e) {
        log.error("Пользователь не найден!");
        return ResponseEntity.status(HttpStatus.NOT_FOUND)
                .body("Пользователь не найден!");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseStatus(HttpStatus.UNAUTHORIZED)
    public ResponseEntity<String> handleUnauthorizedException(UnauthorizedException e) {
        log.error("Пользователь не авторизован!");
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                .body("Пользователь не авторизован!");
    }
}
