package com.peterabraham.productarray.service;

import com.peterabraham.productarray.entity.ProductArrayCalculationRecord;
import com.peterabraham.productarray.repository.ProductArrayRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

@Service
public class ProductArrayService {

    private final ProductArrayRepository productArrayRepository;
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    public ProductArrayService(ProductArrayRepository productArrayRepository) {
        this.productArrayRepository = productArrayRepository;
    }

    private HashMap<String, int[]> memoizedCalculations = new HashMap<>();

    @PostConstruct
    public void init() {
        List<ProductArrayCalculationRecord> records = productArrayRepository.findAll();
        for (ProductArrayCalculationRecord record : records) {
            String inputArray = record.getInputArray();
            if (!memoizedCalculations.containsKey(inputArray)) {
                int[] outputArray = parseArray(record.getOutputArray());
                memoizedCalculations.put(inputArray, outputArray);
            }
        }
    }

    public int[] calculationA(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            throw new IllegalArgumentException("The input array must not be empty!");
        }

        if (memoizedCalculations.containsKey(Arrays.toString(inputArray))) {
            logger.info("Using memoized calculation");
            for (HashMap.Entry<String, int[]> entry : memoizedCalculations.entrySet()) {
                logger.info(entry.getKey() + " " + Arrays.toString(entry.getValue()));
            }
            return memoizedCalculations.get(Arrays.toString(inputArray));
        } else {
            logger.info("Using a not memoized calculation");
            for (HashMap.Entry<String, int[]> entry : memoizedCalculations.entrySet()) {
                logger.info(entry.getKey() + " " + Arrays.toString(entry.getValue()));
            }
            int product = Arrays.stream(inputArray).reduce(1, (a, b) -> a * b);
            int[] result = Arrays.stream(inputArray).map(i -> product / i).toArray();
            memoizedCalculations.putIfAbsent(Arrays.toString(inputArray), result);
            return result;
        }

    }

    public void saveProductArrayCalculationRecord(int[] inputArray, int[] outputArray, String comment) {
        ProductArrayCalculationRecord record = new ProductArrayCalculationRecord(Arrays.toString(inputArray), Arrays.toString(outputArray), comment, LocalDateTime.now());
        productArrayRepository.save(record);
    }

    private int[] parseArray(String arrayString) {
        String[] elements = arrayString.substring(1, arrayString.length() - 1).split(", ");
        int[] array = new int[elements.length];
        for (int i = 0; i < elements.length; i++) {
            array[i] = Integer.parseInt(elements[i]);
        }
        return array;
    }
}
