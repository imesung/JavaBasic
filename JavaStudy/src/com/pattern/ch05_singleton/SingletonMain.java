package com.pattern.ch05_singleton;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;

public class SingletonMain {

    public static void main(String [] args) throws ClassCastException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException, ClassNotFoundException, NoSuchFieldException {
        SystemSpeaker systemSpeaker1 = SystemSpeaker.getInstance();
        SystemSpeaker systemSpeaker2 = SystemSpeaker.getInstance();

        System.out.println("systemSpeaker1 : " + systemSpeaker1);
        System.out.println("systemSpeaker2 : " + systemSpeaker2);

        //리플렉션을 활용하여 싱글톤 깨버리기
        Class<?> speakerClass = Class.forName("com.pattern.ch05_singleton.SystemSpeaker");
        Constructor<?> constructor = speakerClass.getDeclaredConstructor();
        constructor.setAccessible(true);
        SystemSpeaker systemSpeaker3 = (SystemSpeaker) constructor.newInstance();
        System.out.println("systemSpeaker3 : " + systemSpeaker3);


        //enum 사용
        System.out.println(System.identityHashCode(EnumSystemSpeaker.INSTANCE.getClass()));
        System.out.println(System.identityHashCode(EnumSystemSpeaker.INSTANCE.getClass()));
    }
}
