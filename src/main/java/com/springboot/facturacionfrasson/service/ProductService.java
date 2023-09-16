package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.model.Product;
import com.springboot.facturacionfrasson.model.ProductDTO;
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
        if(product.getTitle() == null) throw new ValidationException("Product title is empty.", "title");
        if(product.getDescription() == null) throw new ValidationException("Product description is empty.", "description");
        if(product.getCode() == null) throw new ValidationException("Product code is empty.", "sku");
        if(Double.valueOf(product.getPrice()) == null || product.getPrice() <= 0.0) throw new ValidationException("Product price is wrong.", "price");
        if(Integer.valueOf(product.getStock()) == null || product.getStock() <= 0) throw new ValidationException("Product stock is wrong.", "stock");

        product.setActive(true);

        return this.productRepository.save(product);
    }

    public void updateProduct(int id, Product updateProduct) throws Exception{
        Optional<Product> productFoundUpdate = this.productRepository.findById(id);
        if(productFoundUpdate.isEmpty() || !productFoundUpdate.get().getIsActive()){
            throw new Exception("Product not founded with id: " + id);
        }
        //Actualizamos el producto
        Product productUpdate = productFoundUpdate.get();
        if(updateProduct.getTitle() != null) productUpdate.setTitle(updateProduct.getTitle());
        if(updateProduct.getDescription() != null) productUpdate.setDescription(updateProduct.getDescription());
        if(updateProduct.getCode() != null) productUpdate.setCode(updateProduct.getCode());
        if(updateProduct.getPrice() >= 0) productUpdate.setPrice(updateProduct.getPrice());
        if(updateProduct.getStock() >= 0) {
            int nuevoStock = updateProduct.getStock();
            productUpdate.setStock(nuevoStock);
        }
        //Guardamos el producto actualizado
        this.productRepository.save(productUpdate);
    }

    public ProductDTO getProductById(int id) throws Exception{
        Optional<Product> productFound = this.productRepository.findById(id);
        if(productFound.isEmpty() || !productFound.get().getIsActive()){
            throw new Exception("Product not founded with id: " + id);
        }
        ProductDTO productDTO = new ProductDTO(
                productFound.get().getId(),
                productFound.get().getTitle(),
                productFound.get().getDescription(),
                productFound.get().getCode(),
                productFound.get().getPrice(),
                productFound.get().getStock()
        );
        return productDTO;
    }

    public List<Product> getProductsById(List<RequestProductDetail> productListId) throws Exception{
        List<Product> productList = new ArrayList<>();

        for(RequestProductDetail requestProductDetail: productListId){
            Optional<Product> productoFound = this.productRepository.findById(requestProductDetail.getProductoId());

            //Validdamos que el producto este, tenga stock disponible o la cantidad pedida no sea mayor al stock.
            if(productoFound.isEmpty() || !productoFound.get().getIsActive()) {
                throw new ValidationException("Product not found", "id");
            }
            if (productoFound.get().getStock() == 0) {
                throw new ValidationException("Product id: ".concat(String.valueOf(productoFound.get().getId())) ,"stock empty.");
            }
            if (productoFound.get().getStock() < requestProductDetail.getQuantity()) {
                throw new ValidationException("Product id: ".concat(String.valueOf(productoFound.get().getId())) ,"stock is insufficient.");
            }
            productList.add(productoFound.get());
        }
        return productList;
    }

    public ProductDTO deleteProduct(int id) throws Exception{
        Optional<Product> productFound = this.productRepository.findById(id);
        if(productFound.isEmpty() || !productFound.get().getIsActive()){
            throw new Exception("Product not founded with id: " + id);
        }
        ProductDTO product = new ProductDTO(
                productFound.get().getId(),
                productFound.get().getTitle(),
                productFound.get().getDescription(),
                productFound.get().getCode(),
                productFound.get().getPrice(),
                productFound.get().getStock()
        );
        productFound.get().setActive(false);
//        this.productRepository.deleteById(productFound.get().getId());
        this.productRepository.save(productFound.get());
        return product;
    }

    public List<ProductDTO> getProducts() throws Exception {
//        List<Product> productList = this.productRepository.findAll();
        List<Product> productList = this.productRepository.findByIsActiveTrue();
        List<ProductDTO> productDTOS = new ArrayList<>();
        if(productList.isEmpty()) {
            throw new Exception("List is empty.");
        };
        for(Product product: productList){
            ProductDTO productDTO = new ProductDTO(
                    product.getId(),
                    product.getTitle(),
                    product.getDescription(),
                    product.getCode(),
                    product.getPrice(),
                    product.getStock()
            );
            productDTOS.add(productDTO);
        }
        return productDTOS;
    }
}
