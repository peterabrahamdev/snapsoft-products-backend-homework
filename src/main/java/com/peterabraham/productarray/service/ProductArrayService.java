package com.peterabraham.productarray.service;

import com.peterabraham.productarray.entity.ProductArrayCalculationRecord;
import com.peterabraham.productarray.repository.ProductArrayRepository;
import jakarta.annotation.PostConstruct;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
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
            int[] outputArray = memoizedCalculations.get(Arrays.toString(inputArray));
            logger.info("Using a memoized value for calculation a)");
            logger.info("Result of calculation a): " + Arrays.toString(outputArray));

            return outputArray;
        } else {
            logger.info("Using a not memoized value for calculation a)");
            int product = Arrays.stream(inputArray).reduce(1, (a, b) -> a * b);
            int[] result = Arrays.stream(inputArray).map(i -> product / i).toArray();
            memoizedCalculations.putIfAbsent(Arrays.toString(inputArray), result);
            logger.info("Result of calculation a): " + Arrays.toString(result));
            return result;
        }
    }

    public int[] calculationB(int[] inputArray) {
        if (inputArray == null || inputArray.length == 0) {
            throw new IllegalArgumentException("The input array must not be empty!");
        }

        int[] outputArray = new int[inputArray.length];

        for (int i = 0; i < inputArray.length; i++) {
            int currentProduct = 1;
            for (int j = 0; j < inputArray.length; j++) {
                if (i != j) {
                    currentProduct *= inputArray[j];
                }
            }
            outputArray[i] = currentProduct;
        }
        logger.info("Result of calculation b): " + Arrays.toString(outputArray));
        return outputArray;
    }

    public int[] calculationC(int[] input) {
        if (input == null || input.length == 0) {
            throw new IllegalArgumentException("Input array cannot be empty");
        }

        int n = input.length;
        int[] result = new int[n];

        result[0] = 1;
        for (int i = 1; i < n; i++) {
            result[i] = result[i - 1] * input[i - 1];
        }

        int suffixProduct = 1;
        for (int i = n - 1; i >= 0; i--) {
            result[i] *= suffixProduct;
            suffixProduct *= input[i];
        }

        logger.info("Result of calculation c): " + Arrays.toString(result));
        return result;
    }

    public List<ProductArrayCalculationRecord> getHistory(String searchTerm) {
        if (searchTerm == null || searchTerm.isEmpty()) {
            return productArrayRepository.findAll();
        }
        return productArrayRepository.findByCommentContainingIgnoreCase(searchTerm);
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
