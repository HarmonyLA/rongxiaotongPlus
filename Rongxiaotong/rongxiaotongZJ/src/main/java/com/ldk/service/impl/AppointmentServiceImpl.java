package com.ldk.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldk.api.json.ResponseResult;
import com.ldk.mapper.AppointmentMapper;
import com.ldk.pojo.Appointment;
import com.ldk.service.AppointmentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Date;
@Service
public class AppointmentServiceImpl
        extends ServiceImpl<AppointmentMapper, Appointment>
        implements AppointmentService {

    // 无需手动设置时间，插入时会自动填充
    public ResponseResult createAppointment(Appointment appointment) {
        save(appointment);
        return new ResponseResult()
                .setCode(200)
                .setMessage("success")
                .setData(appointment);
    }
}
//@Service
//public class AppointmentServiceImpl implements AppointmentService {
//
//    @Autowired
//    private AppointmentMapper appointmentMapper;
//
//    @Transactional
//    @Override
//    public ResponseResult createAppointment(Appointment appointment) {
//        appointment.setCreateTime(new Date());
//        int affected = appointmentMapper.insert(appointment);
//
//        if (affected == 1) {
//            return new ResponseResult(200, "预约成功", appointment, null);
//        }
//        return new ResponseResult(500, "预约失败", null, null);
//    }
//}
