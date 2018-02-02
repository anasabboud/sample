package com.example.sample;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.stereotype.Service;

@Service
public class LogDetailService {

    @Autowired
    private Tracer tracer;

    public Span getCurrentSpan() {
        return tracer.getCurrentSpan();
    }
}