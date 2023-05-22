package br.com.undefined.api.controllers;

import br.com.undefined.api.dto.ClientDTO;
import br.com.undefined.api.entities.Client;
import br.com.undefined.api.entities.Restaurant;
import br.com.undefined.api.services.ClientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping(value = "/clients")
public class ClientController {

    @Autowired
    private ClientService clientService;

    @Autowired
    private PagedResourcesAssembler<Restaurant> assembler;

    @GetMapping(value = "/{id}")
    public ResponseEntity<Client> findById(@PathVariable Long id) {
        Client clt = clientService.findById(id);
        return ResponseEntity.ok().body(clt);
    }

    @PostMapping(value = "/add")
    public ResponseEntity<Void> create(ClientDTO dtoBody) {
        Client clt = clientService.fromDTO(dtoBody);
        clt = clientService.insert(clt);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(clt.getId()).toUri();
        return ResponseEntity.created(uri).build();
    }

    @PutMapping(value = "/{id}")
    public ResponseEntity<Void> update(@RequestBody ClientDTO clientDTO, @PathVariable Long id){
        Client client = clientService.fromDTO(clientDTO);
        client.setId(id);
        clientService.update(client);
        return ResponseEntity.noContent().build();
    }

}
