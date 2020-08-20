package br.com.estudo.mongo.app.config;

import com.mongodb.MongoClient;
import com.mongodb.MongoClientURI;

public class ConnectionMongo {

    private static MongoClient mongoClient;

    /*
    * CRIA UMA NOVA CONEXÃO PARA O ENDEREÇO INFORMADO
    * NO CASO É LOCALHOST
    * */
    public static MongoClient getConnection() {
        if (mongoClient != null) {
            return mongoClient;
        }
        mongoClient = new MongoClient(new MongoClientURI("mongodb://localhost:27017"));
        return mongoClient;
    }
}
