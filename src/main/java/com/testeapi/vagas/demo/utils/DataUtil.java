package com.testeapi.vagas.demo.utils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class DataUtil {
    public static String getDataAtualDMYHMS() {
        LocalDateTime now = LocalDateTime.now();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        return now.format(formatter);
    }
}
