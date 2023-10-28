package ch.lianto.aiwiki.engine.service.data.mapper;

import ch.lianto.aiwiki.engine.entity.Project;
import ch.lianto.aiwiki.engine.service.data.ProjectDto;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public interface ProjectMapper {
    ProjectMapper INSTANCE = Mappers.getMapper(ProjectMapper.class);

    Project fromDto(ProjectDto dto);
}
