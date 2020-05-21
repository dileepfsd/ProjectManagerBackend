package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.entity.Project;
import com.pm.service.IProjectService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectService IProjectService;

    @Test
    public void createProject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(mockProject());
        final MvcResult mvcResult = mockMvc.perform(
                post("/project/create")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
    }

    private Project mockProject() {
        final Project project = new Project();
        //project.setProjectId(0);
        project.setProjectTitle("Test");
        project.setPriority(1);
        project.setUserId(1);
        //project.setStartDate(LocalDate.of(2000, 10, 10));
        //project.setEndDate(LocalDate.of(2000, 10, 20));
        return project;
    }
}
