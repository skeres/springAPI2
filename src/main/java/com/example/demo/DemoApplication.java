package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.Date;

@SpringBootApplication
//implements CommandLineRunner permet de lancer des commandes spécifique au demarrage de l'application.
//exemple : insérer des données dans une table
public class DemoApplication implements CommandLineRunner {

	//@Value("${logging.path}")
	//private String loggingPath;

	@Value("${logging.file.name}")
	private String loggingFileName;

	@Value("${server.port}")
	private String serverPort;

	@Value("${spring.datasource.url}")
	private String datasource;

	@Value("${spring.datasource.password}")
	private String password;

	@Value("${spring.datasource.username}")
	private String username;

	@Autowired  //permet d'injecter au runtime une implémentation de cette interface
	private EtudiantRepository etudiantRepository;

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@Override
	//methode overide de CommandLineRunner
	public void run(String... args) throws Exception {
		etudiantRepository.save(new Etudiant(null,"k","steph",new Date()));
		etudiantRepository.save(new Etudiant(null,"m","maria",new Date()));
		etudiantRepository.save(new Etudiant(null,"p","phil",new Date()));
		etudiantRepository.save(new Etudiant(null,"j","jessica",new Date()));
		etudiantRepository.save(new Etudiant(null,"z","jessica",new Date()));

		System.out.println(">>> serverPort ="+serverPort);
		//System.out.println(">>> loggingPath ="+loggingPath);
		System.out.println(">>> loggingPFile ="+loggingFileName);

		System.out.println("source : "+datasource);
		System.out.println("user : "+username);
		System.out.println("password : "+password);


	}
}
