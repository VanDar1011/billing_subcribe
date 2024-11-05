package com.example.biling_system.controller;

import com.example.biling_system.contant.ResponseCode;
import com.example.biling_system.model.ResponseObject;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/api/test")
public class TestController {
    @GetMapping
    public ResponseEntity<ResponseObject> test() {
        return ResponseEntity.status(HttpStatus.OK).body(new ResponseObject(ResponseCode.OK.getStatus(), "ok", null
        ));
    }

}
