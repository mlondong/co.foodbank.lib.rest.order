package co.com.foodbank.order.restcontroller;

import java.util.Collection;
import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import co.com.foodbank.message.dto.MessageDTO;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceException;
import co.com.foodbank.message.sdk.exception.SDKMessageServiceIllegalArgumentException;
import co.com.foodbank.order.dto.OrderDTO;
import co.com.foodbank.order.dto.interfaces.IOrder;
import co.com.foodbank.order.exception.OrderErrorException;
import co.com.foodbank.order.exception.OrderNotFoundException;
import co.com.foodbank.order.v1.controller.OrderController;
import co.com.foodbank.order.v1.model.Order;
import co.com.foodbank.packaged.dto.PackagedDTO;
import co.com.foodbank.packaged.request.RequestPackaged;
import co.com.foodbank.user.dto.request.RequestVolunterData;
import co.com.foodbank.user.sdk.exception.SDKUserNotFoundException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceIllegalArgumentException;
import co.com.foodbank.user.sdk.exception.SDKUserServiceNotAvailableException;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;

/**
 * Class to handle all Order for foodbank.
 * 
 * @author mauricio.londono@gmail.com co.com.foodbank.order.restcontroller
 *         9/08/2021
 */
@RestController
@RequestMapping(value = "/order")
@Tag(name = "Order", description = "the order API")
@Validated
public class OrderRestController {


    @Autowired
    private OrderController controller;

    /**
     * Method find Order by Id.
     * 
     * @param id
     * @return {@code ResponseEntity<IOrder> }
     * @throws PackageNotFoundException
     */
    @Operation(summary = "Find Order by Id.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Order found.",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findById/{id-order}")
    public ResponseEntity<IOrder> findById(
            @PathVariable("id-order") @NotBlank @NotNull String id)
            throws OrderNotFoundException {
        return ResponseEntity.status(HttpStatus.OK)
                .body(controller.findById(id));
    }



    /**
     * Method to find all Order.
     * 
     * @return {@code ResonsEntity<Collection<IOrder>>}
     */
    @Operation(summary = "Find all Order.")
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Order found.",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/findAll")
    public ResponseEntity<Collection<IOrder>> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(controller.findAll());
    }



    /**
     * Method to create Order.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserNotFoundException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws PackageErrorException
     */
    @Operation(summary = "Create Order", description = "", tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Order created",
                            content = @Content(schema = @Schema(
                                    implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Order already exists")})
    @PostMapping(value = "/create",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IOrder> create(@RequestBody @Valid OrderDTO dto)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        return new ResponseEntity<IOrder>(controller.create(dto),
                HttpStatus.CREATED);
    }



    /**
     * Method to add an Volunteer.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserNotFoundException
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws PackageErrorException
     */
    @Operation(summary = "Add volunter order", description = "",
            tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Added Volunter",
                            content = @Content(schema = @Schema(
                                    implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Order already exists")})
    @PostMapping(value = "/add-Volunter/id/{id-order}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IOrder> addVolunteer(
            @PathVariable("id-order") @NotNull String id,
            @RequestBody @Valid RequestVolunterData volunteer)
            throws JsonMappingException, JsonProcessingException,
            SDKUserServiceNotAvailableException, SDKUserServiceException,
            SDKUserServiceIllegalArgumentException, SDKUserNotFoundException {
        return new ResponseEntity<IOrder>(
                controller.addVolunteer(id, volunteer), HttpStatus.OK);
    }



    /**
     * Method to add an Message.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws SDKMessageServiceException
     * @throws SDKMessageServiceIllegalArgumentException
     * @throws PackageErrorException
     */
    @Operation(summary = "Add message order", description = "",
            tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Added Message Order",
                            content = @Content(schema = @Schema(
                                    implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Order already exists")})
    @PostMapping(value = "/add-Message/id/{id-order}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IOrder> addMessage(
            @PathVariable("id-order") @NotNull String id,
            @RequestBody @Valid MessageDTO messageDTO)
            throws SDKMessageServiceIllegalArgumentException,
            SDKMessageServiceException {
        return new ResponseEntity<IOrder>(controller.addMessage(id, messageDTO),
                HttpStatus.OK);
    }

    /**
     * Method to add an Package.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws PackageErrorException
     */
    @Operation(summary = "Add package order", description = "",
            tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Added package order",
                            content = @Content(schema = @Schema(
                                    implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Order already exists")})
    @PostMapping(value = "/add-Package/id/{id-order}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IOrder> addPackage(
            @PathVariable("id-order") @NotNull String idOrder,
            @RequestBody RequestPackaged request) throws OrderErrorException {
        return new ResponseEntity<IOrder>(
                controller.addPackage(idOrder, request), HttpStatus.OK);
    }



    /**
     * Method to remove a Package order.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws PackageErrorException
     */
    @Operation(summary = "Remove package order", description = "",
            tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "201",
                            description = "Remove package order",
                            content = @Content(schema = @Schema(
                                    implementation = Order.class))),
                    @ApiResponse(responseCode = "400",
                            description = "Invalid input"),
                    @ApiResponse(responseCode = "409",
                            description = "Order already exists")})
    @DeleteMapping(value = "/remove-Package/id/{id-order}",
            consumes = {MediaType.APPLICATION_JSON_VALUE},
            produces = {MediaType.APPLICATION_JSON_VALUE})
    @ResponseBody
    public ResponseEntity<IOrder> removePackage(
            @PathVariable("id-order") @NotNull String id,
            @Valid PackagedDTO packagedDTO)
            throws OrderErrorException, JsonMappingException,
            JsonProcessingException, SDKUserServiceNotAvailableException,
            SDKUserServiceException, SDKUserServiceIllegalArgumentException {
        return new ResponseEntity<IOrder>(
                controller.removePackage(id, packagedDTO), HttpStatus.OK);
    }



    /**
     * Change state Order.
     * 
     * @param dto
     * @return {@code ResponseEntity<IOrder> }
     * @throws SDKUserServiceIllegalArgumentException
     * @throws SDKUserServiceException
     * @throws SDKUserServiceNotAvailableException
     * @throws JsonProcessingException
     * @throws JsonMappingException
     * @throws PackageErrorException
     */
    @Operation(
            summary = "Change state order, options: 1. Pending , 2. Shipment, 3. Delivered",
            description = "", tags = {"order"})
    @ApiResponses(
            value = {
                    @ApiResponse(responseCode = "200",
                            description = "Update state order",
                            content = {
                                    @Content(mediaType = "application/json")}),
                    @ApiResponse(responseCode = "500",
                            description = "Service not available.",
                            content = @Content),
                    @ApiResponse(responseCode = "400",
                            description = "Bad request.", content = @Content)})
    @GetMapping(value = "/change-stateOrder/id/{id-order}/option/{option}",
            produces = {MediaType.APPLICATION_JSON_VALUE})
    public ResponseEntity<IOrder> changeStateOrder(
            @PathVariable("id-order") @NotNull String id,
            @PathVariable("option") @Pattern(regexp = "^[1-3]{1,1}$",
                    message = "Option only between 1-3.") @NotBlank @NotNull String option)
            throws OrderErrorException, JsonMappingException,
            JsonProcessingException, SDKUserServiceNotAvailableException,
            SDKUserServiceException, SDKUserServiceIllegalArgumentException {
        return new ResponseEntity<IOrder>(
                controller.changeStateOrder(id, option), HttpStatus.OK);
    }



}
