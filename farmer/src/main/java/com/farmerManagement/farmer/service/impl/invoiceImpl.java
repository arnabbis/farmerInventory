package com.farmerManagement.farmer.service.impl;

import com.farmerManagement.farmer.entity.invoiceEntity;
import com.farmerManagement.farmer.repository.invoiceRepository;
import com.farmerManagement.farmer.service.inventoryService;
import com.farmerManagement.farmer.service.invoiceService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class invoiceImpl implements invoiceService {

    private final invoiceRepository invoiceRepo;

    public invoiceImpl(invoiceRepository invoiceRepo) {
        this.invoiceRepo = invoiceRepo;
    }

    @Override
    public List<invoiceEntity> getAllInvoice() {
        return invoiceRepo.findAll();
    }

    @Override
    public Optional<invoiceEntity> getInvoiceById(int id) {
        return invoiceRepo.findById(id);
    }

    @Override
    public invoiceEntity saveInvoice(invoiceEntity invoice) {
        return invoiceRepo.save(invoice);
    }

    @Override
    public invoiceEntity updateInvoice(invoiceEntity invoice) {
        return invoiceRepo.save(invoice);
    }

    @Override
    public boolean deleteInvoice(int id) {
        Optional<invoiceEntity> invoice = invoiceRepo.findById(id);
        if(invoice.isPresent()) {
            invoiceRepo.deleteById(id);
            return true;
        }
        return false;
    }
}
