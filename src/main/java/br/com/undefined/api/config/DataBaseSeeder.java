package br.com.undefined.api.config;

import br.com.undefined.api.entities.*;
import br.com.undefined.api.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;

import java.util.Arrays;
import java.util.Calendar;
import java.util.List;

@Configuration
public class DataBaseSeeder implements CommandLineRunner {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private RestaurantRepository restaurantRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Autowired
    private RatingRepository ratingRepository;

    @Override
    public void run(String... args) throws Exception {

        Phone phone = Phone.builder().DDI(55).DDD(11).phoneNumber("958676458").build();

        Client client = Client.clientBuilder().clientName("Marcos").phone(phone).build();
        clientRepository.save(client);
        clientRepository.flush();

        Restaurant restaurant = Restaurant.restaurantBuilder().restaurantName("Padoquinha do bem").phone(phone).build();
        restaurantRepository.save(restaurant);
        restaurantRepository.flush();

        Product product = Product.builder().name("Bolo").image("productImage.url").quantity(5)
                .description("Pense num bolo bom").pricePerUnit(5.80).restaurant(restaurant).build();
        productRepository.save(product);
        productRepository.flush();

        Product product2 = Product.builder().name("Pãozinho").image("productImage.url").quantity(5)
                .description("Pense num pão bom").pricePerUnit(5.85).restaurant(restaurant).build();
        productRepository.save(product2);
        productRepository.flush();

        Category category = Category.builder().name("Doces").description("docinho nham nham").productList(List.of(product, product2)).build();
        categoryRepository.save(category);
        categoryRepository.flush();

        restaurant.setProducts(List.of(product, product2));
        restaurantRepository.save(restaurant);
        restaurantRepository.flush();

        Rating rating = Rating.builder().client(client).product(product).stars(5)
                .restaurant(restaurant).date(Calendar.getInstance()).comment("BOM DEMAIS").build();
        ratingRepository.save(rating);
        ratingRepository.flush();

        product.setRatings(List.of(rating));
        productRepository.save(product);
        productRepository.flush();

    }
}
