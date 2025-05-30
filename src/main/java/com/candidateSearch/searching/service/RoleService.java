package com.candidateSearch.searching.service;

import com.candidateSearch.searching.dto.request.RoleRequestDto;
import com.candidateSearch.searching.dto.response.RoleResponseDto;
import com.candidateSearch.searching.entity.OriginEntity;
import com.candidateSearch.searching.entity.RoleEntity;
import com.candidateSearch.searching.entity.JobProfileEntity;
import com.candidateSearch.searching.exception.type.CannotBeCreateException;
import com.candidateSearch.searching.exception.type.EntityNoExistException;
import com.candidateSearch.searching.exception.type.FieldAlreadyExistException;
import com.candidateSearch.searching.mapper.IMapperRole;
import com.candidateSearch.searching.repository.IRoleRepository;
import com.candidateSearch.searching.repository.IJobProfileRepository;
import com.candidateSearch.searching.repository.IOriginRepository;
import com.candidateSearch.searching.entity.utility.Status;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class RoleService {
    private final IRoleRepository roleRepository;
    private final IJobProfileRepository jobProfileRepository;
    private final IOriginRepository originRepository;
    private final IMapperRole mapperRole;

    public RoleResponseDto getRole(Long id){
        return roleRepository.findById(id)
                .map(mapperRole::toDto)
                .orElseThrow(EntityNoExistException::new);
    }

    public List<RoleResponseDto> getAllRoles(){
        return roleRepository.findAll().stream()
                .filter(roles -> roles.getStatus() == Status.ACTIVE)
                .map(mapperRole::toDto)
                .collect(Collectors.toList());
    }

    public RoleResponseDto saveRole(RoleRequestDto roleRequestDto) {

        if(roleRequestDto.getStatus().equals(Status.INACTIVE) || roleRequestDto.getStatus().equals(Status.BLOCKED)){
            throw new CannotBeCreateException();
        }

        if (roleRepository.existsByNameRoleAndStatusNot(roleRequestDto.getNameRole(), Status.INACTIVE)) {
            throw new FieldAlreadyExistException(roleRequestDto.getNameRole());
        }

        JobProfileEntity jobProfileEntity = jobProfileRepository.findById(roleRequestDto.getJobProfile())
                .orElseThrow(EntityNoExistException::new);

        OriginEntity originEntity = originRepository.findById(roleRequestDto.getOrigin())
                .orElseThrow(EntityNoExistException::new);

        RoleEntity roleEntityNew = new RoleEntity();
        roleEntityNew.setNameRole(roleRequestDto.getNameRole());
        roleEntityNew.setDescription(roleRequestDto.getDescription());
        roleEntityNew.setContract(roleRequestDto.getContract());
        roleEntityNew.setSalary(roleRequestDto.getSalary());
        roleEntityNew.setLevel(roleRequestDto.getLevel());
        roleEntityNew.setSeniority(roleRequestDto.getSeniority());
        roleEntityNew.setSkills(roleRequestDto.getSkills());
        roleEntityNew.setExperience(roleRequestDto.getExperience());
        roleEntityNew.setAssignmentTime(roleRequestDto.getAssignmentTime());
        roleEntityNew.setStatus(roleRequestDto.getStatus());
        roleEntityNew.setJobProfile(jobProfileEntity);
        roleEntityNew.setOrigin(originEntity);

        RoleEntity vacancyEntitySave = roleRepository.save(roleEntityNew);

        jobProfileEntity.getVacancies().add(vacancyEntitySave);
        jobProfileRepository.save(jobProfileEntity);

        originEntity.getRoles().add(vacancyEntitySave);
        originRepository.save(originEntity);

        return mapperRole.toDto(vacancyEntitySave);
    }

    public Optional<RoleResponseDto> updateRole(Long id, RoleRequestDto roleRequestDto) {

        if (roleRequestDto.getStatus() == Status.INACTIVE ||
                roleRequestDto.getStatus() == Status.BLOCKED) {
            throw new CannotBeCreateException();
        }

        RoleEntity existingEntity  = roleRepository.findById(id)
                .orElseThrow(EntityNoExistException::new);

        JobProfileEntity jobProfileEntity = jobProfileRepository.findById(roleRequestDto.getJobProfile())
                .orElseThrow(EntityNoExistException::new);

        OriginEntity originEntity = originRepository.findById(roleRequestDto.getOrigin())
                .orElseThrow(EntityNoExistException::new);

        existingEntity.setNameRole(roleRequestDto.getNameRole());
        existingEntity.setDescription(roleRequestDto.getDescription());
        existingEntity.setContract(roleRequestDto.getContract());
        existingEntity.setSalary(roleRequestDto.getSalary());
        existingEntity.setLevel(roleRequestDto.getLevel());
        existingEntity.setSeniority(roleRequestDto.getSeniority());
        existingEntity.setSkills(roleRequestDto.getSkills());
        existingEntity.setExperience(roleRequestDto.getExperience());
        existingEntity.setAssignmentTime(roleRequestDto.getAssignmentTime());
        existingEntity.setStatus(Status.ACTIVE);
        existingEntity.setJobProfile(jobProfileEntity);
        existingEntity.setOrigin(originEntity);

        RoleEntity vacancyEntitySave = roleRepository.save(existingEntity);

        jobProfileEntity.getVacancies().add(vacancyEntitySave);
        jobProfileRepository.save(jobProfileEntity);

        originEntity.getRoles().add(vacancyEntitySave);
        originRepository.save(originEntity);

        return Optional.of(mapperRole.toDto(vacancyEntitySave));
    }

    public void deleteRole(Long id){
        RoleEntity existing = roleRepository.findById(id)
                .orElseThrow(EntityNoExistException::new);

        existing.setStatus(Status.INACTIVE);
        roleRepository.save(existing);
    }
}
