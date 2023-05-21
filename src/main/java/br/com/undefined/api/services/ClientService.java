package br.com.undefined.api.services;

import br.com.undefined.api.dto.ClientDTO;
import br.com.undefined.api.entities.Client;
import br.com.undefined.api.entities.Order;
import br.com.undefined.api.entities.Rating;
import br.com.undefined.api.repositories.ClientRepository;
import org.hibernate.ObjectNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService implements UserDetailsService {

    @Autowired
    private ClientRepository clientRepository;

    public Client findById(Long id) {
        Optional<Client> prd = clientRepository.findById(id);
        return prd.orElseThrow(() -> new ObjectNotFoundException(Client.class, "Objeto não encontrado"));
    }

    public void delete(Long id) {
        clientRepository.deleteById(id);
    }

    public Client insert(Client client) {
        return clientRepository.save(client);
    }

    public Client update(Client client) {
        Client clt = findById(client.getId());
        dataUpdate(clt, client);
        return clientRepository.save(clt);
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return clientRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException("Cliente não encontrado"));
    }

    public void dataUpdate(Client cltToAtt, Client client) {
        cltToAtt.setClientName(client.getClientName());
        cltToAtt.setPhone(client.getPhone());
    }

    public Client fromDTO(ClientDTO clientDTO){
        return new Client(
                clientDTO.getClientName(),
                clientDTO.getPhone()
        );
    }

    public List<Order> findAllClientOrders(Long id) {
        return new ArrayList<Order>(clientRepository.findById(id).get().getOrders());
    }

    public List<Rating> findAllClientRatings(Long id) {
        return new ArrayList<Rating>(clientRepository.findById(id).get().getRatings());
    }
}
