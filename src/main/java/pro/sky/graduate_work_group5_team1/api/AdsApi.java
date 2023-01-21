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
import pro.sky.graduate_work_group5_team1.exeption.AdsCommentNotFoundException;
import pro.sky.graduate_work_group5_team1.exeption.UserNotFoundException;
import pro.sky.graduate_work_group5_team1.model.dto.*;

import javax.validation.Valid;
import javax.validation.constraints.Min;

@Validated
public interface AdsApi {
    /**
     * Метод для добавления комментария к объявлению. В метод передается айди объявления и сущность комментария.
     *
     * @param adPk
     * @param adsCommentDto
     * @return AdsCommentDto
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если автор объявления не найден
     */
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

    /**
     * Метод для добавления объявления. Принимает текстовые данные и мультипарт файл фотографии.
     *
     * @param createAds
     * @param multipartFile
     * @return AdsDto
     */
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

    /**
     * Метод для удаления комментария
     *
     * @param adPk
     * @param id
     * @return AdsCommentDto
     * @throws AdsCommentNotFoundException если комментарий не найден
     */
    @Operation(
            summary = "deleteAdsComment",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsCommentDto> deleteAdsComment(@Parameter(description = "adPk", required = true, schema = @Schema()) @Min(1) Integer adPk,
                                                   @Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    /**
     * Метод для получения всех объявлений. Возвращает ResponseWrapperAds. В полях которого Коллекция объявленийц и счетчик их кол-ва.
     *
     * @return ResponseWrapperAds
     */
    @Operation(

            summary = "getALLAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = ResponseWrapperAds.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden"),
                    @ApiResponse(responseCode = "404", description = "Not Found")}
    )
    ResponseEntity<ResponseWrapperAds> getALLAds();

    /**
     * Метод для получения комментария
     *
     * @param adPk ключ объявления, к которому принадлежит комментарий
     * @param id   идентификатор комментария
     * @return AdsCommentDto
     * @throws AdsCommentNotFoundException если комментарий не найден
     */
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

    /**
     * Метод для получения всех комментариев объявления. Возвращает ResponseWrapperAdsComment. В полях которого Коллекция комментариев и счетчик их кол-ва.
     * В метод передается id объявления.
     *
     * @param adPk
     * @return ResponseWrapperAdsComment
     */
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

    /**
     * Метод для получения всех объявлений авторизованного пользователя. Возвращает список объявлений и счетчиком объявлений в виде ResponseWrapperAds
     *
     * @return ResponseWrapperAds
     * @throws UserNotFoundException если пользователь не найден
     */
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

    /**
     * Получение полной информации об объявлении (в т.ч. об авторе)
     *
     * @param id идентификатор объявления
     * @return FullAds
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если пользователь или объявление не найдены
     */
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

    /**
     * Метод для удаления объявления по ID
     *
     * @param id идентификатор объявления
     * @return AdsDto
     */
    @Operation(
            summary = "removeAds",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "204", description = "No Content"),
                    @ApiResponse(responseCode = "401", description = "Unauthorized"),
                    @ApiResponse(responseCode = "403", description = "Forbidden")})
    ResponseEntity<AdsDto> removeAds(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id);

    /**
     * метод для Обновление комментария
     *
     * @param adPk
     * @param id
     * @param adsCommentDto
     * @return измененный комментарий AdsCommentDto
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound если комментарий, объявление, или пользователь не найдены.
     */
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

    /**
     * Метод для  изменения объявления. Возвращает изменённое объявление
     *
     * @param id        идентификатор объявления
     * @param createAds изменённое объявление
     * @return AdsDto
     * @throws org.springframework.web.client.HttpClientErrorException.NotFound Если объявление не найдено
     */
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

    /**
     * Обновление фотографии объявления
     *
     * @param id
     * @param file
     * @return AdsDto
     * @throws pro.sky.graduate_work_group5_team1.exeption.AdsNotFoundException если объявление не найдено
     */
    @Operation(
            summary = "updateAdsImage",
            description = "Обновить фото",
            tags = "Объявления",
            responses = {
                    @ApiResponse(responseCode = "200", description = "OK", content = @Content(
                            mediaType = MediaType.APPLICATION_JSON_VALUE,
                            schema = @Schema(implementation = AdsDto.class))),
                    @ApiResponse(responseCode = "401", description = "Unauthorized")})
    ResponseEntity<AdsDto> updateAdsImage(@Parameter(description = "id", required = true, schema = @Schema()) @Min(1) Integer id,
                                          @Parameter(description = "image", required = true, schema = @Schema()) MultipartFile file);
}
