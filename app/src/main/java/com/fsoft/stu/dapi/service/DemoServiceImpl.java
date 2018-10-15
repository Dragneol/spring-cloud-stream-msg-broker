package com.fsoft.stu.dapi.service;

import com.fsoft.stu.dapi.DemoApplication;
import com.fsoft.stu.dapi.dto.DemoDto;
import com.fsoft.stu.dapi.converter.Demo2DemoDtoConverter;
import com.fsoft.stu.dapi.model.DemoModel;
import com.fsoft.stu.dapi.msgbrokerRabbitMQ.Producer;
import com.fsoft.stu.dapi.msgprovider.Provider;
import com.fsoft.stu.dapi.repository.DemoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@Service
public class DemoServiceImpl implements DemoService {

    @Autowired
    private final DemoRepository demoRepository;

    @Autowired
    private final Demo2DemoDtoConverter converter;

    public DemoServiceImpl(DemoRepository demoRepository, Demo2DemoDtoConverter converter) {
        this.demoRepository = demoRepository;
        this.converter = converter;
    }

    public String sendMessage(DemoDto message) throws IOException, TimeoutException {
        Producer producer = new Producer(DemoApplication.queueName);
        producer.sendMessage(message);
        return message.toString();
    }

    @Override
    public DemoDto findById(Long id) {
        final DemoModel demo = demoRepository.findById(id).get();
        if (demo == null) {
            return null;
        }
        return new DemoDto(demo.getId(), demo.getContent());
    }

    @Override
    public DemoDto create(DemoModel model) {
        return converter.convert(demoRepository.save(model));
    }

    @Override
    public DemoDto update(Long id, DemoModel model) {
        final DemoModel demo = demoRepository.findById(id).get();
        if (demo == null) {
            return null;
        }
        demo.setContent(model.getContent());
        return converter.convert(demoRepository.save(demo));
    }

    @Override
    public void delete(Long id) {
        if (demoRepository.existsById(id)) {
            demoRepository.delete(demoRepository.getOne(id));
        }
    }

}
