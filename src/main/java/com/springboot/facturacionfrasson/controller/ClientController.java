package com.springboot.facturacionfrasson.controller;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.middleware.ResponseHandler;
import com.springboot.facturacionfrasson.model.Client;
import com.springboot.facturacionfrasson.model.ClientDTO;
import com.springboot.facturacionfrasson.service.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/v1/client")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @PostMapping
    public ResponseEntity<Object> postClient(@RequestBody Client client){
        try{
//            System.out.println(client);
            Client clientSaved = clientService.postClient(client);
            return ResponseHandler.generateResponse(
                "Client stored successfully",
                HttpStatus.OK,
                clientSaved
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
    public ResponseEntity<Object> getClients(){
        try{
//            System.out.println("Hola, estoy pidiendo la lista de clientes guardados.");
            List<ClientDTO> clientList = clientService.getClients();
            return ResponseHandler.generateResponse(
                    "Client list founded successfully",
                    HttpStatus.OK,
                    clientList
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
    public ResponseEntity<Object> getClient(@PathVariable() int id){
        try{
//            System.out.println(id);
            ClientDTO clienFound = clientService.getClientById(id);
            return ResponseHandler.generateResponse(
                    "Client founded successfully",
                    HttpStatus.OK,
                    clienFound
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
    public ResponseEntity<Object> deleteClient(@PathVariable() int id){
        try{
            this.clientService.deleteClient(id);
            return ResponseHandler.generateResponse(
                    "Client delete successfully",
                    HttpStatus.OK,
                    null
            );
        }catch (Exception e){
            return ResponseHandler.generateResponse(
                    e.getMessage(),
                    HttpStatus.INTERNAL_SERVER_ERROR,
                    null
            );
        }
    }

    @PutMapping(path = "{id}")
    public ResponseEntity<Object> updateClient(@PathVariable() int id, @RequestBody Client clientUpdate){
        try{
            this.clientService.updateClient(id, clientUpdate);
            return ResponseHandler.generateResponse(
                    "Client update successfully",
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
}
