package com.peterabraham.productarray.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;

import java.time.LocalDateTime;

@Entity(name = "product_array_calculation")
public class ProductArrayCalculationRecord {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String inputArray;
    private String outputArray;
    private String comment;
    private LocalDateTime timestamp;

    public ProductArrayCalculationRecord() {
    }

    public ProductArrayCalculationRecord(String inputArray, String outputArray, String comment, LocalDateTime timestamp) {
        this.inputArray = inputArray;
        this.outputArray = outputArray;
        this.comment = comment;
        this.timestamp = timestamp;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getInputArray() {
        return inputArray;
    }

    public void setInputArray(String inputArray) {
        this.inputArray = inputArray;
    }

    public String getOutputArray() {
        return outputArray;
    }

    public void setOutputArray(String outputArray) {
        this.outputArray = outputArray;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalDateTime timestamp) {
        this.timestamp = timestamp;
    }

    @Override
    public String toString() {
        return "ProductArrayCalculationRecord{" +
                "id=" + id +
                ", inputArray='" + inputArray + '\'' +
                ", outputArray='" + outputArray + '\'' +
                ", comment='" + comment + '\'' +
                ", timestamp=" + timestamp +
                '}';
    }
}
