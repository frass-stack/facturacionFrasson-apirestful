package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.exception.ValidationException;
import com.springboot.facturacionfrasson.model.Client;
import com.springboot.facturacionfrasson.model.ClientDTO;
import com.springboot.facturacionfrasson.repository.ClientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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

    public List<ClientDTO> getClients() throws Exception{
        List<Client> lista = this.clientRepository.findAll();
        List<ClientDTO> listaDTO = new ArrayList<>();
        if(lista.isEmpty()) throw new Exception("List is empty");

        for(Client clientFounded: lista){
            ClientDTO clientDTO = new ClientDTO(
                    clientFounded.getId(),
                    clientFounded.getName(),
                    clientFounded.getLastname(),
                    clientFounded.getDocnumber()
            );
            listaDTO.add(clientDTO);
        }

        return listaDTO;
    }

    public ClientDTO getClientById(int id) throws Exception{
        Optional<Client> clientFounded = this.clientRepository.findById(id);
        if(clientFounded.isEmpty()){
            throw new Exception("Client not found: " + id);
        }
        ClientDTO clientDTO = new ClientDTO(
                clientFounded.get().getId(),
                clientFounded.get().getName(),
                clientFounded.get().getLastname(),
                clientFounded.get().getDocnumber()
        );
        return clientDTO;
    }

    public void deleteClient(int id) throws Exception{
        Optional<Client> clientDelete = this.clientRepository.findById(id);
        if(clientDelete.isEmpty()){
            throw new Exception("Client not found: " + id);
        }
        Client client = clientDelete.get();
        this.clientRepository.deleteById(id);
    }

    public void updateClient(int id, Client dataUpdate) throws Exception{
        Optional<Client> clientFoundUpdate = this.clientRepository.findById(id);
        if(clientFoundUpdate.isEmpty()){
            throw new Exception("Client not founded: " + id);
        }
        //Actualizamos el cliente
        Client clientUpdate = clientFoundUpdate.get();
        if(dataUpdate.getName() != null) clientUpdate.setName(dataUpdate.getName());
        if(dataUpdate.getLastname() != null) clientUpdate.setLastname(dataUpdate.getLastname());
        if(dataUpdate.getDocnumber() != null) clientUpdate.setDocnumber(dataUpdate.getDocnumber());
        //Guardamos el cliente actualizado
        this.clientRepository.save(clientUpdate);
    }
}
