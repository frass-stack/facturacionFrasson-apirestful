package com.springboot.facturacionfrasson.service;

import com.springboot.facturacionfrasson.model.InvoiceDetail;
import com.springboot.facturacionfrasson.model.InvoiceDetailDTO;
import com.springboot.facturacionfrasson.repository.InvoiceDetailRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InvoiceDetailService {
    @Autowired
    private InvoiceDetailRepository invoiceDetailRepository;

    public InvoiceDetail saveInvoiceDetail(InvoiceDetail invoiceDetail){
        return this.invoiceDetailRepository.save(invoiceDetail);
    }

    public List<InvoiceDetailDTO> getInvoiceDetailsByInvoiceId(int invoice_id)throws Exception{
        return this.invoiceDetailRepository.getInvoiceDetailsByInvoiceId(invoice_id);
    }
}
