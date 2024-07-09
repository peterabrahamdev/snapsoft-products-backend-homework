package com.peterabraham.productarray.repository;

import com.peterabraham.productarray.entity.ProductArrayCalculationRecord;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductArrayRepository extends CrudRepository<ProductArrayCalculationRecord, Long> {
    List<ProductArrayCalculationRecord> findAll();
    List<ProductArrayCalculationRecord> findByCommentContainingIgnoreCase(String comment);
}
