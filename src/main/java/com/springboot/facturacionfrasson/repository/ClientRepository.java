package com.springboot.facturacionfrasson.repository;

import com.springboot.facturacionfrasson.model.Client;
import jakarta.persistence.Tuple;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface ClientRepository extends JpaRepository<Client, Integer> {

    List<Client> findByNameLike(String name);

    @Query("SELECT a FROM Client a WHERE name = :nombre ORDER BY lastname ASC")
    List<Client> getByNameOrderedByLastNameJPQL(@Param("nombre") String name);

    @Query(value = "SELECT * FROM client a WHERE name = ?1 ORDER BY lastname ASC", nativeQuery = true)
    List<Client> getByNameOrderedByLastNameNativeQuery(String name);

    @Query("SELECT c, i FROM Client c INNER JOIN c.invoice i WHERE c.id = :id")
    List<Tuple> getInvoicesWithClientById(@Param("id") int id);

    Optional<Client> findByDocnumber(String docnumber);
}
