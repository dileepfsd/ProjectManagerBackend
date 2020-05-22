package com.pm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.pm.entity.Project;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import com.pm.service.IProjectService;
import org.springframework.test.web.servlet.MvcResult;

import java.time.LocalDate;
import java.util.Arrays;

import static org.junit.Assert.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class)
@WebMvcTest(ProjectController.class)
public class ProjectControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private IProjectService projectServiceImpl;

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
        verify(projectServiceImpl, times(1)).createProject(any());
        assertNotNull(mvcResult.getResponse().getContentAsString());
    }

    @Test
    public void findAllProjects() throws Exception {
        when(projectServiceImpl.findAllProjects()).thenReturn(Arrays.asList(mockProject()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(mockProject()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjects")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjects();
        assertNotNull(content);
    }

    @Test
    public void findAllProjectByInput() throws Exception {
        when(projectServiceImpl.findAllProjectByInput(anyString())).thenReturn(Arrays.asList(mockProject()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(mockProject()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjectByInput/input")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjectByInput(anyString());
        assertNotNull(content);
    }

    @Test
    public void findProjectById() throws Exception {
        when(projectServiceImpl.findProjectById(anyLong())).thenReturn(mockProject());
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(mockProject());
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findProjectById/10")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findProjectById(anyLong());
        assertNotNull(content);
    }

    @Test
    public void deleteProject() throws Exception {
        ObjectMapper mapper = new ObjectMapper();
        when(projectServiceImpl.deleteProject(anyLong())).thenReturn(mockProject());
        String requestJson = mapper.writeValueAsString(100);
        final MvcResult mvcResult = mockMvc.perform(
                post("/project/delete")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).deleteProject(anyLong());
        assertNotNull(content);
    }

    @Test
    public void findAllProjectsWithTaskStatus() throws Exception {
        when(projectServiceImpl.findAllProjectsWithTaskStatus()).thenReturn(Arrays.asList(mockProject()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(mockProject()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjectsWithTaskStatus")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjectsWithTaskStatus();
        assertNotNull(content);
    }

    @Test
    public void findAllProjectByInputWithTask() throws Exception {
        when(projectServiceImpl.findAllProjectByInputWithTask(anyString())).thenReturn(Arrays.asList(mockProject()));
        ObjectMapper mapper = new ObjectMapper();
        String requestJson = mapper.writeValueAsString(Arrays.asList(mockProject()));
        final MvcResult mvcResult = mockMvc.perform(
                get("/project/findAllProjectByInputWithTask/test")
                        .content(requestJson)
                        .contentType(MediaType.APPLICATION_JSON_VALUE)
                        .accept(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk()).andDo(print())
                .andReturn();
        String content = mvcResult.getResponse().getContentAsString();
        verify(projectServiceImpl, times(1)).findAllProjectByInputWithTask(anyString());
        assertNotNull(content);
    }

    private Project mockProject() {
        final Project project = new Project();
        project.setProjectId(0);
        project.setProjectTitle("Test");
        project.setPriority(1);
        project.setUserId(1);
        project.setStartDate(LocalDate.of(2000, 10, 10));
        project.setEndDate(LocalDate.of(2000, 10, 20));
        return project;
    }
}
