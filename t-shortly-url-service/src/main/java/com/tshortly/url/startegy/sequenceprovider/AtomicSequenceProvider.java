package com.tshortly.url.startegy.sequenceprovider;

import org.springframework.stereotype.Component;

import java.util.concurrent.atomic.AtomicLong;

@Component("atomicSequenceProvider")
public class AtomicSequenceProvider implements SequenceProvider{
    private final AtomicLong counter = new AtomicLong(1);
    @Override
    public Long next() {
        return counter.getAndIncrement();
    }
}