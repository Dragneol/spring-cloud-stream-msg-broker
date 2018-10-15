package com.fsoft.stu.dapi.controller;


import com.fsoft.stu.dapi.request.DemoRequest;
import com.fsoft.stu.dapi.dto.DemoDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(DemoApi.API_BASE)
public interface DemoApi {
    String API_VERSION_1 = "application/json";
    String API_BASE = "/demo";

    /**
     * Get demo by id.
     *
     * @param id id of demo
     * @return   Content of demo (id, content)
     */
    @RequestMapping(path = "/query/{id}", method = RequestMethod.GET,
            consumes = API_VERSION_1, produces = API_VERSION_1)
    ResponseEntity<DemoDto> getDemo(@PathVariable final Long id);

    /**
     * Create demo.
     *
     * @param demoRequest content for demo to create
     * @return Content of demo (id, content)
     */
    @RequestMapping(path = "/create", method = RequestMethod.POST,
            consumes = API_VERSION_1, produces = API_VERSION_1)
    ResponseEntity<DemoDto> createDemo(@RequestBody final DemoRequest demoRequest);

    /**
     * Update demo.
     *
     * @param demoRequest content for demo to update
     * @return Content of demo (id, content)
     */
    @RequestMapping(path = "/update/{id}", method = RequestMethod.PUT,
            consumes = API_VERSION_1, produces = API_VERSION_1)
    ResponseEntity<DemoDto> updateDemo(@PathVariable final Long id, @PathVariable final DemoRequest demoRequest);

    /**
     * Delete demo.
     *
     * @param id id of demo
     */
    @RequestMapping(path = "/delete/{id}", method = RequestMethod.DELETE,
            consumes = API_VERSION_1, produces = API_VERSION_1)
    void deleteDemo(@PathVariable final Long id);
}
