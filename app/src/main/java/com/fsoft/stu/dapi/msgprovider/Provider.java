package com.fsoft.stu.dapi.msgprovider;

import com.fsoft.stu.dapi.dto.DemoDto;

import java.io.IOException;

public interface Provider {

    void sendMessage(DemoDto object) throws IOException;
}
