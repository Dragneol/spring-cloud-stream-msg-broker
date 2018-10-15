package com.fsoft.stu.dapi.converter;

import com.fsoft.stu.dapi.dto.DemoDto;
import com.fsoft.stu.dapi.model.DemoModel;
import org.springframework.stereotype.Component;

@Component
public class Demo2DemoDtoConverter {

    public DemoDto convert(final DemoModel demoModel) {
        return new DemoDto(demoModel.getId(), demoModel.getContent());
    }
}
