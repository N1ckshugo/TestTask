package com.example.testtask.rest;

import com.example.testtask.model.Order;
import com.example.testtask.repository.OrderRepository;
import com.example.testtask.service.UserDetailsServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Optional;

import static org.hamcrest.Matchers.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(OrderRestController.class)
class OrderRestControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper mapper;

    @MockBean
    private OrderRepository orderRepository;

    @MockBean
    private UserDetailsServiceImpl userDetailsService;

    @Test
    void getOrderById() throws Exception {
        when(orderRepository.findById(anyLong())).thenReturn(Optional.of(
                new Order(1L, "test1", 89197604471L, "test1@mail.ru", "test1.home")
        ));

        mockMvc.perform(get("/orders/get?id=1"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("test1")))
                .andExpect(jsonPath("$.phone", equalTo(89197604471L)))
                .andExpect(jsonPath("$.email", equalTo("test1@mail.ru")))
                .andExpect(jsonPath("$.address", equalTo("test1.home")));
    }

    @Test
    void getAllOrders() throws Exception {
        when(orderRepository.findAll()).thenReturn(Arrays.asList(
                new Order(1L, "test1", 89197604471L, "test1@mail.ru", "test1.home"),
                new Order(2L, "test2", 89197604472L, "test2@mail.ru", "test2.home")
        ));

        mockMvc.perform(get("/orders/getall"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[*].id", containsInAnyOrder(1, 2)))
                .andExpect(jsonPath("$[*].name", containsInAnyOrder("test1", "test2")))
                .andExpect(jsonPath("$[*].phone", containsInAnyOrder(89197604471L, 89197604472L)))
                .andExpect(jsonPath("$[*].email", containsInAnyOrder("test1@mail.ru", "test2@mail.ru")))
                .andExpect(jsonPath("$[*].address", containsInAnyOrder("test1.home", "test2.home")));
    }

    @Test
    void createOrder() throws Exception {
        Order order = new Order(1L, "test1", 89197604471L, "test1@mail.ru", "test1.home");
        when(orderRepository.save(ArgumentMatchers.any())).thenReturn(order);

        mockMvc.perform(post("/orders/create")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsBytes(order))
                        .accept(MediaType.APPLICATION_JSON)
                )
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", equalTo(1)))
                .andExpect(jsonPath("$.name", equalTo("test1")))
                .andExpect(jsonPath("$.phone", equalTo(89197604471L)))
                .andExpect(jsonPath("$.email", equalTo("test1@mail.ru")))
                .andExpect(jsonPath("$.address", equalTo("test1.home")));
    }

}