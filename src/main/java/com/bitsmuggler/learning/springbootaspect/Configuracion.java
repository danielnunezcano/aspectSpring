package com.bitsmuggler.learning.springbootaspect;

import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.stereotype.Component;

@Configuration
@EnableAspectJAutoProxy
@ComponentScan("com.bitsmuggler.learning.springbootaspect.aspects")
public class Configuracion {

}
