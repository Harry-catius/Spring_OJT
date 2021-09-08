package catius.ojt.device.controller;

import catius.ojt.device.DeviceObjectMother;
import catius.ojt.device.DiscardDeviceObjectMother;
import catius.ojt.device.service.DeviceServiceImpl;
import catius.ojt.device.service.dto.DeviceDto;
import catius.ojt.device.service.dto.DeviceFactory;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.nio.charset.StandardCharsets;

import static org.hamcrest.Matchers.containsString;
import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

//특정 컨트롤러만 테스트
@WebMvcTest(DeviceController.class)
@RunWith(MockitoJUnitRunner.class)
class DeviceControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DeviceServiceImpl deviceService;

    protected MediaType contentType =
            new MediaType(
                    MediaType.APPLICATION_JSON.getType(),
                    MediaType.APPLICATION_JSON.getSubtype(),
                    StandardCharsets.UTF_8);

    @Test
    void ShouldRegisterDevice() throws Exception {
        given(deviceService.register(any()))
                .willReturn(DeviceObjectMother.registerResponseDto());

        String json = new ObjectMapper().writeValueAsString(DeviceObjectMother.registerRequestDto());

        mockMvc.perform(MockMvcRequestBuilders.post("/devices/register")
                        .content(json)
                        .contentType(contentType)
                )
                .andExpect(MockMvcResultMatchers.status().isCreated())
                .andExpect(content().string(containsString("serialNumber")))
                .andExpect(content().string(containsString("macAddress")))
                .andExpect(content().string(containsString("qrCode")))
                .andDo(MockMvcResultHandlers.print());
    }

    @Test
    void shouldFindAllDevice() throws Exception {

        when(deviceService.findDevices(null,null,null))
                .thenReturn(DeviceObjectMother.defaultDeviceListDto());

        mockMvc.perform(get("/devices").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$.[0].serialNumber").value("serialNumber1"))
                .andExpect(jsonPath("$.[0].macAddress").value("macAddress1"))
                .andExpect(jsonPath("$.[0].qrCode").value("qrCode1"))
                .andExpect(jsonPath("$.[1].serialNumber").value("serialNumber2"))
                .andExpect(jsonPath("$.[1].macAddress").value("macAddress2"))
                .andExpect(jsonPath("$.[1].qrCode").value("qrCode2"))
                .andDo(print());
    }

    @Test
    void shouldFindOne() throws Exception {
        when(deviceService.findOne(anyLong()))
                .thenReturn(DeviceFactory.getDeviceDto(DeviceObjectMother.defaultDevice()));

        mockMvc.perform(get("/devices/1").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.serialNumber").value("serialNumber"))
                .andExpect(jsonPath("$.macAddress").value("macAddress"))
                .andExpect(jsonPath("$.qrCode").value("qrCode"))
                .andExpect(jsonPath("$.status").value("ACTIVE"))
                .andDo(print());
    }

    @Test
    void shouldFindAllDiscardedDevice() throws Exception {
        when(deviceService.findAllDiscardedDevice())
                .thenReturn(DiscardDeviceObjectMother.defaultDiscardedDeviceList());

        mockMvc.perform(get("/devices/discard").contentType(contentType))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.[0].discardedDeviceId").value(1L))
                .andExpect(jsonPath("$.[0].discardStatus").value("DISCARD"))
                .andExpect(jsonPath("$.[1].discardedDeviceId").value(2L))
                .andExpect(jsonPath("$.[1].discardStatus").value("DISCARD"))
                .andExpect(jsonPath("$.[2].discardedDeviceId").value(3L))
                .andExpect(jsonPath("$.[2].discardStatus").value("DISCARD"))
                .andDo(print());
    }

    @Test
    void shouldUpdateDevice() throws Exception {

        String json = new ObjectMapper().writeValueAsString(DeviceObjectMother.updateRequestDto());

        mockMvc.perform(put("/devices/1")
                        .content(json)
                        .contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print());

        verify(deviceService).updateDevice(eq(1L),eq(DeviceObjectMother.updateRequestDto()));
    }

    @Test
    void shouldChangeDeviceStatus() throws Exception {

        mockMvc.perform(put("/devices/1/status").contentType(contentType))
                .andExpect(status().isOk())
                .andDo(print());

        verify(deviceService).changeDeviceStatus(eq(1L));

    }

    @Test
    void shouldDeleteDevice() throws Exception {

        mockMvc.perform(delete("/devices/1").contentType(contentType))
                .andExpect(status().isNoContent())
                .andDo(print());

        verify(deviceService).deleteDevice(eq(1L));
    }
}