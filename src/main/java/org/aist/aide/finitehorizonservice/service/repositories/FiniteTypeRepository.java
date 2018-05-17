package org.aist.aide.finitehorizonservice.service.repositories;

import java.util.Optional;
import org.aist.aide.finitehorizonservice.domain.models.FiniteType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface FiniteTypeRepository extends MongoRepository<FiniteType, String> {
    Optional<FiniteType> findByTypeId(String typeId);
}
