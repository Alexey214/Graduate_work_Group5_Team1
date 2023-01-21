package pro.sky.graduate_work_group5_team1.api;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.multipart.MultipartFile;
import pro.sky.graduate_work_group5_team1.exeption.ForbiddenException;
import pro.sky.graduate_work_group5_team1.model.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface AdsApi {

    @Operation(
            summary = "addAdsComments",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsCommentDto.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<AdsCommentDto> addAdsComments(@Parameter(description = "adPk", required = true, schema = @Schema()) @Min(1) Integer adPk,
                                                 @Parameter(description = "comment", required = true, schema = @Schema()) @Valid AdsCommentDto adsCommentDto);

    @Operation(
            summary = "addAds",
            description = "Добавить объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "201", description = "Created", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    ResponseEntity<AdsDto> addAds(@Parameter(description = "createAds", required = true, schema = @Schema())
                                  @Valid CreateAds createAds, MultipartFile multipartFile);

    @Operation(
            summary = "deleteAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsCommentDto> deleteAdsComment(@Parameter(description = "adPk", required = true, schema = @Schema()) @Min(1) Integer adPk,
                                                   @Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    @Operation(
            summary = "getALLAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseWrapperAds.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<ResponseWrapperAds> getALLAds();

    @Operation(
            summary = "getAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsCommentDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<AdsCommentDto> getAdsComment(@Parameter(description = "adPk", required = true, schema = @Schema()) @Min(1) Integer adPk,
                                                @Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    @Operation(
            summary = "getAdsComments",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseWrapperAdsComment.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@Parameter(description = "adPk", required = true, schema = @Schema()) Integer adPk);

    @Operation(
            summary = "getAdsMe",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseWrapperAds.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<ResponseWrapperAds> getAdsMe();

    @Operation(
            summary = "getAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = FullAds.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<FullAds> getAds(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    @Operation(
            summary = "removeAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsDto> removeAds(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    @Operation(
            summary = "updateAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsCommentDto.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsCommentDto> updateAdsComment(@Parameter(description = "adPk", required = true, schema = @Schema()) @Min(1) Integer adPk,
                                                   @Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id,
                                                   @Parameter(description = "comment", required = true, schema = @Schema()) @Valid AdsCommentDto adsCommentDto);

    @Operation(
            summary = "updateAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsDto> updateAds(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id,
                                     @Parameter(description = "ads", required = true, schema = @Schema()) @Valid CreateAds createAds);

    @Operation(
            summary = "addAds",
            description = "Добавить объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    ResponseEntity<AdsDto> updateAdsImage(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id,
                                          @Parameter(description = "image", required = true, schema = @Schema()) MultipartFile file);
}
