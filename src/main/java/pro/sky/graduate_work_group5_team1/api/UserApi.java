package pro.sky.graduate_work_group5_team1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.model.dto.NewPassword;
import pro.sky.graduate_work_group5_team1.model.dto.ResponseWrapperUser;
import pro.sky.graduate_work_group5_team1.model.dto.UserDto;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface UserApi {

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
                            schema = @Schema(implementation = UserDto.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<UserDto> updateUser(@Parameter(description = "user", required = true, schema = @Schema())
                                       @Valid UserDto user);

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @GetMapping(value = "/me/image", produces = {MediaType.IMAGE_PNG_VALUE})
    byte[] getUserPhoto();

    @PreAuthorize("hasRole('ADMIN') || hasRole('USER')")
    @PatchMapping(value = "/me/image", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    ResponseEntity<UserDto> patchUserPhoto(@RequestPart("image") MultipartFile file);
}
