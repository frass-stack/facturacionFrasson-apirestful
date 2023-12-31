package com.springboot.facturacionfrasson.controller;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.middleware.ResponseHandler;
import com.springboot.facturacionfrasson.model.Product;
import com.springboot.facturacionfrasson.model.ProductDTO;
import com.springboot.facturacionfrasson.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        }catch (ValidationException e) {
            return ResponseHandler.generateValidationErrorResponse(
                    e.getMessage(),
                    HttpStatus.BAD_REQUEST,
                    e.getField()
            );
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    @GetMapping
    public ResponseEntity<Object> getProducts(){
        try{
            List<ProductDTO> productList = this.productService.getProducts();
            return ResponseHandler.generateResponse(
                    "Products List founded successfully",
                    HttpStatus.OK,
                    productList
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @GetMapping(path = "{id}")
    public ResponseEntity<Object> getProductById(@PathVariable() int id){
        try{
            ProductDTO data = this.productService.getProductById(id);
            return ResponseHandler.generateResponse(
                    "Product founded successfully",
                    HttpStatus.OK,
                    data
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Product not found: " + id,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateProduct(@PathVariable() int id, @RequestBody Product updateProduct){
        try{
            this.productService.updateProduct(id, updateProduct);
            return ResponseHandler.generateResponse(
                    "Product update successfully",
                    HttpStatus.OK,
                    null
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @DeleteMapping(path = "{id}")
    public ResponseEntity<Object> deleteProduct(@PathVariable() int id){
        try{
            ProductDTO productoBorrado = this.productService.getProductById(id);
            this.productService.deleteProduct(id);
            return ResponseHandler.generateResponse(
                    "Product delete successfully",
                    HttpStatus.OK,
                    productoBorrado
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}
