package com.example.service;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.config.BeanDefinition;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CounterService {
    private int count = 0;

    public void increment(){
        count++;
    }

    public int getCount(){
        return count;
    }
}
