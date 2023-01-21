package pro.sky.graduate_work_group5_team1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface UserApi {

    /**
     * Получение пользователя по Id
     *
     * @param id идентификатор пользователя
     * @return возвращает пользователя {@link UserDto}
     * @throws pro.sky.graduate_work_group5_team1.exception.UserNotFoundException если пользователь не найден
     */
    @Operation(
            summary = "getUsers",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<UserDto> getUser(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    /**
     * Метод для получения всех пользователей. Возвращает список пользователей со счетчиком в виде ResponseWrapperUser
     *
     * @return возвращает всех пользователей {@link ResponseWrapperUser}
     * @throws pro.sky.graduate_work_group5_team1.exception.UserNotFoundException Если пользователи не найдены
     */
    @Operation(
            summary = "getUsers",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseWrapperUser.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<ResponseWrapperUser> getUsers();

    /**
     * Изменение пароля пользователя
     *
     * @param newPassword новый пароль пользователя
     * @return возвращает новый пароль {@link NewPassword}
     */
    @Operation(
            summary = "setPassword",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = NewPassword.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<NewPassword> setPassword(@Parameter(description = "newPassword", required = true, schema = @Schema())
                                            @Valid NewPassword newPassword);

    /**
     * Обновление данных пользователя
     *
     * @param user изменённый пользователь
     * @return возвращает изменённого пользователя {@link UserDto}
     * @throws pro.sky.graduate_work_group5_team1.exception.UserNotFoundException если пользователь не найден
     */
    @Operation(
            summary = "updateUser",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<UserDto> updateUser(@Parameter(description = "user", required = true, schema = @Schema())
                                       @Valid UserDto user);

    ResponseEntity<UserDto> deleteUser();

    /**
     * Получение изображения пользователя
     *
     * @return аватар пользователя
     */
    @Operation(
            summary = "getUserPhoto",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.IMAGE_PNG_VALUE))})
    byte[] getUserPhoto();

    /**
     * Изменение изображения пользователя
     *
     * @param file аватар пользователя
     * @return возвращает пользователя {@link UserDto} с изменённым аватаром
     */
    @Operation(
            summary = "patchUserPhoto",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = UserDto.class)))})
    ResponseEntity<UserDto> patchUserPhoto(@RequestPart("image") MultipartFile file);
}
