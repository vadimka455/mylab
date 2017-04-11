package com.company;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonSyntaxException;
import com.google.gson.reflect.TypeToken;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.lang.reflect.Type;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.LinkedList;

class Commands {

    static private GsonBuilder gsonBuilder = new GsonBuilder();
    static private Gson gson = gsonBuilder.create();
    static private Type potatoesType = new TypeToken<Potatoes>() {
    }.getType();

    /**
     * Команда добавляет элемент, если он больше, чем имеющиеся.
     *
     * @param jsonStr - {element} из команды
     * @return возвращает измененную коллекцию
     */
    static LinkedList<Potatoes> add_if_max(String jsonStr, LinkedList<Potatoes> newclubni) {

        try {
            Potatoes pot = gson.fromJson(jsonStr, potatoesType);
            if (newclubni.size() != 0) {
                if (newclubni.element().getWeight() < pot.getWeight()) {
                    newclubni.addFirst(pot);
                    System.out.println("Добавлено...");
                } else {
                    System.out.println("Не добавлено...");
                }
            } else {
                newclubni.addFirst(pot);
            }
        } catch (JsonSyntaxException e) {System.out.println("Некорректный формат элемента... (help_element)");}
        catch (Exception e) {
            System.out.println("Ошибка выполнения...");
        }
        return newclubni;
    }

    /**
     * Команда удаляет все элементы больше введенного.
     *
     * @param jsonStr - {element} из команды
     * @return возвращает измененную коллекцию
     */
    static LinkedList<Potatoes> remove_greater(String jsonStr, LinkedList<Potatoes> newclubni) {
        try {
            System.out.println(jsonStr);
            Potatoes potatoes = gson.fromJson(jsonStr, potatoesType);
            newclubni.removeIf(potatoes1 -> potatoes.getWeight() < potatoes1.getWeight());
        }catch (JsonSyntaxException e ){
            System.out.println("Некорректный формат элемента... (help_element)");
        }
        catch (Exception e){
            System.out.println("Ошибка выполнения...");
        }
        return newclubni;
    }

    /**
     * Команда удаляет последний элемент из коллекции.
     *
     * @return возвращает измененную коллекцию
     */
    static LinkedList<Potatoes> remove_last(LinkedList<Potatoes> newclubni) {
        if (newclubni.size() != 0) {
            newclubni.removeLast();
        }
        return newclubni;
    }

    /**
     * Команда выводит доступный список команд.
     */
    static void help() {
        System.out.println("add_if_max {element}: добавить новый элемент в коллекцию, если его значение превышает значение наибольшего элемента этой коллекции\n" +
                "remove_last: удалить последний элемент из коллекции\n" +
                "remove_first: удалить первый элемент из коллекции\n" +
                "remove_greater {element}: удалить из коллекции все элементы, превышающие заданный");
    }

    static void cat() {
        try {
            Files.lines(Paths.get("cat.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Котика сегодня не будет...");
        }
    }

    static void duck() {
        try {
            Files.lines(Paths.get("duck.txt"), StandardCharsets.UTF_8).forEach(System.out::println);
        } catch (Exception e) {
            System.out.println("Уточка сегодня не выйдет...");
        }
    }

    static void show_elements(LinkedList<Potatoes> newclubni) {
        try {
            System.out.println(gson.toJson(newclubni));
        } catch (Exception e) {
            System.out.println("Не могу вывести, возникли проблемы...");
        }
    }

    /**
     * Команда выводит формат ввода элемента.
     */
    static void help_element() {
        System.out.println("Формат: {[\"num\":<int>[,\"weight\":<int>]}, вместо <type> значение типа type");
    }

    static void add_default_elements(LinkedList<Potatoes> clubni)throws Exception {
            clubni.addLast(new Potatoes(3));
            clubni.addLast(new Potatoes(1));
            clubni.addLast(new Potatoes(4, 8));
            clubni.addLast(new Potatoes(2, 2));
            clubni.sort(
                    (Potatoes pot1, Potatoes pot2) ->
                            pot2.getWeight() - pot1.getWeight());

    }

    /**
     * Команда удаляет первый элемент из коллекции.
     *
     * @return возвращает измененную коллекцию
     */
    static LinkedList<Potatoes> remove_first(LinkedList<Potatoes> newclubni) {
        if (newclubni.size() != 0) {
            newclubni.removeFirst();
        }
        return newclubni;
    }
    static String save (LinkedList<Potatoes> newclubni, String filename){
        try {
            FileOutputStream fileOutputStream = new FileOutputStream(filename);
            OutputStreamWriter output = new OutputStreamWriter(fileOutputStream);
            output.write(gson.toJson(newclubni));
            output.close();
            return "Файл сохранен";
        }catch (FileNotFoundException fileNotFound){
            return ("Файл не сохранен, потому что не найден");
        }catch (IOException ioe){
            return ("Проблема с вводом данных");
        }
    }
}