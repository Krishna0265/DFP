package com.kiran.ecom_proj.repository;

import com.kiran.ecom_proj.model.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ProductRepo extends JpaRepository<Product,Integer> {

    @Query("select p from Product p where" +
            "Lower(p.name) like lower(concat('%',:keyword,'%')) OR " +
            "Lower(p.description) like lower(concat('%',:keyword,'%')) OR " +
            "Lower(p.brand) like lower(concat('%',:keyword,'%')) OR " +
            "Lower(p.category) like lower(concat('%',:keyword,'%'))")
    public List<Product> search(String keyword);
}
