package org.aist.aide.finitehorizonservice.unit;

import static org.aist.aide.finitehorizonservice.utils.TestsConstants.*;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;

import org.aist.aide.finitehorizonservice.domain.exceptions.NotFoundException;
import org.aist.aide.finitehorizonservice.domain.exceptions.ValidationFailureException;
import org.aist.aide.finitehorizonservice.domain.services.FiniteTypeCrudService;
import org.aist.aide.finitehorizonservice.service.controllers.FiniteTypeController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class FiniteTypeControllerTests {
    @Mock
    private FiniteTypeCrudService finiteTypeCrudService;

    @InjectMocks
    private FiniteTypeController finiteTypeController;

    @Before
    public void initMocks() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void whenGetAllCalled_ServiceGetAllCalled() {
        // act
        finiteTypeController.getAll();

        // assert
        verify(finiteTypeCrudService, times(1)).getAll();
    }

    @Test
    public void whenGetIsCalledWithATypeID_ServiceGetIsCalled() throws NotFoundException {
        // act
        finiteTypeController.get(type);

        // assert
        verify(finiteTypeCrudService, times(1)).getFiniteType(type);
    }

    @Test
    public void whenCreateCalled_ServiceCreateCalled() throws ValidationFailureException {
        // act
        finiteTypeController.create(finiteType);

        // assert
        verify(finiteTypeCrudService, times(1)).create(finiteType);
    }

    @Test
    public void whenUpdateCalled_ServiceUpdateCalled() throws NotFoundException {
        // act
        finiteTypeController.update(finiteType);

        // assert
        verify(finiteTypeCrudService, times(1)).update(finiteType);
    }

    @Test
    public void whenUpdateCalledWithTypeAndValue_ServiceAddValueCalled() throws NotFoundException,
                                                                                ValidationFailureException {
        // act
        finiteTypeController.update(type, additionalValue);

        // assert
        verify(finiteTypeCrudService, times(1))
                .addValue(type, additionalValue.getAdditionalValue());
    }

    @Test
    public void whenDeleteCalled_ServiceDeleteCalled() throws NotFoundException {
        // act
        finiteTypeController.delete(type);

        // assert
        verify(finiteTypeCrudService, times(1)).delete(type);
    }
}
