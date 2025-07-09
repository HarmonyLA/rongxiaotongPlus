package com.ldk.controller;

import com.ldk.api.json.ResponseResult;
import com.ldk.pojo.Appointment;
import com.ldk.service.AppointmentService;
import io.swagger.v3.oas.annotations.parameters.RequestBody;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Date;

@RestController
@RequestMapping("/api/cze/appointments")
public class AppointmentController {

    @Autowired
    private AppointmentService appointmentService;

    // 新增预约
    @PostMapping
    public ResponseResult create(@RequestBody Appointment appointment) {
        return appointmentService.createAppointment(appointment);
    }
}
