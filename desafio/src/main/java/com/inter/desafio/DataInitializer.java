package com.inter.desafio;

import com.inter.desafio.model.entity.UserEntity;
import com.inter.desafio.model.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UserRepository userRepository;

    @Override
    public void run(String... args) {
        // Verifica se já existem usuários no banco
        if (userRepository.count() == 0) {
            // Cria e salva os usuários iniciais
            UserEntity user1 = new UserEntity("José Maria", "jose.maria@example.com", "senha123", "199.237.070-24");
            UserEntity user2 = new UserEntity("Ana Silva", "ana.silva@example.com", "senha123", "45.581.681/0001-67");
            UserEntity user3 = new UserEntity("Flavio Luna Ferreira", "flaviolunaferreira@gmail.com", "senha123", "98738267420");

            userRepository.save(user1);
            userRepository.save(user2);
            userRepository.save(user3);

            System.out.println("Usuários iniciais salvos no banco de dados.");
        } else {
            System.out.println("Usuários já existem no banco de dados. Nenhuma ação necessária.");
        }
    }
}
