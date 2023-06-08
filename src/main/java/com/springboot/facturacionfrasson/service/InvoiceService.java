package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.model.*;
import com.springboot.facturacionfrasson.repository.ClientRepository;
import com.springboot.facturacionfrasson.repository.InvoiceRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class InvoiceService {

    @Autowired
    private InvoiceRepository invoiceRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    @Autowired
    private ProductService productService;

    @Autowired
    private InvoiceDetailService invoiceDetailService;

    public InvoiceDTO postInvoice(RequestInvoice requestInvoice) throws Exception{
        //Buscamos cliente y productos
        Optional<Client> clientFound = this.clientRepository.findById(requestInvoice.getClient_id());
        if(clientFound.isEmpty()){
            throw new Exception("Client not found");
        }
        Client client = clientFound.get();
        List<Product> productList = productService.getProductsById(requestInvoice.getProduct_list());

        //Calculamos el total de la factura
        double total = 0;
        int i = 0;
        for(Product product: productList){
            total += product.getPrice() * requestInvoice.getProduct_list().get(i).getQuantity();
            i++;
        }

        //Creamos una factura y poblamos sus atributos
        Invoice invoiceCreated = new Invoice();
        invoiceCreated.setCreated_at(new Date().toString());
        invoiceCreated.setClient(client);
        invoiceCreated.setTotal(total);
        //Guardamos la factura
        this.invoiceRepository.save(invoiceCreated);

        //Creamos los detalles de factura para cada producto y guardamos.
        int j = 0;
        for(Product productInvoiceDetail: productList){
            InvoiceDetail newInvoiceDetail = new InvoiceDetail();
            newInvoiceDetail.setPrecio(productInvoiceDetail.getPrice());
            newInvoiceDetail.setInvoice(invoiceCreated);
            newInvoiceDetail.setProduct(productInvoiceDetail);
            newInvoiceDetail.setCantidad(requestInvoice.getProduct_list().get(j).getQuantity());
            this.invoiceDetailService.saveInvoiceDetail(newInvoiceDetail);
            j++;
        }

        //Actualizamos el stock del producto
        int n = 0;
        for(Product productUpdateStock: productList){
//            System.out.println(productUpdateStock.getId());
//            System.out.println(productUpdateStock.getStock());
            Product productFound = this.productService.getProductById(productUpdateStock.getId());
//            System.out.println(productFound);
            int stockRestante = productUpdateStock.getStock() - requestInvoice.getProduct_list().get(n).getQuantity();
            productFound.setStock(stockRestante);
            this.productService.updateProduct(productUpdateStock.getId(), productFound);
            n++;
        }

        //Retornamos la nueva factura mediante un DTO
        return new InvoiceDTO(
                invoiceCreated.getId(),
                invoiceCreated.getCreated_at(),
                invoiceCreated.getTotal()
        );
    }

    public InvoiceWithDetailsDTO getInvoiceById(int id, int client_id) throws Exception{
        //Corroboramos que la factura exista
        Optional<Invoice> invoiceFound = this.invoiceRepository.findById(id);
        if(invoiceFound.isEmpty()){
            throw new Exception("Invoice not found to id: " + id);
        }
        Optional<Client> clientFound = this.clientRepository.findById(client_id);
        if(clientFound.isEmpty()){
            throw new Exception("client not found to id: " + client_id);
        }

        //Buscamos los detalles asociados a la factura.
        List<InvoiceDetailDTO> invoiceDetails = this.invoiceDetailService.getInvoiceDetailsByInvoiceId(id);

        return new InvoiceWithDetailsDTO(
            invoiceFound.get().getId(),
            clientFound.get().getId(),
            invoiceFound.get().getCreated_at(),
            invoiceFound.get().getTotal(),
            invoiceDetails
        );
    }

    public List<InvoiceDTO> getInvoiceByClientId(int client_id) throws Exception{
        Optional<Client> clientFound = this.clientRepository.findById(client_id);
        if(clientFound.isEmpty()){
            throw new Exception("Client not found");
        }
        List<InvoiceDTO> data = this.invoiceRepository.getInvoiceByClientId(clientFound.get().getId());
        return data;
    }
}
