package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.model.Product;
import com.springboot.facturacionfrasson.model.RequestProductDetail;
import com.springboot.facturacionfrasson.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product postProduct(Product product) throws Exception{
        return this.productRepository.save(product);
    }

    public Product updateProduct(int id, Product updateProduct) throws Exception{
        Optional<Product> productFoundUpdate = this.productRepository.findById(id);
        if(productFoundUpdate.isEmpty()){
            throw new Exception("Product not founded");
        }
        //Actualizamos el producto
        Product productUpdate = productFoundUpdate.get();
        if(updateProduct.getDescription() != null) productUpdate.setDescription(updateProduct.getDescription());
        if(updateProduct.getCode() != null) productUpdate.setCode(updateProduct.getCode());
        if(updateProduct.getPrice() >= 0) productUpdate.setPrice(updateProduct.getPrice());
        if(updateProduct.getStock() >= 0) productUpdate.setStock(updateProduct.getStock());
        //Guardamos el producto actualizado
        this.productRepository.save(productUpdate);

        return this.productRepository.findById(id).get();
    }

    public Product getProductById(int id) throws Exception{
        Optional<Product> productFound = this.productRepository.findById(id);
        if(productFound.isEmpty()){
            throw new Exception("Product not founded");
        }
        return productFound.get();
    }

    public List<Product> getProductsById(List<RequestProductDetail> productListId) throws Exception{
        List<Product> productList = new ArrayList<>();

        for(RequestProductDetail requestProductDetail: productListId){
            Optional<Product> productoFound = this.productRepository.findById(requestProductDetail.getProductoId());
            //Validdamos que el producto este, tenga stock disponible o la cantidad pedida no sea mayor al stock.
            if(productoFound.isEmpty() || productoFound.get().getStock() == 0 || productoFound.get().getStock() < requestProductDetail.getQuantity()){
                throw new Exception("Product not found" + requestProductDetail.getProductoId());
            }
            productList.add(productoFound.get());
            //TODO: Actualizamos la cantidad del stock,
        }
        return productList;
    }

    public Product deleteProduct(int id) throws Exception{
        Optional<Product> productFound = this.productRepository.findById(id);
        if(productFound.isEmpty()){
            throw new Exception("Product not founded");
        }
        Product productDelete = productFound.get();
        this.productRepository.deleteById(productFound.get().getId());
        return productDelete;
    }
}
