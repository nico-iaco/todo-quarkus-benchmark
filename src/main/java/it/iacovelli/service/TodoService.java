package it.iacovelli.service;

import it.iacovelli.mapper.TodoMapper;
import it.iacovelli.model.Todo;
import it.iacovelli.model.dto.TodoDto;
import lombok.RequiredArgsConstructor;

import javax.enterprise.context.ApplicationScoped;
import javax.persistence.EntityManager;
import javax.transaction.Transactional;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@ApplicationScoped
public class TodoService {

    private final EntityManager em;

    private final TodoMapper todoMapper;

    @Transactional
    public TodoDto save(TodoDto todoDto) {
        Todo todo = todoMapper.fromDtoToEntity(todoDto);
        todo.setId(UUID.randomUUID());
        em.merge(todo);
        return todoMapper.fromEntityToDto(todo);
    }

    @Transactional
    public TodoDto edit(TodoDto dto) {
        Todo todo = em.createQuery("select t from Todo t where t.id = :id", Todo.class)
                .setParameter("id", dto.getId())
                .getSingleResult();
        todoMapper.updateEntityFromDto(dto, todo);
        em.merge(todo);
        return todoMapper.fromEntityToDto(todo);
    }

    @Transactional
    public boolean delete(UUID id) {
        int executed = em.createQuery("delete from Todo t where t.id = :id", Todo.class)
                .setParameter("id", id)
                .executeUpdate();
        return executed == 1;
    }

    public TodoDto find(UUID id) {
        Todo todo = em.createQuery("select t from Todo t where t.id = :id", Todo.class)
                .setParameter("id", id)
                .getSingleResult();
        return todoMapper.fromEntityToDto(todo);
    }

    public List<TodoDto> findAll() {
        return em.createQuery("select t from Todo t", Todo.class)
                .getResultList()
                .stream()
                .map(todoMapper::fromEntityToDto)
                .collect(Collectors.toList());
    }



}
