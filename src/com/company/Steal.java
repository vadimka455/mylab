package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.io.*;
import java.lang.reflect.Type;
import java.util.LinkedList;
import java.util.Scanner;

class Steal {
    LinkedList<Potatoes> clubni = new LinkedList<>();

    void stealPotatoes(String filename) throws Exception{
        Scanner sc = new Scanner(System.in);
        GsonBuilder gsonBuilder = new GsonBuilder();
        try {
            FileInputStream fileInputStream = new FileInputStream(filename);
            String json = new BufferedReader(new InputStreamReader(fileInputStream)).readLine();
            System.out.println(filename); //Проверка какая-то (убрать потом)
            if (json==null || json.equals("")){
                FileOutputStream fileOutputStream = new FileOutputStream(filename);
                OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                output.write("[]");
                output.close();
                json = "[]";
            }
            Gson gson = gsonBuilder.create();
            Type potatoesListType = new TypeToken<LinkedList<Potatoes>>(){}.getType();
            clubni = gson.fromJson(json, potatoesListType);
            clubni.sort(
                    (Potatoes pot1, Potatoes pot2) ->
                            pot2.getWeight() - pot1.getWeight());
        }catch (FileNotFoundException e){
            System.out.print("Файла нет, но я могу создать :) Создать? (yes/no) ");
            if (sc.hasNext()) {
                String answer =sc.next();
                if (answer.equals("yes")) {
                    try{
                        if(!new File(filename).createNewFile()){
                            System.out.println("Файл нельзя создать почему-то...");
                        }else{
                            FileOutputStream fileOutputStream = new FileOutputStream(filename);
                            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
                            output.write("[]");
                            output.close();
                        }
                    }catch (Exception es){
                        System.out.println("Файл нельзя создать, что-то пошло не так...");
                        sc.close();
                    }
                } else if (answer.equals("no")) {
                    System.out.println("Тогда пока...");
                    sc.close();
                }
            }
        }catch (Exception es){
            System.out.println("one" + es);
        }
    }
}