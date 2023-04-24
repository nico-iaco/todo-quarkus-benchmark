package it.iacovelli.mapper;


import it.iacovelli.model.Todo;
import it.iacovelli.model.dto.TodoDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;

@Mapper(componentModel = "cdi")
public interface TodoMapper {

    @Mapping(target = "id", ignore = true)
    Todo fromDtoToEntity(TodoDto dto);

    TodoDto fromEntityToDto(Todo entity);

    @Mapping(target = "id", ignore = true)
    Todo updateEntityFromDto(TodoDto dto, @MappingTarget Todo entity);

}
