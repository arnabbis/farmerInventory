package com.farmerManagement.farmer.service;

import com.farmerManagement.farmer.entity.invoiceEntity;

import java.util.List;
import java.util.Optional;


public interface invoiceService {
    List<invoiceEntity> getAllInvoice();
    Optional<invoiceEntity> getInvoiceById(int id);
    invoiceEntity saveInvoice(invoiceEntity invoice);
    invoiceEntity updateInvoice(invoiceEntity invoice);
    boolean deleteInvoice(int id);
}
