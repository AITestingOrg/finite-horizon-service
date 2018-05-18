package org.aist.aide.finitehorizonservice.domain.services;

import java.util.List;
import java.util.logging.Logger;
import org.aist.aide.finitehorizonservice.domain.exceptions.NotFoundException;
import org.aist.aide.finitehorizonservice.domain.exceptions.ValidationFailureException;
import org.aist.aide.finitehorizonservice.domain.models.FiniteType;
import org.aist.aide.finitehorizonservice.service.repositories.FiniteTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FiniteTypeCrudService {
    private static final Logger LOGGER = Logger.getLogger(FiniteTypeCrudService.class.getName());
    private FiniteTypeRepository finiteTypeRepository;

    public FiniteTypeCrudService(@Autowired FiniteTypeRepository finiteTypeRepository) {
        this.finiteTypeRepository = finiteTypeRepository;
    }

    public List<FiniteType> getAll() {
        return finiteTypeRepository.findAll();
    }

    public FiniteType getFiniteType(String typeId) throws NotFoundException {
        var result = finiteTypeRepository.findByTypeId(typeId.toLowerCase());
        if (result.isPresent()) {
            return result.get();
        }
        throw new NotFoundException(String.format("Failed to find entry for type %s.",  typeId));
    }

    public void create(FiniteType finiteType) throws ValidationFailureException {
        final var lowerFiniteType = new FiniteType(finiteType.getTypeId().toLowerCase(), finiteType.getValues());
        var typeId = lowerFiniteType.getTypeId();
        var existingType = finiteTypeRepository.findByTypeId(typeId);
        if (existingType.isPresent()) {
            LOGGER.warning(String.format("Entry for type %s already exists.", typeId));
            throw new ValidationFailureException(
                    String.format("Entry for type %s already exists, cannot create.", typeId));
        }
        finiteTypeRepository.save(lowerFiniteType);
    }

    public void delete(String typeId) throws NotFoundException {
        var existingType = finiteTypeRepository.findByTypeId(typeId.toLowerCase());
        if (!existingType.isPresent()) {
            throw new NotFoundException(String.format("Failed to find entry for type %s.",  typeId));
        }
        finiteTypeRepository.delete(existingType.get());
    }

    public void addValue(String typeId, String newValue) throws NotFoundException, ValidationFailureException {
        var existingType = finiteTypeRepository.findByTypeId(typeId.toLowerCase());
        if (!existingType.isPresent()) {
            throw new NotFoundException(String.format("Failed to find entry for type %s.",  typeId));
        }
        var finiteType = existingType.get();
        if (finiteType.getValues().contains(newValue)) {
            throw new ValidationFailureException(
                    String.format("The case %s is already included for type %s.", newValue, typeId));
        }
        finiteType.getValues().add(newValue);
        finiteTypeRepository.save(finiteType);
    }

    public void update(FiniteType finiteType) throws NotFoundException {
        final var lowerFiniteType = new FiniteType(finiteType.getTypeId().toLowerCase(), finiteType.getValues());
        final var typeId = lowerFiniteType.getTypeId();
        final var existingType = finiteTypeRepository.findByTypeId(typeId);
        if (!existingType.isPresent()) {
            throw new NotFoundException(String.format("Failed to find entry for type %s.",  typeId));
        }
        finiteTypeRepository.save(lowerFiniteType);
    }
}
