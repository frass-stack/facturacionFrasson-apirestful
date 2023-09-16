package com.springboot.facturacionfrasson.repository;

import com.springboot.facturacionfrasson.model.Invoice;
import com.springboot.facturacionfrasson.model.InvoiceDTO;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface InvoiceRepository extends JpaRepository<Invoice, Integer> {
    @Query("SELECT new com.springboot.facturacionfrasson.model.InvoiceDTO(" +
            "i.id invoice_id, " +
            "i.created_at, " +
            "i.total" +
            ") FROM Invoice i INNER JOIN i.client c WHERE c.id = :id")
    List<InvoiceDTO> getInvoiceByClientId(@Param("id") int id);
}
