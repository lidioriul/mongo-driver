package br.com.estudo.mongo.app.adapter;

import br.com.estudo.mongo.app.entities.Person;
import br.com.estudo.mongo.app.utils.JsonUtil;
import com.mongodb.client.MongoCursor;
import org.bson.Document;

public class PersonAdapter {

    public static Document toDocument(Person p) {
        return new Document("_id", p.getId())
                .append("name", p.getName())
                .append("address", new Document("street", p.getAddress().getStreet())
                        .append("city", p.getAddress().getCity())
                        .append("state", p.getAddress().getState())
                        .append("zip", p.getAddress().getZip()))
                .append("books", p.getBooks());
    }

    public static void showPerson(MongoCursor<Document> cursor) {
        try {
            while (cursor.hasNext()) {
                Person p = JsonUtil.readValue(cursor.next().toJson(), Person.class);
                System.out.println(p.toString());
            }
        } finally {
            cursor.close();
        }
    }
}
