package com.example.ecom_project.controller;

import com.example.ecom_project.model.Product;
import com.example.ecom_project.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
@CrossOrigin
@RestController
@RequestMapping("/api")
public class ProductController {
    @Autowired
    private ProductService service;
//    @RequestMapping("/")
//    public String greet(){
//        return "Helllo World";
//    }
    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllproducts(){
        return new ResponseEntity<>(service.getAllproducts(), HttpStatus.OK);
    }
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProduct(@PathVariable int id){
        Product product=service.getProduct(id);
        if(product!=null){
            return new ResponseEntity<>(product,HttpStatus.OK);
        }
        return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        //return null;
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product, @RequestPart MultipartFile imageFile){
        try{
        Product p=service.addProduct(product,imageFile);
            System.out.println("Hi hello");
        return new ResponseEntity<>(p,HttpStatus.CREATED);
    } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){
        Product product=service.getProduct(productId);
        byte[] imageFile=product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart Product product, @RequestPart MultipartFile imageFile){
        Product product1= null;
        try {
            product1 = service.updateProduct(id,product,imageFile);
        } catch (IOException e) {
            return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
        }
        if(product1!=null){
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        }
        return new ResponseEntity<>("Failed to Update",HttpStatus.BAD_REQUEST);
    }
//    @DeleteMapping("/product/{id}")
//    public ResponseEntity
}
