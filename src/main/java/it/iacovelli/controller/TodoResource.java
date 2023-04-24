package it.iacovelli.controller;

import it.iacovelli.model.dto.TodoDto;
import it.iacovelli.service.TodoService;
import lombok.RequiredArgsConstructor;

import javax.ws.rs.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import java.util.UUID;

@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
@RequiredArgsConstructor
@Path("/todo")
public class TodoResource {

    private final TodoService todoService;

    @POST
    @Path("/")
    public Response createTodo(TodoDto todoDto) {
        TodoDto saved = todoService.save(todoDto);
        return Response.status(Response.Status.CREATED).entity(saved).build();
    }

    @PATCH
    @Path("/")
    public Response updateTodo(TodoDto todoDto) {
        TodoDto edited = todoService.edit(todoDto);
        return Response.ok(edited).build();
    }

    @DELETE
    @Path("/{id}")
    public Response deleteTodo(@PathParam("id") UUID id) {
        todoService.delete(id);
        return Response.noContent().build();
    }

    @GET
    @Path("/")
    public Response getTodos() {
        return Response.ok(todoService.findAll()).build();
    }


}