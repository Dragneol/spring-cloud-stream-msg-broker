package com.fsoft.stu.dapi.api;

import com.fsoft.stu.dapi.controller.DemoApi;
import com.fsoft.stu.dapi.converter.DemoConverter;
import com.fsoft.stu.dapi.dto.DemoDto;
import com.fsoft.stu.dapi.request.DemoRequest;
import com.fsoft.stu.dapi.service.DemoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@RestController
public class DemoController implements DemoApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DemoController.class);

    @Autowired
    private DemoService demoService;

    @Override
    public ResponseEntity<DemoDto> getDemo(@PathVariable final Long id) {
        LOGGER.info("get content response by id={}", id);
        final DemoDto dto = demoService.findById(id);
        return new ResponseEntity<>(dto, HttpStatus.OK);
    }

    @Override
    public ResponseEntity<DemoDto> createDemo(@RequestBody final DemoRequest demoRequest) {
        LOGGER.info("create demo={}", demoRequest);
        DemoDto dto = demoService.create(DemoConverter.toDemo(demoRequest));
        try {
            demoService.sendMessage(dto);
        } catch (IOException e) {
            LOGGER.info(String.valueOf(e.getCause()));
        } catch (TimeoutException e) {
            LOGGER.info(String.valueOf(e.getCause()));
        }
        return new ResponseEntity<>(dto, HttpStatus.CREATED);
    }

    @Override
    public ResponseEntity<DemoDto> updateDemo(@PathVariable final Long id, @RequestBody final DemoRequest demoRequest) {
        LOGGER.info("update demo={}", demoRequest);
        DemoDto result = demoService.update(id, DemoConverter.toDemo(demoRequest));
        if (result == null) {
            return new ResponseEntity<>(result, HttpStatus.BAD_REQUEST);
        }
        return new ResponseEntity<>(result, HttpStatus.OK);
    }

    @Override
    public void deleteDemo(@PathVariable final Long id) {
        LOGGER.info("delete demo by id={}", id);
        demoService.delete(id);
    }

}
