import com.mongodb.MongoClient;
import com.mongodb.client.MongoClients;
import com.mongodb.client.MongoCollection;
import com.mongodb.client.MongoDatabase;
import org.bson.Document;
import org.bson.types.ObjectId;

import javax.crypto.spec.PSource;
import java.time.LocalDateTime;
import java.util.Map;
import java.util.function.Consumer;

public class Main {
    public static void main(String[] args) {
        try (var mongoClient = MongoClients.create()) {

            // получение списка баз данных

//            mongoClient.listDatabases()
//                    .forEach((Consumer< Document >) System.out::println);
//            mongoClient.listDatabaseNames()
//                    .forEach((Consumer<String>) System.out::println);

            var database = mongoClient.getDatabase("change some code");  // получение базы данных с названием



            var todoCollection = database.getCollection("todo"); // получение коллекции с названием

            todoCollection.find()
                    .forEach((Consumer<Document>) System.out::println);

            todoCollection.updateOne(new Document("_id", new ObjectId("641753717580bf4f17d5e3df")), // обновление
                    new Document(Map.of(
                            "$set", new Document("done", true),
                            "$currentDate", new Document("dateOne", true),
                            "$unset", new Document("dateCreated", true)

                    ))
            );

            todoCollection.deleteOne(new Document("_id", new ObjectId("641753717580bf4f17d5e3df"))); // удаление

             // добавление в базу

//           var todoDocument = new Document(Map.of("_id", new ObjectId(),
//                   "task", "Drink some coffee",
//                   "dateCreated" , LocalDateTime.now(),
//                   "done", false
//           ));
//
//           todoCollection.insertOne(todoDocument);


        }
    }
}
