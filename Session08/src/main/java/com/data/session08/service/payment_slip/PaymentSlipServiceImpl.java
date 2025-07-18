package com.data.session08.service.payment_slip;

import com.data.session08.model.entity.PaymentSlip;
import com.data.session08.repo.PaymentSlipRepository;
import com.data.session08.service.PaymentSlipService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.resource.NoResourceFoundException;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class PaymentSlipServiceImpl implements PaymentSlipService {

    @Autowired
    private PaymentSlipRepository repository;

    @Override
    public PaymentSlip create(PaymentSlip paymentSlip) {
        paymentSlip.setCreatedAt(LocalDateTime.now());
        return repository.save(paymentSlip);
    }

    @Override
    public PaymentSlip update(Long id, PaymentSlip paymentSlip) throws NoResourceFoundException {
        PaymentSlip existing = repository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.PUT, "PaymentSlip not found with id: " + id));

        existing.setTitle(paymentSlip.getTitle());
        existing.setDescription(paymentSlip.getDescription());
        existing.setMoney(paymentSlip.getMoney());
        return repository.save(existing);
    }

    @Override
    public void delete(Long id) throws NoResourceFoundException {
        PaymentSlip paymentSlip = repository.findById(id)
                .orElseThrow(() -> new NoResourceFoundException(HttpMethod.DELETE, "PaymentSlip not found with id: " + id));
        repository.delete(paymentSlip);
    }

    @Override
    public List<PaymentSlip> findAll() {
        return repository.findAll();
    }
}
