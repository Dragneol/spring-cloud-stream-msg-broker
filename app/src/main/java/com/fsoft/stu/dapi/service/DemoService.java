package com.fsoft.stu.dapi.service;

import com.fsoft.stu.dapi.dto.DemoDto;
import com.fsoft.stu.dapi.model.DemoModel;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public interface DemoService {

    DemoDto findById(Long id);

    DemoDto create(DemoModel model);

    DemoDto update(Long id, DemoModel model);

    void delete(Long id);

    String sendMessage(DemoDto message) throws IOException, TimeoutException;

}
