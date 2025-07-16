package com.data.session07.service.work;

import com.data.session07.model.entity.Worker;
import com.data.session07.repository.WorkerRepository;
import com.data.session07.service.WorkerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class WorkerServiceImpl implements WorkerService {
    @Autowired
    private WorkerRepository workerRepository;
    @Override
    public List<Worker> getAllWorkers() {
        return workerRepository.findAll();
    }

    @Override
    public Worker getWorkerById(Long id) {
        return workerRepository.getReferenceById(id);
    }

    @Override
    public Worker addWorker(Worker worker) {
        return workerRepository.save(worker);
    }

    @Override
    public Worker updateWorker(Long id, Worker worker) {
        Optional<Worker> optionalWorker = workerRepository.findById(id);
        if (optionalWorker.isPresent()) {
            Worker w =  optionalWorker.get();
            w.setAddress(worker.getAddress());
            w.setSalary(worker.getSalary());
            w.setPhone(worker.getPhone());
            w.setFullName(worker.getFullName());
            return workerRepository.save(w);
        }
        return null;
    }

    @Override
    public void deleteWorker(Long id) {
        workerRepository.deleteById(id);
    }
}
