package com.distribuida;
import com.distribuida.db.Book;
import jakarta.enterprise.inject.spi.CDI;
import com.distribuida.services.BookService;
import com.google.gson.Gson;
import io.helidon.webserver.WebServer;
import org.apache.webbeans.config.WebBeansContext;
import org.apache.webbeans.spi.ContainerLifecycle;


import java.util.List;


public class Main {

    private static ContainerLifecycle lifecycle = null;


    public static void main(String[] args) {

        lifecycle = WebBeansContext.currentInstance().getService(ContainerLifecycle.class);
        lifecycle.startApplication(null);
        BookService sr = CDI.current().select(BookService.class).get();

        WebServer server = WebServer.builder()
                .port(8080)
                .routing(builder -> builder
                        .post("/books", (req, res) -> {
                            try {
                                Gson gson = new Gson();
                                String body = req.content().as(String.class);
                                Book object = gson.fromJson(body, Book.class);
                                sr.createBook(object);
                                res.send("Se ha insertado el libro con exito");
                            } catch (Exception e) {
                                res.status(500).send("No se realizo nada: " + e.getMessage());
                            }
                        })
                        .get("/books", (req, res) -> {
                            try {
                                List<Book> books = sr.listBooks();
                                String response = new Gson().toJson(books);
                                res.send(response);
                            } catch (Exception e) {
                                res.status(500).send("No se realizo nada: " + e.getMessage());
                            }
                        })
                        .get("/books/{id}", (req, res) -> {
                            try {
                                Book aux = sr.getBook(Integer.valueOf(req.path().pathParameters().get("id")));
                                res.send(new Gson().toJson(aux));
                            } catch (Exception e) {
                                res.status(500).send("No se realizo nada: " + e.getMessage());
                            }
                        })
                        .delete("/books/{id}", (req, res) -> {
                            try {
                                Integer id = Integer.valueOf(req.path().pathParameters().get("id"));
                                sr.deleteBook(id);
                                res.send("Se ha eliminado el libro con id " + id + " con exito");
                            } catch (Exception e) {
                                res.status(500).send("No se realizo nada: " + e.getMessage());
                            }
                        })
                        .put("/books/{id}", (req, res) -> {
                            try {
                                Gson gson = new Gson();
                                String aux = req.content().as(String.class);
                                Book object = gson.fromJson(aux, Book.class);
                                Integer id = Integer.valueOf(req.path().pathParameters().get("id"));
                                object.setId(id);
                                sr.updateBook(object);
                                res.send("Se ha actualizado el libro con id " + id + " con exito");
                            } catch (Exception e) {
                                res.status(500).send("No se realizo nada: " + e.getMessage());
                            }
                        })

                )
                .build();

        server.start();

    }
}
