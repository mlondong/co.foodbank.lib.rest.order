package co.com.foodbank.order.restcontroller;

import java.util.Collection;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import co.com.foodbank.order.dto.IOrder;
import co.com.foodbank.order.exception.OrderNotFoundException;
import co.com.foodbank.order.v1.controller.OrderController;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
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
     * @return {@code ResponseEntity<IPackage>}
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



}
