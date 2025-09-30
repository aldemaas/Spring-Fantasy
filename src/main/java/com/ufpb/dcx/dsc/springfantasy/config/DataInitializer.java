package com.ufpb.dcx.dsc.springfantasy.config;

import com.ufpb.dcx.dsc.springfantasy.enums.RodadaStatus;
import com.ufpb.dcx.dsc.springfantasy.enums.UserRole;
import com.ufpb.dcx.dsc.springfantasy.model.*;
import com.ufpb.dcx.dsc.springfantasy.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component
public class DataInitializer implements CommandLineRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private ClubeRepository clubeRepository;

    @Autowired
    private JogadorRepository jogadorRepository;

    @Autowired
    private RodadaRepository rodadaRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void run(String... args) throws Exception {
        if (usuarioRepository.count() == 0) {
            initializeData();
        }
    }

    private void initializeData() {
        // Criar usuário administrador
        Usuario admin = new Usuario();
        admin.setNome("Administrador");
        admin.setEmail("admin@springfantasy.com");
        admin.setSenha(passwordEncoder.encode("admin123"));
        admin.setRole(UserRole.ADMIN);
        usuarioRepository.save(admin);

        // Criar usuário comum para teste
        Usuario user = new Usuario();
        user.setNome("João Silva");
        user.setEmail("joao@email.com");
        user.setSenha(passwordEncoder.encode("123456"));
        user.setRole(UserRole.USER);
        usuarioRepository.save(user);

        // Criar clubes do Brasileirão
        Clube atleticoMG = criarClube("Atlético-MG");
        Clube botafogo = criarClube("Botafogo");
        Clube ceara = criarClube("Ceará");
        Clube corinthians = criarClube("Corinthians");
        Clube cruzeiro = criarClube("Cruzeiro");
        Clube flamengo = criarClube("Flamengo");
        Clube fluminense = criarClube("Fluminense");
        Clube fortaleza = criarClube("Fortaleza");
        Clube gremio = criarClube("Grêmio");
        Clube internacional = criarClube("Internacional");
        Clube juventude = criarClube("Juventude");
        Clube mirassol = criarClube("Mirassol");
        Clube palmeiras = criarClube("Palmeiras");
        Clube rbBragantino = criarClube("RB Bragantino");
        Clube santos = criarClube("Santos");
        Clube saoPaulo = criarClube("São Paulo");
        Clube sport = criarClube("Sport");
        Clube vasco = criarClube("Vasco");
        Clube vitoria = criarClube("Vitória");
        Clube bahia = criarClube("Bahia");

        // Criar jogadores do Atlético-MG
        criarJogador("Everson", "GOL", new BigDecimal("8.00"), atleticoMG);
        criarJogador("Gabriel Delfim", "GOL", new BigDecimal("6.50"), atleticoMG);
        criarJogador("Matheus Mendes", "GOL", new BigDecimal("5.50"), atleticoMG);
        criarJogador("Guilherme Arana", "DEF", new BigDecimal("7.50"), atleticoMG);
        criarJogador("Junior Alonso", "DEF", new BigDecimal("7.00"), atleticoMG);
        criarJogador("Nathan Silva", "DEF", new BigDecimal("7.00"), atleticoMG);
        criarJogador("Battaglia", "DEF", new BigDecimal("6.50"), atleticoMG);
        criarJogador("Mariano", "DEF", new BigDecimal("6.50"), atleticoMG);
        criarJogador("Rubens", "DEF", new BigDecimal("6.00"), atleticoMG);
        criarJogador("Renzo Saravia", "DEF", new BigDecimal("6.00"), atleticoMG);
        criarJogador("Bruno Fuchs", "DEF", new BigDecimal("5.50"), atleticoMG);
        criarJogador("Igor Rabello", "DEF", new BigDecimal("6.50"), atleticoMG);
        criarJogador("Lyanco", "DEF", new BigDecimal("7.50"), atleticoMG);
        criarJogador("Alan Franco", "MEI", new BigDecimal("8.00"), atleticoMG);
        criarJogador("Otávio", "MEI", new BigDecimal("7.50"), atleticoMG);
        criarJogador("Zaracho", "MEI", new BigDecimal("9.00"), atleticoMG);
        criarJogador("Gustavo Scarpa", "MEI", new BigDecimal("10.50"), atleticoMG);
        criarJogador("Allan", "MEI", new BigDecimal("8.50"), atleticoMG);
        criarJogador("Igor Gomes", "MEI", new BigDecimal("7.00"), atleticoMG);
        criarJogador("Robert Santos", "MEI", new BigDecimal("6.50"), atleticoMG);
        criarJogador("Paulo Vitor", "MEI", new BigDecimal("6.00"), atleticoMG);
        criarJogador("Bernard", "MEI", new BigDecimal("8.00"), atleticoMG);
        criarJogador("Hulk", "ATA", new BigDecimal("12.00"), atleticoMG);
        criarJogador("Paulinho", "ATA", new BigDecimal("9.50"), atleticoMG);
        criarJogador("Eduardo Vargas", "ATA", new BigDecimal("8.50"), atleticoMG);
        criarJogador("Cadu", "ATA", new BigDecimal("7.00"), atleticoMG);
        criarJogador("Alisson", "ATA", new BigDecimal("6.50"), atleticoMG);

        // Criar jogadores do Botafogo
        criarJogador("John", "GOL", new BigDecimal("8.50"), botafogo);
        criarJogador("Gatito Fernández", "GOL", new BigDecimal("7.50"), botafogo);
        criarJogador("Lucas Perri", "GOL", new BigDecimal("6.50"), botafogo);
        criarJogador("Raul", "GOL", new BigDecimal("5.50"), botafogo);
        criarJogador("Alexander Barboza", "DEF", new BigDecimal("7.50"), botafogo);
        criarJogador("Bastos", "DEF", new BigDecimal("7.00"), botafogo);
        criarJogador("Adryelson", "DEF", new BigDecimal("6.50"), botafogo);
        criarJogador("Cuiabano", "DEF", new BigDecimal("6.50"), botafogo);
        criarJogador("Hugo", "DEF", new BigDecimal("6.00"), botafogo);
        criarJogador("Rafael", "DEF", new BigDecimal("6.00"), botafogo);
        criarJogador("Mateo Ponte", "DEF", new BigDecimal("6.50"), botafogo);
        criarJogador("Vitinho", "DEF", new BigDecimal("6.00"), botafogo);
        criarJogador("Danilo Barbosa", "MEI", new BigDecimal("7.50"), botafogo);
        criarJogador("Marlon Freitas", "MEI", new BigDecimal("7.00"), botafogo);
        criarJogador("Tchê Tchê", "MEI", new BigDecimal("8.00"), botafogo);
        criarJogador("Thiago Almada", "MEI", new BigDecimal("11.00"), botafogo);
        criarJogador("Luiz Henrique", "MEI", new BigDecimal("9.50"), botafogo);
        criarJogador("Savarino", "MEI", new BigDecimal("8.50"), botafogo);
        criarJogador("Eduardo", "MEI", new BigDecimal("7.50"), botafogo);
        criarJogador("Jefferson Savarino", "MEI", new BigDecimal("8.00"), botafogo);
        criarJogador("Carlos Eduardo", "MEI", new BigDecimal("6.50"), botafogo);
        criarJogador("Kauê", "MEI", new BigDecimal("6.00"), botafogo);
        criarJogador("Igor Jesus", "ATA", new BigDecimal("9.50"), botafogo);
        criarJogador("Tiquinho Soares", "ATA", new BigDecimal("9.00"), botafogo);
        criarJogador("Júnior Santos", "ATA", new BigDecimal("8.50"), botafogo);
        criarJogador("Matheus Nascimento", "ATA", new BigDecimal("7.50"), botafogo);
        criarJogador("Oscar Romero", "ATA", new BigDecimal("8.00"), botafogo);

        // Criar jogadores do Ceará
        criarJogador("Richard", "GOL", new BigDecimal("6.50"), ceara);
        criarJogador("Fernando Miguel", "GOL", new BigDecimal("6.00"), ceara);
        criarJogador("Keiller", "GOL", new BigDecimal("5.50"), ceara);
        criarJogador("Bruno Ferreira", "GOL", new BigDecimal("5.00"), ceara);
        criarJogador("Rafael Ramos", "DEF", new BigDecimal("5.50"), ceara);
        criarJogador("Marllon", "DEF", new BigDecimal("6.00"), ceara);
        criarJogador("Luiz Otávio", "DEF", new BigDecimal("5.50"), ceara);
        criarJogador("Gabriel Lacerda", "DEF", new BigDecimal("5.00"), ceara);
        criarJogador("Dieguinho", "DEF", new BigDecimal("4.50"), ceara);
        criarJogador("Willian Machado", "DEF", new BigDecimal("5.00"), ceara);
        criarJogador("Nicolas", "DEF", new BigDecimal("4.50"), ceara);
        criarJogador("Éder", "DEF", new BigDecimal("5.50"), ceara);
        criarJogador("Marcos Victor", "DEF", new BigDecimal("4.50"), ceara);
        criarJogador("Fabiano Souza", "DEF", new BigDecimal("4.00"), ceara);
        criarJogador("Matheus Bahia", "DEF", new BigDecimal("4.50"), ceara);
        criarJogador("Matheus Araújo", "MEI", new BigDecimal("6.00"), ceara);
        criarJogador("Lucas Mugni", "MEI", new BigDecimal("5.50"), ceara);
        criarJogador("Vinicius Zanocelo", "MEI", new BigDecimal("5.00"), ceara);
        criarJogador("Richardson", "MEI", new BigDecimal("5.50"), ceara);
        criarJogador("Vina", "MEI", new BigDecimal("6.50"), ceara);
        criarJogador("Lucas Lima", "MEI", new BigDecimal("6.00"), ceara);
        criarJogador("Rodriguinho", "MEI", new BigDecimal("5.50"), ceara);
        criarJogador("Fernando Sobral", "MEI", new BigDecimal("5.00"), ceara);
        criarJogador("Lourenço", "MEI", new BigDecimal("4.50"), ceara);
        criarJogador("Pedro Henrique", "ATA", new BigDecimal("6.50"), ceara);
        criarJogador("Pedro Raul", "ATA", new BigDecimal("7.00"), ceara);
        criarJogador("Aylon", "ATA", new BigDecimal("5.50"), ceara);
        criarJogador("Galeano", "ATA", new BigDecimal("5.00"), ceara);
        criarJogador("Fernandinho", "ATA", new BigDecimal("4.50"), ceara);
        criarJogador("Guilherme", "ATA", new BigDecimal("5.00"), ceara);
        criarJogador("Lucca", "ATA", new BigDecimal("5.50"), ceara);

        // Criar jogadores do Corinthians
        criarJogador("Cássio", "GOL", new BigDecimal("8.00"), corinthians);
        criarJogador("Hugo Souza", "GOL", new BigDecimal("7.00"), corinthians);
        criarJogador("Matheus Donelli", "GOL", new BigDecimal("5.50"), corinthians);
        criarJogador("Bruno Méndez", "DEF", new BigDecimal("6.00"), corinthians);
        criarJogador("Cacá", "DEF", new BigDecimal("6.50"), corinthians);
        criarJogador("Gustavo Henrique", "DEF", new BigDecimal("6.00"), corinthians);
        criarJogador("Félix Torres", "DEF", new BigDecimal("7.50"), corinthians);
        criarJogador("Matheus Bidu", "DEF", new BigDecimal("6.50"), corinthians);
        criarJogador("Hugo", "DEF", new BigDecimal("5.50"), corinthians);
        criarJogador("Matheuzinho", "DEF", new BigDecimal("6.00"), corinthians);
        criarJogador("Leo Mana", "DEF", new BigDecimal("5.00"), corinthians);
        criarJogador("José Martínez", "MEI", new BigDecimal("7.50"), corinthians);
        criarJogador("Raniele", "MEI", new BigDecimal("6.50"), corinthians);
        criarJogador("Breno Bidon", "MEI", new BigDecimal("6.00"), corinthians);
        criarJogador("Ryan", "MEI", new BigDecimal("5.50"), corinthians);
        criarJogador("Alex Santana", "MEI", new BigDecimal("6.00"), corinthians);
        criarJogador("André Carrillo", "MEI", new BigDecimal("7.00"), corinthians);
        criarJogador("Rodrigo Garro", "MEI", new BigDecimal("8.50"), corinthians);
        criarJogador("Charles", "MEI", new BigDecimal("6.50"), corinthians);
        criarJogador("Igor Coronado", "MEI", new BigDecimal("7.50"), corinthians);
        criarJogador("Memphis Depay", "ATA", new BigDecimal("12.00"), corinthians);
        criarJogador("Yuri Alberto", "ATA", new BigDecimal("9.50"), corinthians);
        criarJogador("Romero", "ATA", new BigDecimal("8.00"), corinthians);
        criarJogador("Giovane", "ATA", new BigDecimal("6.50"), corinthians);
        criarJogador("Pedro Raul", "ATA", new BigDecimal("7.50"), corinthians);
        criarJogador("Talles Magno", "ATA", new BigDecimal("7.00"), corinthians);
        criarJogador("Héctor Hernández", "ATA", new BigDecimal("6.50"), corinthians);

        // Criar jogadores do Cruzeiro
        criarJogador("Cássio", "GOL", new BigDecimal("7.50"), cruzeiro);
        criarJogador("Anderson", "GOL", new BigDecimal("6.50"), cruzeiro);
        criarJogador("Rafael Cabral", "GOL", new BigDecimal("6.00"), cruzeiro);
        criarJogador("João Marcelo", "DEF", new BigDecimal("6.50"), cruzeiro);
        criarJogador("Villalba", "DEF", new BigDecimal("6.00"), cruzeiro);
        criarJogador("Zé Ivaldo", "DEF", new BigDecimal("7.00"), cruzeiro);
        criarJogador("Lucas Oliveira", "DEF", new BigDecimal("6.50"), cruzeiro);
        criarJogador("William", "DEF", new BigDecimal("6.50"), cruzeiro);
        criarJogador("Marlon", "DEF", new BigDecimal("6.00"), cruzeiro);
        criarJogador("Kaiki", "DEF", new BigDecimal("6.00"), cruzeiro);
        criarJogador("Fagner", "DEF", new BigDecimal("6.50"), cruzeiro);
        criarJogador("Wesley Gasolina", "MEI", new BigDecimal("7.50"), cruzeiro);
        criarJogador("Lucas Romero", "MEI", new BigDecimal("8.00"), cruzeiro);
        criarJogador("Matheus Henrique", "MEI", new BigDecimal("8.50"), cruzeiro);
        criarJogador("Lucas Silva", "MEI", new BigDecimal("7.50"), cruzeiro);
        criarJogador("Walace", "MEI", new BigDecimal("7.00"), cruzeiro);
        criarJogador("Vitinho", "MEI", new BigDecimal("7.50"), cruzeiro);
        criarJogador("Barreal", "MEI", new BigDecimal("8.00"), cruzeiro);
        criarJogador("Arthur Gomes", "MEI", new BigDecimal("6.50"), cruzeiro);
        criarJogador("Álvaro Pacheco", "MEI", new BigDecimal("6.00"), cruzeiro);
        criarJogador("Mateus Vital", "ATA", new BigDecimal("8.50"), cruzeiro);
        criarJogador("Lautaro Díaz", "ATA", new BigDecimal("8.00"), cruzeiro);
        criarJogador("Gabriel Veron", "ATA", new BigDecimal("9.00"), cruzeiro);
        criarJogador("Kaio Jorge", "ATA", new BigDecimal("9.50"), cruzeiro);
        criarJogador("Rafa Silva", "ATA", new BigDecimal("7.50"), cruzeiro);

        // Completar jogadores do Flamengo
        criarJogador("Rossi", "GOL", new BigDecimal("8.50"), flamengo);
        criarJogador("Matheus Cunha", "GOL", new BigDecimal("7.00"), flamengo);
        criarJogador("Dyogo Alves", "GOL", new BigDecimal("6.00"), flamengo);
        criarJogador("Alex Sandro", "DEF", new BigDecimal("8.00"), flamengo);
        criarJogador("Léo Pereira", "DEF", new BigDecimal("7.50"), flamengo);
        criarJogador("Fabrício Bruno", "DEF", new BigDecimal("8.00"), flamengo);
        criarJogador("Léo Ortiz", "DEF", new BigDecimal("7.50"), flamengo);
        criarJogador("Wesley", "DEF", new BigDecimal("6.50"), flamengo);
        criarJogador("Varela", "DEF", new BigDecimal("7.00"), flamengo);
        criarJogador("Ayrton Lucas", "DEF", new BigDecimal("7.00"), flamengo);
        criarJogador("David Luiz", "DEF", new BigDecimal("7.50"), flamengo);
        criarJogador("Cleiton", "DEF", new BigDecimal("6.00"), flamengo);
        criarJogador("Pulgar", "MEI", new BigDecimal("8.50"), flamengo);
        criarJogador("Gerson", "MEI", new BigDecimal("10.00"), flamengo);
        criarJogador("De La Cruz", "MEI", new BigDecimal("9.50"), flamengo);
        criarJogador("Arrascaeta", "MEI", new BigDecimal("11.80"), flamengo);
        criarJogador("Allan", "MEI", new BigDecimal("7.50"), flamengo);
        criarJogador("Evertton Araújo", "MEI", new BigDecimal("6.50"), flamengo);
        criarJogador("Alcaraz", "MEI", new BigDecimal("7.00"), flamengo);
        criarJogador("Lorran", "MEI", new BigDecimal("6.00"), flamengo);
        criarJogador("Michael", "ATA", new BigDecimal("8.50"), flamengo);
        criarJogador("Bruno Henrique", "ATA", new BigDecimal("10.50"), flamengo);
        criarJogador("Pedro", "ATA", new BigDecimal("11.50"), flamengo);
        criarJogador("Gabigol", "ATA", new BigDecimal("11.00"), flamengo);
        criarJogador("Carlinhos", "ATA", new BigDecimal("7.00"), flamengo);
        criarJogador("Gonzalo Plata", "ATA", new BigDecimal("8.00"), flamengo);

        // Completar jogadores do Fluminense
        criarJogador("Fábio", "GOL", new BigDecimal("8.50"), fluminense);
        criarJogador("Marcelo Pitaluga", "GOL", new BigDecimal("6.00"), fluminense);
        criarJogador("Vitor Eudes", "GOL", new BigDecimal("5.00"), fluminense);
        criarJogador("Samuel Xavier", "DEF", new BigDecimal("6.50"), fluminense);
        criarJogador("Thiago Silva", "DEF", new BigDecimal("9.00"), fluminense);
        criarJogador("Ignácio", "DEF", new BigDecimal("6.50"), fluminense);
        criarJogador("Renê", "DEF", new BigDecimal("6.00"), fluminense);
        criarJogador("Gabriel Fuentes", "DEF", new BigDecimal("5.50"), fluminense);
        criarJogador("Igor Rabello", "DEF", new BigDecimal("6.00"), fluminense);
        criarJogador("Freytes", "DEF", new BigDecimal("5.50"), fluminense);
        criarJogador("Guga", "DEF", new BigDecimal("5.00"), fluminense);
        criarJogador("Manoel", "DEF", new BigDecimal("5.50"), fluminense);
        criarJogador("Thiago Santos", "DEF", new BigDecimal("5.00"), fluminense);
        criarJogador("Júlio Fidelis", "DEF", new BigDecimal("4.50"), fluminense);
        criarJogador("Facundo Bernal", "MEI", new BigDecimal("7.00"), fluminense);
        criarJogador("Martinelli", "MEI", new BigDecimal("7.50"), fluminense);
        criarJogador("PH Ganso", "MEI", new BigDecimal("8.50"), fluminense);
        criarJogador("Nonato", "MEI", new BigDecimal("6.00"), fluminense);
        criarJogador("Lezcano", "MEI", new BigDecimal("6.50"), fluminense);
        criarJogador("Lucho Acosta", "MEI", new BigDecimal("7.00"), fluminense);
        criarJogador("Hércules", "MEI", new BigDecimal("6.50"), fluminense);
        criarJogador("Lima", "MEI", new BigDecimal("6.00"), fluminense);
        criarJogador("Wallace Davi", "MEI", new BigDecimal("5.00"), fluminense);
        criarJogador("Otávio", "MEI", new BigDecimal("5.50"), fluminense);
        criarJogador("Yeferson Soteldo", "ATA", new BigDecimal("9.50"), fluminense);
        criarJogador("Everaldo", "ATA", new BigDecimal("7.00"), fluminense);
        criarJogador("Keno", "ATA", new BigDecimal("8.00"), fluminense);
        criarJogador("Germán Cano", "ATA", new BigDecimal("10.50"), fluminense);
        criarJogador("Agustín Canobbio", "ATA", new BigDecimal("7.50"), fluminense);
        criarJogador("Lavega", "ATA", new BigDecimal("6.50"), fluminense);
        criarJogador("Riquelme", "ATA", new BigDecimal("6.00"), fluminense);
        criarJogador("Santi Moreno", "ATA", new BigDecimal("6.50"), fluminense);
        criarJogador("Kevin Serna", "ATA", new BigDecimal("7.00"), fluminense);
        criarJogador("John Kennedy", "ATA", new BigDecimal("7.50"), fluminense);

        // Criar jogadores do Fortaleza
        criarJogador("João Ricardo", "GOL", new BigDecimal("7.50"), fortaleza);
        criarJogador("Santos", "GOL", new BigDecimal("6.50"), fortaleza);
        criarJogador("Kozlinski", "GOL", new BigDecimal("5.50"), fortaleza);
        criarJogador("Emanuel Brítez", "DEF", new BigDecimal("7.50"), fortaleza);
        criarJogador("Kuscevic", "DEF", new BigDecimal("7.00"), fortaleza);
        criarJogador("Cardona", "DEF", new BigDecimal("7.00"), fortaleza);
        criarJogador("Tinga", "DEF", new BigDecimal("6.50"), fortaleza);
        criarJogador("Felipe Jonatan", "DEF", new BigDecimal("6.50"), fortaleza);
        criarJogador("Bruno Pacheco", "DEF", new BigDecimal("6.00"), fortaleza);
        criarJogador("Eros Mancuso", "DEF", new BigDecimal("6.00"), fortaleza);
        criarJogador("Diogo Barbosa", "DEF", new BigDecimal("5.50"), fortaleza);
        criarJogador("Pedro Augusto", "MEI", new BigDecimal("7.50"), fortaleza);
        criarJogador("Hércules", "MEI", new BigDecimal("7.00"), fortaleza);
        criarJogador("José Welison", "MEI", new BigDecimal("6.50"), fortaleza);
        criarJogador("Rossetto", "MEI", new BigDecimal("7.00"), fortaleza);
        criarJogador("Calebe", "MEI", new BigDecimal("6.50"), fortaleza);
        criarJogador("Pochettino", "MEI", new BigDecimal("8.00"), fortaleza);
        criarJogador("Lucas Sasha", "MEI", new BigDecimal("6.50"), fortaleza);
        criarJogador("Tomás Cardona", "MEI", new BigDecimal("6.00"), fortaleza);
        criarJogador("Renato Kayzer", "ATA", new BigDecimal("8.50"), fortaleza);
        criarJogador("Juan Martín Lucero", "ATA", new BigDecimal("9.00"), fortaleza);
        criarJogador("Moisés", "ATA", new BigDecimal("8.00"), fortaleza);
        criarJogador("Marinho", "ATA", new BigDecimal("9.50"), fortaleza);
        criarJogador("Yago Pikachu", "ATA", new BigDecimal("7.50"), fortaleza);
        criarJogador("Imanol Machuca", "ATA", new BigDecimal("7.00"), fortaleza);

        // Completar jogadores do Grêmio
        criarJogador("Tiago Volpi", "GOL", new BigDecimal("7.00"), gremio);
        criarJogador("Gabriel Grando", "GOL", new BigDecimal("6.50"), gremio);
        criarJogador("Thiago Beltrame", "GOL", new BigDecimal("5.50"), gremio);
        criarJogador("Jorge", "GOL", new BigDecimal("5.00"), gremio);
        criarJogador("João Lucas", "DEF", new BigDecimal("5.50"), gremio);
        criarJogador("Wagner Leonardo", "DEF", new BigDecimal("6.00"), gremio);
        criarJogador("Kannemann", "DEF", new BigDecimal("7.50"), gremio);
        criarJogador("Rodrigo Ely", "DEF", new BigDecimal("6.50"), gremio);
        criarJogador("Fabián Balbuena", "DEF", new BigDecimal("7.00"), gremio);
        criarJogador("Marcos Rocha", "DEF", new BigDecimal("6.00"), gremio);
        criarJogador("João Pedro", "DEF", new BigDecimal("5.50"), gremio);
        criarJogador("Jemerson", "DEF", new BigDecimal("6.50"), gremio);
        criarJogador("Marlon", "DEF", new BigDecimal("5.50"), gremio);
        criarJogador("Lucas Esteves", "DEF", new BigDecimal("5.00"), gremio);
        criarJogador("Enzo", "DEF", new BigDecimal("4.50"), gremio);
        criarJogador("Viery", "DEF", new BigDecimal("5.00"), gremio);
        criarJogador("Gustavo Martins", "DEF", new BigDecimal("4.50"), gremio);
        criarJogador("Cuéllar", "MEI", new BigDecimal("7.50"), gremio);
        criarJogador("Edenilson", "MEI", new BigDecimal("8.00"), gremio);
        criarJogador("Franco Cristaldo", "MEI", new BigDecimal("8.50"), gremio);
        criarJogador("Monsalve", "MEI", new BigDecimal("7.00"), gremio);
        criarJogador("Camilo", "MEI", new BigDecimal("6.00"), gremio);
        criarJogador("Dodi", "MEI", new BigDecimal("5.50"), gremio);
        criarJogador("Erick Noriega", "MEI", new BigDecimal("5.00"), gremio);
        criarJogador("Villasanti", "MEI", new BigDecimal("7.00"), gremio);
        criarJogador("Arthur", "MEI", new BigDecimal("6.50"), gremio);
        criarJogador("Riquelme", "MEI", new BigDecimal("6.00"), gremio);
        criarJogador("Alex Santana", "MEI", new BigDecimal("6.50"), gremio);
        criarJogador("Willian", "MEI", new BigDecimal("7.00"), gremio);
        criarJogador("Pavon", "ATA", new BigDecimal("8.00"), gremio);
        criarJogador("Amuzu", "ATA", new BigDecimal("7.50"), gremio);
        criarJogador("Aravena", "ATA", new BigDecimal("7.00"), gremio);
        criarJogador("Braithwaite", "ATA", new BigDecimal("9.00"), gremio);
        criarJogador("Alysson", "ATA", new BigDecimal("6.00"), gremio);
        criarJogador("André Henrique", "ATA", new BigDecimal("6.50"), gremio);
        criarJogador("Carlos Vinícius", "ATA", new BigDecimal("8.50"), gremio);
        criarJogador("Cristian Olivera", "ATA", new BigDecimal("8.00"), gremio);

        // Completar jogadores do Internacional
        criarJogador("Sergio Rochet", "GOL", new BigDecimal("7.50"), internacional);
        criarJogador("Anthoni", "GOL", new BigDecimal("6.50"), internacional);
        criarJogador("Ivan", "GOL", new BigDecimal("6.00"), internacional);
        criarJogador("Clayton Sampaio", "DEF", new BigDecimal("7.00"), internacional);
        criarJogador("Vitão", "DEF", new BigDecimal("7.00"), internacional);
        criarJogador("Rogel", "DEF", new BigDecimal("6.50"), internacional);
        criarJogador("Igor Gomes", "DEF", new BigDecimal("6.00"), internacional);
        criarJogador("Renê", "DEF", new BigDecimal("6.50"), internacional);
        criarJogador("Bernabei", "DEF", new BigDecimal("7.00"), internacional);
        criarJogador("Bruno Gomes", "DEF", new BigDecimal("6.50"), internacional);
        criarJogador("Nathan", "DEF", new BigDecimal("6.00"), internacional);
        criarJogador("Fernando", "MEI", new BigDecimal("8.00"), internacional);
        criarJogador("Thiago Maia", "MEI", new BigDecimal("7.50"), internacional);
        criarJogador("Bruno Henrique", "MEI", new BigDecimal("7.00"), internacional);
        criarJogador("Rômulo", "MEI", new BigDecimal("6.50"), internacional);
        criarJogador("Bruno Tabata", "MEI", new BigDecimal("8.50"), internacional);
        criarJogador("Alan Patrick", "MEI", new BigDecimal("9.50"), internacional);
        criarJogador("Wesley", "MEI", new BigDecimal("7.50"), internacional);
        criarJogador("Gustavo Prado", "MEI", new BigDecimal("6.50"), internacional);
        criarJogador("Wanderson", "ATA", new BigDecimal("8.00"), internacional);
        criarJogador("Rafael Borré", "ATA", new BigDecimal("10.00"), internacional);
        criarJogador("Enner Valencia", "ATA", new BigDecimal("10.50"), internacional);
        criarJogador("Lucca", "ATA", new BigDecimal("7.50"), internacional);
        criarJogador("Ricardo Mathias", "ATA", new BigDecimal("7.00"), internacional);

        // Completar jogadores do Juventude
        criarJogador("Marcão", "GOL", new BigDecimal("6.00"), juventude);
        criarJogador("Zé Henrique", "GOL", new BigDecimal("5.50"), juventude);
        criarJogador("Dida", "GOL", new BigDecimal("5.00"), juventude);
        criarJogador("Gustavo", "GOL", new BigDecimal("4.50"), juventude);
        criarJogador("Reginaldo", "DEF", new BigDecimal("5.00"), juventude);
        criarJogador("Ewerthon", "DEF", new BigDecimal("4.50"), juventude);
        criarJogador("João Vitor", "DEF", new BigDecimal("5.50"), juventude);
        criarJogador("Alan Ruschel", "DEF", new BigDecimal("6.00"), juventude);
        criarJogador("Felipinho", "DEF", new BigDecimal("4.50"), juventude);
        criarJogador("Marcos Paulo", "DEF", new BigDecimal("5.00"), juventude);
        criarJogador("Danilo Boza", "DEF", new BigDecimal("6.50"), juventude);
        criarJogador("Rodrigo Sam", "DEF", new BigDecimal("6.00"), juventude);
        criarJogador("Abner", "DEF", new BigDecimal("5.50"), juventude);
        criarJogador("Adriano Martins", "DEF", new BigDecimal("5.50"), juventude);
        criarJogador("Jadson", "MEI", new BigDecimal("6.00"), juventude);
        criarJogador("Dudu Vieira", "MEI", new BigDecimal("5.50"), juventude);
        criarJogador("Kelvi", "MEI", new BigDecimal("5.00"), juventude);
        criarJogador("Davi Góes", "MEI", new BigDecimal("4.50"), juventude);
        criarJogador("Caíque", "MEI", new BigDecimal("5.00"), juventude);
        criarJogador("Peixoto", "MEI", new BigDecimal("5.50"), juventude);
        criarJogador("Mandaca", "MEI", new BigDecimal("6.50"), juventude);
        criarJogador("Jean Carlos", "MEI", new BigDecimal("6.00"), juventude);
        criarJogador("LH", "MEI", new BigDecimal("5.50"), juventude);
        criarJogador("Nenê", "MEI", new BigDecimal("8.00"), juventude);
        criarJogador("Gabriel Taliari", "ATA", new BigDecimal("7.00"), juventude);
        criarJogador("Erick Farias", "ATA", new BigDecimal("6.50"), juventude);
        criarJogador("Ênio", "ATA", new BigDecimal("6.00"), juventude);
        criarJogador("Gilberto", "ATA", new BigDecimal("7.50"), juventude);
        criarJogador("Emerson Batalla", "ATA", new BigDecimal("6.50"), juventude);
        criarJogador("Petterson", "ATA", new BigDecimal("5.50"), juventude);
        criarJogador("Vitor Pernambucano", "ATA", new BigDecimal("6.00"), juventude);
        criarJogador("Giovanny", "ATA", new BigDecimal("6.50"), juventude);
        criarJogador("Maurício Garcez", "ATA", new BigDecimal("6.00"), juventude);
        criarJogador("Matheus Babi", "ATA", new BigDecimal("7.00"), juventude);
        criarJogador("Ronie Carrillo", "ATA", new BigDecimal("7.50"), juventude);

        // Criar jogadores do Mirassol
        criarJogador("Walter", "GOL", new BigDecimal("6.50"), mirassol);
        criarJogador("Alex Muralha", "GOL", new BigDecimal("6.00"), mirassol);
        criarJogador("Bertinato", "GOL", new BigDecimal("5.50"), mirassol);
        criarJogador("Thomazzela", "GOL", new BigDecimal("5.00"), mirassol);
        criarJogador("Gabriel K.", "DEF", new BigDecimal("5.50"), mirassol);
        criarJogador("Jemmes", "DEF", new BigDecimal("6.00"), mirassol);
        criarJogador("João Victor", "DEF", new BigDecimal("5.50"), mirassol);
        criarJogador("Luiz Otávio", "DEF", new BigDecimal("6.50"), mirassol);
        criarJogador("David Braz", "DEF", new BigDecimal("7.00"), mirassol);
        criarJogador("Daniel Borges", "DEF", new BigDecimal("5.50"), mirassol);
        criarJogador("Lucas Ramon", "DEF", new BigDecimal("5.00"), mirassol);
        criarJogador("Reinaldo", "DEF", new BigDecimal("7.50"), mirassol);
        criarJogador("Felipe Jonatan", "DEF", new BigDecimal("6.00"), mirassol);
        criarJogador("PH", "DEF", new BigDecimal("4.50"), mirassol);
        criarJogador("Roni", "MEI", new BigDecimal("6.00"), mirassol);
        criarJogador("Sales", "MEI", new BigDecimal("5.50"), mirassol);
        criarJogador("Matheus Bianqui", "MEI", new BigDecimal("5.50"), mirassol);
        criarJogador("Neto Moura", "MEI", new BigDecimal("6.50"), mirassol);
        criarJogador("Yago Felipe", "MEI", new BigDecimal("6.00"), mirassol);
        criarJogador("Shaylon", "MEI", new BigDecimal("7.00"), mirassol);
        criarJogador("Danielzinho", "MEI", new BigDecimal("6.50"), mirassol);
        criarJogador("Gabriel", "MEI", new BigDecimal("6.00"), mirassol);
        criarJogador("Guilherme Marques", "MEI", new BigDecimal("5.50"), mirassol);
        criarJogador("José Aldo", "MEI", new BigDecimal("5.00"), mirassol);
        criarJogador("Negueba", "ATA", new BigDecimal("7.50"), mirassol);
        criarJogador("Renato Marques", "ATA", new BigDecimal("6.50"), mirassol);
        criarJogador("Alesson", "ATA", new BigDecimal("6.00"), mirassol);
        criarJogador("Carlos Eduardo", "ATA", new BigDecimal("7.00"), mirassol);
        criarJogador("Chico Kim", "ATA", new BigDecimal("6.50"), mirassol);
        criarJogador("da Costa", "ATA", new BigDecimal("6.00"), mirassol);
        criarJogador("Edson Carioca", "ATA", new BigDecimal("6.50"), mirassol);
        criarJogador("Clayson", "ATA", new BigDecimal("7.50"), mirassol);
        criarJogador("Guilherme Pato", "ATA", new BigDecimal("7.00"), mirassol);
        criarJogador("Matheus Davó", "ATA", new BigDecimal("8.00"), mirassol);

        // Criar jogadores do Palmeiras
        criarJogador("Weverton", "GOL", new BigDecimal("9.00"), palmeiras);
        criarJogador("Marcelo Lomba", "GOL", new BigDecimal("7.00"), palmeiras);
        criarJogador("Mateus", "GOL", new BigDecimal("6.00"), palmeiras);
        criarJogador("Marcos Rocha", "DEF", new BigDecimal("7.00"), palmeiras);
        criarJogador("Mayke", "DEF", new BigDecimal("6.50"), palmeiras);
        criarJogador("Gustavo Gómez", "DEF", new BigDecimal("8.50"), palmeiras);
        criarJogador("Murilo", "DEF", new BigDecimal("7.50"), palmeiras);
        criarJogador("Vitor Reis", "DEF", new BigDecimal("7.00"), palmeiras);
        criarJogador("Piquerez", "DEF", new BigDecimal("7.50"), palmeiras);
        criarJogador("Caio Paulista", "DEF", new BigDecimal("6.50"), palmeiras);
        criarJogador("Vanderlan", "DEF", new BigDecimal("6.00"), palmeiras);
        criarJogador("Naves", "DEF", new BigDecimal("5.50"), palmeiras);
        criarJogador("Aníbal Moreno", "MEI", new BigDecimal("7.50"), palmeiras);
        criarJogador("Zé Rafael", "MEI", new BigDecimal("8.50"), palmeiras);
        criarJogador("Richard Ríos", "MEI", new BigDecimal("8.00"), palmeiras);
        criarJogador("Raphael Veiga", "MEI", new BigDecimal("11.00"), palmeiras);
        criarJogador("Maurício", "MEI", new BigDecimal("8.00"), palmeiras);
        criarJogador("Gabriel Menino", "MEI", new BigDecimal("7.50"), palmeiras);
        criarJogador("Fabinho", "MEI", new BigDecimal("6.50"), palmeiras);
        criarJogador("Allan", "MEI", new BigDecimal("6.00"), palmeiras);
        criarJogador("Estêvão", "ATA", new BigDecimal("12.00"), palmeiras);
        criarJogador("Dudu", "ATA", new BigDecimal("10.50"), palmeiras);
        criarJogador("Rony", "ATA", new BigDecimal("9.50"), palmeiras);
        criarJogador("Felipe Anderson", "ATA", new BigDecimal("10.00"), palmeiras);
        criarJogador("José López", "ATA", new BigDecimal("9.00"), palmeiras);
        criarJogador("Flaco López", "ATA", new BigDecimal("8.50"), palmeiras);
        criarJogador("Lázaro", "ATA", new BigDecimal("7.00"), palmeiras);

        // Criar jogadores do RB Bragantino
        criarJogador("Cleiton", "GOL", new BigDecimal("7.50"), rbBragantino);
        criarJogador("Fernando Costa", "GOL", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Fabrício", "GOL", new BigDecimal("5.50"), rbBragantino);
        criarJogador("Lucão", "GOL", new BigDecimal("5.00"), rbBragantino);
        criarJogador("Guzmán Rodríguez", "DEF", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Eduardo Santos", "DEF", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Alix Vinicius", "DEF", new BigDecimal("5.50"), rbBragantino);
        criarJogador("Pedro Henrique", "DEF", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Gustavo Marques", "DEF", new BigDecimal("5.50"), rbBragantino);
        criarJogador("Vanderlan", "DEF", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Juninho Capixaba", "DEF", new BigDecimal("7.00"), rbBragantino);
        criarJogador("Guilherme Lopes", "DEF", new BigDecimal("5.00"), rbBragantino);
        criarJogador("Agustín Sant'Anna", "DEF", new BigDecimal("5.50"), rbBragantino);
        criarJogador("Andrés Hurtado", "DEF", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Nathan Mendes", "DEF", new BigDecimal("5.00"), rbBragantino);
        criarJogador("Fabinho", "MEI", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Gabriel", "MEI", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Matheus Fernandes", "MEI", new BigDecimal("7.00"), rbBragantino);
        criarJogador("Ramires", "MEI", new BigDecimal("8.00"), rbBragantino);
        criarJogador("Jhon Jhon", "MEI", new BigDecimal("7.50"), rbBragantino);
        criarJogador("Gustavo Neves", "MEI", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Praxedes", "MEI", new BigDecimal("6.50"), rbBragantino);
        criarJogador("João Neto", "MEI", new BigDecimal("5.50"), rbBragantino);
        criarJogador("Sasha", "ATA", new BigDecimal("8.50"), rbBragantino);
        criarJogador("Pitta", "ATA", new BigDecimal("7.50"), rbBragantino);
        criarJogador("Fernando", "ATA", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Vinicinho", "ATA", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Thiago Borbas", "ATA", new BigDecimal("8.00"), rbBragantino);
        criarJogador("Lucas Barbosa", "ATA", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Mosquera", "ATA", new BigDecimal("7.00"), rbBragantino);
        criarJogador("Nacho", "ATA", new BigDecimal("6.50"), rbBragantino);
        criarJogador("Marcelinho Braz", "ATA", new BigDecimal("6.00"), rbBragantino);
        criarJogador("Athyrson", "ATA", new BigDecimal("5.50"), rbBragantino);

        // Criar jogadores do Santos
        criarJogador("Diógenes", "GOL", new BigDecimal("6.50"), santos);
        criarJogador("João Pedro", "GOL", new BigDecimal("6.00"), santos);
        criarJogador("Gabriel Brazão", "GOL", new BigDecimal("7.50"), santos);
        criarJogador("João Fernandes", "GOL", new BigDecimal("5.50"), santos);
        criarJogador("Mayke", "DEF", new BigDecimal("6.00"), santos);
        criarJogador("Igor Vinícius", "DEF", new BigDecimal("5.50"), santos);
        criarJogador("JP Chermont", "DEF", new BigDecimal("5.00"), santos);
        criarJogador("Escobar", "DEF", new BigDecimal("6.50"), santos);
        criarJogador("Souza", "DEF", new BigDecimal("5.50"), santos);
        criarJogador("João Basso", "DEF", new BigDecimal("7.00"), santos);
        criarJogador("Luan Peres", "DEF", new BigDecimal("7.50"), santos);
        criarJogador("Alexis Duarte", "DEF", new BigDecimal("6.00"), santos);
        criarJogador("Luisão", "DEF", new BigDecimal("5.50"), santos);
        criarJogador("João Ananias", "DEF", new BigDecimal("5.00"), santos);
        criarJogador("Zé Ivaldo", "DEF", new BigDecimal("6.50"), santos);
        criarJogador("Vinicius Lira", "DEF", new BigDecimal("5.00"), santos);
        criarJogador("Adonis Frías", "DEF", new BigDecimal("6.00"), santos);
        criarJogador("João Schmidt", "MEI", new BigDecimal("7.00"), santos);
        criarJogador("Zé Rafael", "MEI", new BigDecimal("8.00"), santos);
        criarJogador("Tomás Rincón", "MEI", new BigDecimal("8.50"), santos);
        criarJogador("Willian Arão", "MEI", new BigDecimal("7.50"), santos);
        criarJogador("Hyan", "MEI", new BigDecimal("5.50"), santos);
        criarJogador("Thaciano", "MEI", new BigDecimal("7.00"), santos);
        criarJogador("Victor Hugo", "MEI", new BigDecimal("6.50"), santos);
        criarJogador("Padula", "MEI", new BigDecimal("6.00"), santos);
        criarJogador("Mateus Xavier", "MEI", new BigDecimal("5.50"), santos);
        criarJogador("Gabriel Bontempo", "MEI", new BigDecimal("5.00"), santos);
        criarJogador("Robinho Jr.", "ATA", new BigDecimal("6.50"), santos);
        criarJogador("Tiquinho Soares", "ATA", new BigDecimal("9.00"), santos);
        criarJogador("Neymar", "ATA", new BigDecimal("15.00"), santos);
        criarJogador("Guilherme", "ATA", new BigDecimal("6.00"), santos);
        criarJogador("Caballero", "ATA", new BigDecimal("7.50"), santos);
        criarJogador("Lautaro Díaz", "ATA", new BigDecimal("7.00"), santos);
        criarJogador("Brahimi", "ATA", new BigDecimal("8.00"), santos);
        criarJogador("Barreal", "ATA", new BigDecimal("7.50"), santos);
        criarJogador("Rollheiser", "ATA", new BigDecimal("7.00"), santos);

        // Criar jogadores do São Paulo
        criarJogador("Rafael", "GOL", new BigDecimal("8.00"), saoPaulo);
        criarJogador("Jandrei", "GOL", new BigDecimal("7.50"), saoPaulo);
        criarJogador("Young", "GOL", new BigDecimal("6.00"), saoPaulo);
        criarJogador("Leandro", "GOL", new BigDecimal("5.50"), saoPaulo);
        criarJogador("Igor Vinícius", "DEF", new BigDecimal("7.00"), saoPaulo);
        criarJogador("Rafinha", "DEF", new BigDecimal("7.50"), saoPaulo);
        criarJogador("Arboleda", "DEF", new BigDecimal("7.50"), saoPaulo);
        criarJogador("Alan Franco", "DEF", new BigDecimal("7.00"), saoPaulo);
        criarJogador("Ruan", "DEF", new BigDecimal("6.50"), saoPaulo);
        criarJogador("Welington", "DEF", new BigDecimal("6.50"), saoPaulo);
        criarJogador("Patryck", "DEF", new BigDecimal("6.00"), saoPaulo);
        criarJogador("Michel Araújo", "DEF", new BigDecimal("6.50"), saoPaulo);
        criarJogador("Sabino", "DEF", new BigDecimal("6.00"), saoPaulo);
        criarJogador("Luiz Gustavo", "MEI", new BigDecimal("8.50"), saoPaulo);
        criarJogador("Pablo Maia", "MEI", new BigDecimal("8.50"), saoPaulo);
        criarJogador("Alisson", "MEI", new BigDecimal("7.50"), saoPaulo);
        criarJogador("Marcos Antônio", "MEI", new BigDecimal("7.00"), saoPaulo);
        criarJogador("Bobadilla", "MEI", new BigDecimal("7.50"), saoPaulo);
        criarJogador("Rodrigo Nestor", "MEI", new BigDecimal("7.00"), saoPaulo);
        criarJogador("Wellington Rato", "MEI", new BigDecimal("8.00"), saoPaulo);
        criarJogador("Lucas", "MEI", new BigDecimal("8.50"), saoPaulo);
        criarJogador("Ferreira", "ATA", new BigDecimal("8.50"), saoPaulo);
        criarJogador("Calleri", "ATA", new BigDecimal("10.00"), saoPaulo);
        criarJogador("Luciano", "ATA", new BigDecimal("9.50"), saoPaulo);
        criarJogador("William Gomes", "ATA", new BigDecimal("7.50"), saoPaulo);
        criarJogador("André Silva", "ATA", new BigDecimal("9.00"), saoPaulo);
        criarJogador("Erick", "ATA", new BigDecimal("7.00"), saoPaulo);

        // Criar jogadores do Sport
        criarJogador("Gabriel", "GOL", new BigDecimal("6.50"), sport);
        criarJogador("Caíque França", "GOL", new BigDecimal("6.00"), sport);
        criarJogador("Davi", "GOL", new BigDecimal("5.50"), sport);
        criarJogador("Adriano", "GOL", new BigDecimal("5.00"), sport);
        criarJogador("João Silva", "DEF", new BigDecimal("5.50"), sport);
        criarJogador("Rafael Thyere", "DEF", new BigDecimal("6.00"), sport);
        criarJogador("Ramon", "DEF", new BigDecimal("6.50"), sport);
        criarJogador("Lucas Cunha", "DEF", new BigDecimal("5.50"), sport);
        criarJogador("Renzo", "DEF", new BigDecimal("5.00"), sport);
        criarJogador("Aderlan", "DEF", new BigDecimal("5.50"), sport);
        criarJogador("Hereda", "DEF", new BigDecimal("5.00"), sport);
        criarJogador("Matheus Alexandre", "DEF", new BigDecimal("5.50"), sport);
        criarJogador("Kevyson", "DEF", new BigDecimal("5.00"), sport);
        criarJogador("Igor Cariús", "DEF", new BigDecimal("5.50"), sport);
        criarJogador("Luan Cândido", "DEF", new BigDecimal("6.00"), sport);
        criarJogador("Victor Hugo", "DEF", new BigDecimal("5.00"), sport);
        criarJogador("Christian Rivera", "MEI", new BigDecimal("6.50"), sport);
        criarJogador("Lucas Kal", "MEI", new BigDecimal("6.00"), sport);
        criarJogador("Sérgio Oliveira", "MEI", new BigDecimal("7.50"), sport);
        criarJogador("Pedro Augusto", "MEI", new BigDecimal("6.00"), sport);
        criarJogador("Zé Lucas", "MEI", new BigDecimal("5.50"), sport);
        criarJogador("Lucas Lima", "MEI", new BigDecimal("7.00"), sport);
        criarJogador("Matheusinho", "MEI", new BigDecimal("6.50"), sport);
        criarJogador("Hyoran", "MEI", new BigDecimal("7.00"), sport);
        criarJogador("Gonçalo Paciência", "ATA", new BigDecimal("8.50"), sport);
        criarJogador("Colo Ramírez", "ATA", new BigDecimal("7.00"), sport);
        criarJogador("Romarinho", "ATA", new BigDecimal("6.50"), sport);
        criarJogador("Derik Lacerda", "ATA", new BigDecimal("7.50"), sport);
        criarJogador("Rodrigo Atencio", "ATA", new BigDecimal("6.00"), sport);
        criarJogador("Chrystian Barletta", "ATA", new BigDecimal("6.50"), sport);
        criarJogador("Léo Pereira", "ATA", new BigDecimal("6.00"), sport);
        criarJogador("Pablo", "ATA", new BigDecimal("7.00"), sport);
        criarJogador("Zé Roberto", "ATA", new BigDecimal("6.50"), sport);

        // Criar jogadores do Vasco
        criarJogador("Vegetti", "ATA", new BigDecimal("9.50"), vasco);
        criarJogador("Rayan", "MEI", new BigDecimal("6.50"), vasco);
        criarJogador("Léo Jardim", "GOL", new BigDecimal("7.00"), vasco);
        criarJogador("Capasso", "DEF", new BigDecimal("6.50"), vasco);
        criarJogador("Pumita Rodríguez", "DEF", new BigDecimal("6.00"), vasco);
        criarJogador("Andrey Santos", "MEI", new BigDecimal("8.50"), vasco);
        criarJogador("Praxedes", "MEI", new BigDecimal("7.00"), vasco);
        criarJogador("Payet", "ATA", new BigDecimal("9.50"), vasco);
        criarJogador("Gabriel Pec", "ATA", new BigDecimal("7.50"), vasco);

        // Criar jogadores do Vitória
        criarJogador("Kike Saveiro", "ATA", new BigDecimal("6.50"), vitoria);
        criarJogador("Lucas Arcanjo", "GOL", new BigDecimal("6.50"), vitoria);
        criarJogador("Marco Antônio", "DEF", new BigDecimal("6.00"), vitoria);
        criarJogador("Camutanga", "DEF", new BigDecimal("6.50"), vitoria);
        criarJogador("Léo Gomes", "MEI", new BigDecimal("7.00"), vitoria);
        criarJogador("Giovanni Augusto", "MEI", new BigDecimal("7.50"), vitoria);
        criarJogador("Osvaldo", "ATA", new BigDecimal("6.50"), vitoria);
        criarJogador("Tréllez", "ATA", new BigDecimal("7.00"), vitoria);

        // Criar jogadores do Bahia
        criarJogador("Marcos Felipe", "GOL", new BigDecimal("7.50"), bahia);
        criarJogador("Adriel", "GOL", new BigDecimal("6.50"), bahia);
        criarJogador("Danilo Fernandes", "GOL", new BigDecimal("6.00"), bahia);
        criarJogador("Mateus Claus", "GOL", new BigDecimal("5.50"), bahia);
        criarJogador("Gilberto", "DEF", new BigDecimal("7.00"), bahia);
        criarJogador("Santiago Arias", "DEF", new BigDecimal("7.50"), bahia);
        criarJogador("Gabriel Xavier", "DEF", new BigDecimal("7.00"), bahia);
        criarJogador("Kanu", "DEF", new BigDecimal("6.50"), bahia);
        criarJogador("David Duarte", "DEF", new BigDecimal("6.00"), bahia);
        criarJogador("Luciano Juba", "DEF", new BigDecimal("6.50"), bahia);
        criarJogador("Ryan", "DEF", new BigDecimal("6.00"), bahia);
        criarJogador("Iago Borduchi", "DEF", new BigDecimal("5.50"), bahia);
        criarJogador("Acevedo", "MEI", new BigDecimal("7.50"), bahia);
        criarJogador("Everton Ribeiro", "MEI", new BigDecimal("9.50"), bahia);
        criarJogador("Jean Lucas", "MEI", new BigDecimal("8.50"), bahia);
        criarJogador("Caio Alexandre", "MEI", new BigDecimal("7.00"), bahia);
        criarJogador("Carlos de Pena", "MEI", new BigDecimal("8.00"), bahia);
        criarJogador("Cauly", "MEI", new BigDecimal("8.00"), bahia);
        criarJogador("Rezende", "MEI", new BigDecimal("7.50"), bahia);
        criarJogador("Yago Felipe", "MEI", new BigDecimal("6.50"), bahia);
        criarJogador("Nico Acevedo", "MEI", new BigDecimal("6.00"), bahia);
        criarJogador("Thaciano", "ATA", new BigDecimal("8.50"), bahia);
        criarJogador("Everaldo", "ATA", new BigDecimal("8.00"), bahia);
        criarJogador("Ademir", "ATA", new BigDecimal("7.50"), bahia);
        criarJogador("Biel", "ATA", new BigDecimal("7.00"), bahia);
        criarJogador("Rafael Ratão", "ATA", new BigDecimal("7.50"), bahia);
        criarJogador("Luciano Rodríguez", "ATA", new BigDecimal("8.00"), bahia);
        criarJogador("Estupiñán", "ATA", new BigDecimal("7.00"), bahia);

        // Criar primeira rodada
        Rodada rodada1 = new Rodada();
        rodada1.setNumero(1);
        rodada1.setStatus(RodadaStatus.MERCADO_ABERTO);
        rodadaRepository.save(rodada1);

        System.out.println("✅ Dados iniciais criados com sucesso!");
        System.out.println("⚽ Todos os clubes do Brasileirão e seus jogadores foram criados!");
        System.out.println("🏆 Rodada 1 criada com mercado ABERTO");
        System.out.println("📈 Total de " + clubeRepository.count() + " clubes e " + jogadorRepository.count() + " jogadores cadastrados");
    }

    private Clube criarClube(String nome) {
        Clube clube = new Clube();
        clube.setNome(nome);
        return clubeRepository.save(clube);
    }

    private void criarJogador(String nome, String posicao, BigDecimal preco, Clube clube) {
        Jogador jogador = new Jogador();
        jogador.setNome(nome);
        jogador.setPosicao(posicao);
        jogador.setPreco(preco);
        jogador.setClube(clube);
        jogadorRepository.save(jogador);
    }
}
