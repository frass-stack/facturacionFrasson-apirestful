package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.model.Client;
import com.springboot.facturacionfrasson.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    public Client postClient(Client client) throws Exception{
        if(client.getName() == null) throw new ValidationException("Client name is empty.", "name");
        if(client.getLastname() == null) throw new ValidationException("Client lastname is empty.", "lastname");
        if(client.getDocnumber() == null) throw new ValidationException("Client docnumber is empty.", "docnumber");
       return this.clientRepository.save(client);
    }

    public List<Client> getClients() throws Exception{
        List<Client> lista = this.clientRepository.findAll();
        if(lista.isEmpty()) throw new Exception("List is empty");
        return lista;
    }

    public Client getClientById(int id) throws Exception{
        Optional<Client> clientFounded = this.clientRepository.findById(id);
        if(clientFounded.isEmpty()){
            throw new Exception("Client not found");
        }
        return clientFounded.get();
    }

    public Client deleteClient(int id) throws Exception{
        Optional<Client> clientDelete = this.clientRepository.findById(id);
        if(clientDelete.isEmpty()){
            throw new Exception("Client not found");
        }
        Client client = clientDelete.get();
        this.clientRepository.deleteById(id);
        return client;
    }

    public Client updateClient(int id, Client dataUpdate) throws Exception{
        Optional<Client> clientFoundUpdate = this.clientRepository.findById(id);
        if(clientFoundUpdate.isEmpty()){
            throw new Exception("Client not founded");
        }
        //Actualizamos el cliente
        Client clientUpdate = clientFoundUpdate.get();
        if(dataUpdate.getName() != null) clientUpdate.setName(dataUpdate.getName());
        if(dataUpdate.getLastname() != null) clientUpdate.setLastname(dataUpdate.getLastname());
        if(dataUpdate.getDocnumber() != null) clientUpdate.setDocnumber(dataUpdate.getDocnumber());
        //Guardamos el cliente actualizado
        this.clientRepository.save(clientUpdate);

        return this.clientRepository.findById(id).get();
    }
}
