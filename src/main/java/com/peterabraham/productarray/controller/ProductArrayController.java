package com.peterabraham.productarray.controller;

import com.peterabraham.productarray.entity.InputProductDTO;
import com.peterabraham.productarray.entity.ProductArrayCalculationRecord;
import com.peterabraham.productarray.service.ProductArrayService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class ProductArrayController {

    private final ProductArrayService productArrayService;

    @Autowired
    public ProductArrayController(ProductArrayService productArrayService) {
        this.productArrayService = productArrayService;
    }

    @PostMapping("/calculate/a")
    public ResponseEntity<String> calculateA(@RequestBody InputProductDTO inputRequest) {
        try {
            int[] inputArray = inputRequest.getInputArray();
            String comment = inputRequest.getComment();
            int[] outputArray = productArrayService.calculationA(inputArray);

            productArrayService.saveProductArrayCalculationRecord(inputArray, outputArray, comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/calculate/b")
    public ResponseEntity<String> calculateB(@RequestBody InputProductDTO inputRequest) {
        try {
            int[] inputArray = inputRequest.getInputArray();
            String comment = inputRequest.getComment();
            int[] outputArray = productArrayService.calculationB(inputArray);

            productArrayService.saveProductArrayCalculationRecord(inputArray, outputArray, comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }

    @PostMapping("/calculate/c")
    public ResponseEntity<String> calculateC(@RequestBody InputProductDTO inputRequest) {
        try {
            int[] inputArray = inputRequest.getInputArray();
            String comment = inputRequest.getComment();
            int[] outputArray = productArrayService.calculationC(inputArray);

            productArrayService.saveProductArrayCalculationRecord(inputArray, outputArray, comment);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(e.getMessage());
        }
    }


}
