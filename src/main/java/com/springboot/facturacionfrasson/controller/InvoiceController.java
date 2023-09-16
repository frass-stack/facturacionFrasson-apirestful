package com.springboot.facturacionfrasson.controller;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.middleware.ResponseHandler;
import com.springboot.facturacionfrasson.model.InvoiceDTO;
import com.springboot.facturacionfrasson.model.InvoiceWithDetailsDTO;
import com.springboot.facturacionfrasson.model.RequestInvoice;
import com.springboot.facturacionfrasson.service.InvoiceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/invoice")
public class InvoiceController {

    @Autowired
    private InvoiceService invoiceService;

    @PostMapping
    public ResponseEntity<Object> postInvoice(@RequestBody RequestInvoice requestInvoice){
        System.out.println(requestInvoice);
        try{
            InvoiceDTO data = invoiceService.postInvoice(requestInvoice);
            return ResponseHandler.generateResponse(
                    "Invoice stored successfull",
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

    @GetMapping(path = "{id}/client/{client_id}")
    public ResponseEntity<Object> getInvoiceById(@PathVariable() int id, @PathVariable() int client_id){
        try{
            InvoiceWithDetailsDTO data = this.invoiceService.getInvoiceById(id, client_id);
            return ResponseHandler.generateResponse(
                    "Invoice found successfull",
                    HttpStatus.OK,
                    data
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Invoice not found:" + id,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @GetMapping(path = "/getInvoiceByClientId/{client_id}")
    public ResponseEntity<Object> getInvoiceByClientId(@PathVariable int client_id){
        try{
            List<InvoiceDTO> data = this.invoiceService.getInvoiceByClientId(client_id);
            return ResponseHandler.generateResponse(
                    "Invoices List found successfull",
                    HttpStatus.OK,
                    data
            );
        }catch(Exception e){
            return ResponseHandler.generateResponse(
                    "Invoice not found: " + client_id,
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }
}
