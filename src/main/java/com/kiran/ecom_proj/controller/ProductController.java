package com.kiran.ecom_proj.controller;

import com.kiran.ecom_proj.model.Product;
import com.kiran.ecom_proj.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {

    @Autowired
    ProductService service;

    @GetMapping("/products")
    public ResponseEntity<List<Product>> getAllProducts(){
        return new ResponseEntity<>(service.getAllProducts(), HttpStatus.OK);
    }

    @GetMapping("/product/{prodId}")
    public ResponseEntity<Product> getProductById(@PathVariable int prodId){

        Product pro = service.getProductById(prodId);

        if(pro != null)
            return new ResponseEntity<>(pro,HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart("product") Product product,
                                        @RequestPart MultipartFile imageFile){
        try{
            //System.out.println(product);
            Product p=service.addProduct(product,imageFile);
            return new ResponseEntity<>(p,HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>(e.getMessage(),HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @GetMapping("/product/{productId}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int productId){

        Product product = service.getProductById(productId);
        byte[] imageFile = product.getImageData();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType())).body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<String> updateProduct(@PathVariable int id,@RequestPart("product") Product product,
                                           @RequestPart MultipartFile imageFile){
        Product prod = null;
        try{
             prod = service.updateProduct(id,product,imageFile);
        }catch (Exception e){
            return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
        }


        if(prod != null)
            return new ResponseEntity<>("Updated",HttpStatus.OK);
        return new ResponseEntity<>("Failed to update",HttpStatus.BAD_REQUEST);
    }

    @DeleteMapping("/product/{prodId}")
    public ResponseEntity<String> deleteProduct(@PathVariable int prodId){
        Product product = service.getProductById(prodId);
        if(product != null){
            service.deleteProduct(prodId);
            return new ResponseEntity<>("Success",HttpStatus.OK);
        }
        return new ResponseEntity<>("Product Not Found",HttpStatus.NOT_FOUND);
    }

    @GetMapping("/products/search")
    public ResponseEntity<List<Product>> search(@RequestParam String keyword){
        List<Product> products = service.search(keyword);
        return new ResponseEntity<>(products,HttpStatus.OK);
    }

}
