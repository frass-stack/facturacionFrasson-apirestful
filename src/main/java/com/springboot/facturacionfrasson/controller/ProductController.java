package com.springboot.facturacionfrasson.controller;

import com.springboot.facturacionfrasson.middleware.ResponseHandler;
import com.springboot.facturacionfrasson.model.Product;
import com.springboot.facturacionfrasson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(path = "api/v1/product")
public class ProductController {

    @Autowired
    private ProductService productService;

    @PostMapping
    public ResponseEntity<Object> postProduct(@RequestBody Product product){
        try{
            Product data = this.productService.postProduct(product);
            return ResponseHandler.generateResponse(
                    "Product stored successfully",
                    HttpStatus.OK,
                    data
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Product not found",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProductById(@PathVariable() int id){
        try{
            Product data = this.productService.getProductById(id);
            return ResponseHandler.generateResponse(
                    "Product founded successfully",
                    HttpStatus.OK,
                    data
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(
                    "Product not found",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable() int id, @RequestBody Product updateProduct){
        try{
//            System.out.println(id);
//            System.out.println(updateProduct);
//            System.out.println(this.productService.getProductById(id));
            this.productService.updateProduct(id, updateProduct);
            return ResponseHandler.generateResponse(
                    "Product update successfully",
                    HttpStatus.OK,
                    null
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Product not found",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable() int id){
        try{
            Product productoBorrado = this.productService.getProductById(id);
            this.productService.deleteProduct(id);
            return ResponseHandler.generateResponse(
                    "Product delete successfully",
                    HttpStatus.OK,
                    productoBorrado
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Product not found",
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}
