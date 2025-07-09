package com.ldk.service;

import com.ldk.api.json.ResponseResult;
import com.ldk.pojo.Appointment;
import org.springframework.transaction.annotation.Transactional;

public interface AppointmentService {
    @Transactional
    ResponseResult createAppointment(Appointment appointment);
}
