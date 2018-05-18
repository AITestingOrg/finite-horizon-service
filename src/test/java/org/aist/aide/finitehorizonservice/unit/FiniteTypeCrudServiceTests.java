package org.aist.aide.finitehorizonservice.unit;

import static org.aist.aide.finitehorizonservice.utils.TestsConstants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import java.util.Optional;
import org.aist.aide.finitehorizonservice.domain.exceptions.NotFoundException;
import org.aist.aide.finitehorizonservice.domain.exceptions.ValidationFailureException;
import org.aist.aide.finitehorizonservice.domain.models.FiniteType;
import org.aist.aide.finitehorizonservice.domain.services.FiniteTypeCrudService;
import org.aist.aide.finitehorizonservice.service.repositories.FiniteTypeRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FiniteTypeCrudServiceTests {
    @Mock
    private FiniteTypeRepository finiteTypeRepository;

    @InjectMocks
    private FiniteTypeCrudService finiteTypeCrudService;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetAllIsCalled_FindAllIsCalled() {
        // act
        finiteTypeCrudService.getAll();

        // assert
        verify(finiteTypeRepository,times(1)).findAll();
    }

    @Test(expected = NotFoundException.class)
    public void givenNoFiniteTypeExists_WhenGetCalled_FindByTypeCalledAndExceptionIsThrown() throws NotFoundException {
        // act
        finiteTypeCrudService.getFiniteType(type);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(type);
    }

    @Test(expected = NotFoundException.class)
    public void givenNoFiniteTypeExists_WhenDeleteCalled_FindByTypeCalledAndExceptionIsThrown()
            throws NotFoundException {
        // act
        finiteTypeCrudService.delete(type);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(type);
    }

    @Test
    public void givenNoFiniteTypeExists_WhenCreateCalled_FindByTypeAndSaveIsCalled() throws ValidationFailureException {
        // act
        finiteTypeCrudService.create(finiteType);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(finiteType.getTypeId());
        verify(finiteTypeRepository,times(1)).save(Mockito.any(FiniteType.class));
    }

    @Test(expected = ValidationFailureException.class)
    public void givenFiniteTypeAlreadyExists_WhenCreateCalled_ExceptionIsThrown() throws ValidationFailureException {
        // arrange
        when(finiteTypeRepository.findByTypeId(finiteType.getTypeId())).thenReturn(Optional.of(finiteType));

        // act
        finiteTypeCrudService.create(finiteType);

        // assert
    }

    @Test(expected = NotFoundException.class)
    public void givenNoFiniteTypeExists_WhenUpdateCalled_FindByTypeIsCalledAndExceptionIsThrown()
            throws NotFoundException {
        // act
        finiteTypeCrudService.update(finiteType);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(finiteType.getTypeId());
    }

    @Test
    public void givenFiniteTypeExists_WhenUpdateCalled_FindByTypeAndSaveAreCalled() throws NotFoundException {
        // arrange
        when(finiteTypeRepository.findByTypeId(finiteType.getTypeId())).thenReturn(Optional.of(finiteType));

        // act
        finiteTypeCrudService.update(finiteType);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(finiteType.getTypeId());
        verify(finiteTypeRepository,times(1)).save(Mockito.any(FiniteType.class));
    }

    @Test(expected = NotFoundException.class)
    public void givenNoFiniteTypeExists_WhenAddValueCalled_FindByTypeIsCalledAndExceptionIsThrown()
            throws NotFoundException, ValidationFailureException {
        // act
        finiteTypeCrudService.addValue(type, newValue);

        // assert
        verify(finiteTypeRepository,times(1)).findByTypeId(type);
    }

    @Test(expected = ValidationFailureException.class)
    public void givenFiniteTypeExistsAndValueAlreadyInclued_WhenAddValueCalled_ExceptionIsThrown()
            throws NotFoundException, ValidationFailureException {
        // arrange
        when(finiteTypeRepository.findByTypeId(finiteType.getTypeId())).thenReturn(Optional.of(finiteType));

        // act
        finiteTypeCrudService.addValue(type, existingValue);
    }
    
    @Test
    public void givenFiniteTypeExistsAndValueNotIncluded_WhenAddValueCalled_SaveIsCalled()
            throws NotFoundException, ValidationFailureException {
        // arrange
        when(finiteTypeRepository.findByTypeId(finiteType.getTypeId())).thenReturn(Optional.of(finiteType));

        // act
        finiteTypeCrudService.addValue(type, newValue);

        // assert
        verify(finiteTypeRepository,times(1)).save(Mockito.any(FiniteType.class));
    }
}
