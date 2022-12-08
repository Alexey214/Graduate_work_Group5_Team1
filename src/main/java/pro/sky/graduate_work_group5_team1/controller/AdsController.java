package pro.sky.graduate_work_group5_team1.controller;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
//import jakarta.validation.Valid;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import pro.sky.graduate_work_group5_team1.model.*;

import javax.validation.Valid;

@Validated
public interface AdsController {

    @Operation(
            summary = "addAdsComments",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsComment.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<AdsComment> addAdsComments(@Parameter(description = "ad_pk", required = true, schema = @Schema()) String adPk,
                                              @Parameter(description = "comment", required = true, schema = @Schema()) @Valid AdsComment adsComment);

    @Operation(
            summary = "addAds",
            description = "Добавить объявления",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class))),
                    @ApiResponse(responseCode = "201", description = "Created"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<Ads> addAds(@Parameter(description = "createAds", required = true, schema = @Schema())
                               @Valid CreateAds createAds);

    @Operation(
            summary = "deleteAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<Void> deleteAdsComment(@Parameter(description = "ad_pk", required = true, schema = @Schema()) String adPk,
                                          @Parameter(description = "id", required = true, schema = @Schema()) Integer id);

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
                            schema = @Schema(implementation = AdsComment.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")})
    ResponseEntity<AdsComment> getAdsComment(@Parameter(description = "ad_pk", required = true, schema = @Schema()) String adPk,
                                             @Parameter(description = "id", required = true, schema = @Schema()) Integer id);

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
    ResponseEntity<ResponseWrapperAdsComment> getAdsComments(@Parameter(description = "ad_pk", required = true, schema = @Schema()) String adPk);

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
    ResponseEntity<ResponseWrapperAds> getAdsMe(@Parameter(schema = @Schema()) @Valid Boolean authenticated,
                                                @Parameter(schema = @Schema()) @Valid String authorities0Authority,
                                                @Parameter(schema = @Schema()) @Valid Object credentials,
                                                @Parameter(schema = @Schema()) @Valid Object details,
                                                @Parameter(schema = @Schema()) @Valid Object principal);

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
    ResponseEntity<FullAds> getAds(@Parameter(description = "id", required = true, schema = @Schema()) Integer id);

    @Operation(
            summary = "removeAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<Void> removeAds(@Parameter(description = "id", required = true, schema = @Schema()) Integer id);

    @Operation(
            summary = "updateAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsComment.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsComment> updateAdsComment(@Parameter(description = "ad_pk", required = true, schema = @Schema()) String adPk,
                                                @Parameter(description = "id", required = true, schema = @Schema()) Integer id,
                                                @Parameter(description = "comment", required = true, schema = @Schema()) @Valid AdsComment adsComment);

    @Operation(
            summary = "updateAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = Ads.class))),
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<Ads> updateAds(@Parameter(description = "id", required = true, schema = @Schema()) Integer id,
                                  @Parameter(description = "ads", required = true, schema = @Schema()) @Valid Ads ads);
}
