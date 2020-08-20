package br.com.estudo.mongo.app.dao;

import br.com.estudo.mongo.app.adapter.PersonAdapter;
import br.com.estudo.mongo.app.config.ConnectionMongo;
import br.com.estudo.mongo.app.entities.Person;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoCursor;
import com.mongodb.client.MongoDatabase;
import com.mongodb.client.model.Filters;
import org.bson.Document;

import java.util.Map;

public class PersonDAO {

    private final MongoDatabase db;
    private final MongoCollection<Document> personCollection;

    public PersonDAO() {
        db = ConnectionMongo.getConnection().getDatabase("estudo-mongodb-java");
        personCollection = db.getCollection("person");
    }

    public void createPerson(Person p) {
        personCollection.insertOne(PersonAdapter.toDocument(p));
    }

    public void updatePerson(String id, Map<String, String> mapPerson) {
        for (Map.Entry<String, String> map : mapPerson.entrySet()) {
            Document doc = new Document("$set", new Document(map.getKey(), map.getValue()));
            personCollection.updateOne(Filters.eq("_id", id), doc);
        }
    }

    public void deletePersonById(String id) {
        personCollection.findOneAndDelete(Filters.eq("_id", id));
    }

    public void findPerson() {
        MongoCursor<Document> cursor = personCollection.find().iterator();
        PersonAdapter.showPerson(cursor);
        cursor.close();
    }

    public void findPersonById(String id) {
        MongoCursor<Document> cursor = personCollection.find(Filters.eq("_id", id)).iterator();
        PersonAdapter.showPerson(cursor);
        cursor.close();
    }
}
