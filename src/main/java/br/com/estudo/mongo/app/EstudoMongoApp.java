package br.com.estudo.mongo.app;

import br.com.estudo.mongo.app.dao.PersonDAO;
import br.com.estudo.mongo.app.entities.Address;
import br.com.estudo.mongo.app.entities.Person;

import java.util.*;

public class EstudoMongoApp {

    private static PersonDAO personDAO;

    public static void main(String[] args) {
        personDAO = new PersonDAO();
        Scanner scanner = new Scanner(System.in);
        int option;

        System.out.println("Escolha o número da opção desejada:");
        System.out.println("1 - Inserir pessoas");
        System.out.println("2 - Atualizar pessoas");
        System.out.println("3 - Deletar pessoas");
        System.out.println("4 - Consultar pessoas");
        System.out.println("5 - Consultar pessoas por CPF");
        option = scanner.nextInt();

        switch (option) {
            case 1:
                insertPerson(scanner);
                break;
            case 2:
                updatePerson(scanner);
                break;
            case 3:
                deletePerson(scanner);
                break;
            case 4:
                findPerson();
                break;
            case 5:
                findPersonById(scanner);
                break;
            default:
                System.out.println("Opção inválida!!");
        }
    }

    private static void insertPerson(Scanner scanner) {
        Person p = new Person();
        Address a = new Address();
        System.out.println("Informe os dados da pessoa.");
        System.out.println("CPF: ");
        p.setId(scanner.next());
        System.out.println("NOME: ");
        p.setName(scanner.next());

        System.out.println("RUA: ");
        a.setStreet(scanner.next());
        System.out.println("CIDADE: ");
        a.setCity(scanner.next());
        System.out.println("ESTADO: ");
        a.setState(scanner.next());
        System.out.println("CEP: ");
        a.setZip(scanner.next());
        p.setAddress(a);

        System.out.println("DIGITE O CÓDIGO DOS LIVROS PREFERIDOS SEPARADOS POR VÍRGULA: ");
        String strBooks = scanner.next();
        List<Integer> books = new ArrayList<>();
        for(String book : strBooks.split(",")) {
            books.add(Integer.valueOf(book));
        }
        p.setBooks(books);
        personDAO.createPerson(p);
    }

    private static void updatePerson(Scanner scanner) {
        Map<String, String> map = new HashMap<>();
        String cpf;
        System.out.println("Informe o CPF da pessoa a ser atualizada: ");
        cpf = scanner.next();

        System.out.println("NOME: ");
        map.put("name", scanner.next());

        System.out.println("RUA: ");
        map.put("address.street", scanner.next());
        System.out.println("CIDADE: ");
        map.put("address.city", scanner.next());
        System.out.println("ESTADO: ");
        map.put("address.state", scanner.next());
        System.out.println("CEP: ");
        map.put("address.zip", scanner.next());
        personDAO.updatePerson(cpf, map);
    }

    private static void deletePerson(Scanner scanner) {
        String cpf;
        System.out.println("Informe o CPF da pessoa a ser atualizada: ");
        cpf = scanner.next();
        personDAO.deletePersonById(cpf);
    }

    private static void findPerson() {
        personDAO.findPerson();
    }

    private static void findPersonById(Scanner scanner) {
        String cpf;
        System.out.println("Informe o CPF da pessoa a ser consultada: ");
        cpf = scanner.next();
        personDAO.findPersonById(cpf);
    }
}
