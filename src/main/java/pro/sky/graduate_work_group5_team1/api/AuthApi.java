package pro.sky.graduate_work_group5_team1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import pro.sky.graduate_work_group5_team1.model.dto.LoginReq;
import pro.sky.graduate_work_group5_team1.model.dto.RegReq;

import javax.validation.Valid;

@Validated
public interface AuthApi {

    /**
     * Авторизация пользователя
     *
     * @param loginReq авторизационные данные
     * @return в случае успеха возвращает {@link ResponseEntity#ok()}, либо {@link HttpStatus#FORBIDDEN}
     */
    @Operation(
            summary = "login",
            tags = "Авторизация",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = LoginReq.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<LoginReq> login(@Valid LoginReq loginReq);

    /**
     * Регистрация пользователя
     *
     * @param regReq регистрационные данные
     * @return в случае успеха возвращает {@link ResponseEntity#ok()}, либо {@link HttpStatus#BAD_REQUEST}
     */
    @Operation(
            summary = "register",
            tags = "Авторизация",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = RegReq.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<RegReq> register(@Parameter(description = "req", required = true) @Valid RegReq regReq);


}
