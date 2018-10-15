package com.fsoft.stu.dapi.converter;

import com.fsoft.stu.dapi.request.DemoRequest;
import com.fsoft.stu.dapi.model.DemoModel;

public final class DemoConverter {

    private DemoConverter() {}

    public static DemoModel toDemo(final DemoRequest request) {
        if (request == null) {
            return null;
        }
        DemoModel demoModel = new DemoModel();
        demoModel.setContent(request.getContent());
        return demoModel;
    }
}
