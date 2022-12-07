package pro.sky.graduate_work_group5_team1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import pro.sky.graduate_work_group5_team1.model.NewPassword;
import pro.sky.graduate_work_group5_team1.model.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.User;

@Validated
public interface UserController {

    @Operation(
            summary = "getUsers",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<User> getUser(@Parameter(description = "id", required = true, schema = @Schema()) Integer id);

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

    @Operation(
            summary = "updateUser",
            tags = "Пользователи",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = User.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<User> updateUser(@Parameter(description = "user", required = true, schema = @Schema()) @Valid User user);
}
