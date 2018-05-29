package org.aist.aide.finitehorizonservice.service.controllers;

import java.util.List;
import java.util.logging.Logger;
import javax.validation.Valid;
import org.aist.aide.finitehorizonservice.domain.models.AdditionalValue;
import org.aist.aide.finitehorizonservice.domain.models.FiniteType;
import org.aist.aide.finitehorizonservice.domain.services.FiniteTypeCrudService;
import org.aist.aide.formexpert.common.exceptions.NotFoundException;
import org.aist.aide.formexpert.common.exceptions.ValidationFailureException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1/finitetype")
public class FiniteTypeController {
    private static final Logger LOGGER = Logger.getLogger(FiniteTypeController.class.getName());
    private FiniteTypeCrudService finiteTypeCrudService;

    public FiniteTypeController(@Autowired FiniteTypeCrudService finiteTypeCrudService) {
        this.finiteTypeCrudService = finiteTypeCrudService;
    }

    @RequestMapping("/")
    public ResponseEntity<List<FiniteType>> getAll() {
        return new ResponseEntity<>(finiteTypeCrudService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.POST)
    public ResponseEntity<String> create(@Valid @RequestBody FiniteType finiteType) {
        try {
            finiteTypeCrudService.create(finiteType);
        } catch (ValidationFailureException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(finiteType.getTypeId(), HttpStatus.CREATED);
    }

    @RequestMapping(path = "/{typeId}")
    public ResponseEntity<FiniteType> get(@PathVariable String typeId) {
        FiniteType finiteType;
        try {
            finiteType = finiteTypeCrudService.getFiniteType(typeId);
        } catch (NotFoundException e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<>(finiteType, HttpStatus.OK);
    }

    @RequestMapping(path = "/", method = RequestMethod.PUT)
    public ResponseEntity update(@Valid @RequestBody FiniteType finiteType) {
        try {
            finiteTypeCrudService.update(finiteType);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.PUT)
    public ResponseEntity update(@PathVariable String typeId,
                                 @Valid @RequestBody AdditionalValue additionalValue) {
        try {
            finiteTypeCrudService.addValue(typeId, additionalValue.getAdditionalValue());
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        } catch (ValidationFailureException e) {
            return new ResponseEntity(HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity(HttpStatus.OK);
    }

    @RequestMapping(path = "/{typeId}", method = RequestMethod.DELETE)
    public ResponseEntity delete(@PathVariable String typeId) {
        try {
            finiteTypeCrudService.delete(typeId);
        } catch (NotFoundException e) {
            return new ResponseEntity(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity(HttpStatus.OK);
    }
}
