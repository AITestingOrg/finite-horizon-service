package org.aist.aide.finitehorizonservice.integration;

import static org.aist.aide.finitehorizonservice.utils.TestsConstants.*;

import org.aist.aide.finitehorizonservice.service.controllers.FiniteTypeController;
import org.junit.After;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FiniteTypeServiceIntegrationTests {

    @Autowired
    private FiniteTypeController finiteTypeController;

    @After
    public void tearDown() {
        var finiteTypes = finiteTypeController.getAll();
        for (var finiteType: finiteTypes.getBody()) {
            finiteTypeController.delete(finiteType.getTypeId());
        }
    }

    @Test
    public void givenDbEmpty_WhenCreateIsCalled_EntryIsCreated() {
        //act
        finiteTypeController.create(finiteType);

        //assert
        var resultsInDB = finiteTypeController.getAll().getBody();
        Assert.assertFalse(resultsInDB.isEmpty());
    }

    @Test
    public void givenItemNotInDb_WhenGetIsCalled_RespondNotFound() {
        //act
        var result = finiteTypeController.get(type);

        //assert
        Assert.assertEquals(result.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenItemInDb_WhenGetIsCalled_RespondOkAndItemRetrieved() {
        //arrange
        finiteTypeController.create(finiteType);

        //act
        var result = finiteTypeController.get(finiteType.getTypeId());

        //assert
        Assert.assertEquals(result.getStatusCode(), HttpStatus.OK);
        Assert.assertEquals(result.getBody().getValues(), finiteType.getValues());
    }

    @Test
    public void givenItemInDb_WhenDeleteIsCalled_ItemIsRemoved() {
        //arrange
        finiteTypeController.create(finiteType);

        //act
        finiteTypeController.delete(finiteType.getTypeId());

        //assert
        var resultNotInDB = finiteTypeController.get(finiteType.getTypeId());
        Assert.assertEquals(resultNotInDB.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenItemNotInDb_WhenDeleteIsCalled_RespondNotFound() {
        //arrange

        //act
        var response = finiteTypeController.delete(newValue);

        //assert
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }

    @Test
    public void givenItemNotInDb_WhenUpdateIsCalled_RespondNotFound() {
        //arrange
        //act
        var response = finiteTypeController.update(finiteType);

        //assert
        Assert.assertEquals(response.getStatusCode(), HttpStatus.NOT_FOUND);
    }
}
