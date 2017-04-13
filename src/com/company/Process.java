package com.company;


import java.util.LinkedList;
import java.util.Scanner;

public class Process {
    public static void main(String[] args) throws Exception {
        Steal steal = new Steal();
        Scanner sc = new Scanner(System.in);
        String filename = "input.json";//System.getenv("S_PATH");
        Runtime.getRuntime().addShutdownHook(new Thread(() -> System.out.println("\n" + Commands.save(steal.clubni, filename))));
        if (filename == null) {
            System.out.println(" Имя файла должно передаваться программе с помощью переменной окружения. (S_PATH)");
            Runtime.getRuntime().exit(5);
        } else if (filename.equals("")) {
            System.out.println("Не корректно задано значения для filename.");
            Runtime.getRuntime().exit(5);
        }

        steal.stealPotatoes(filename); //Подгрузка содержимого файла
        System.out.println("Для просмотра списка команд введите:\"help\"");

        while (sc.hasNextLine()) {
            String newLine = sc.nextLine();
            String[] comm = newLine.split("&");
            for (String strsc1 : comm) {
                Scanner strsc = new Scanner(strsc1);
                if (newLine.length() == 0) {
                    continue;
                }
                if (strsc.hasNext()) {
                    switch (strsc.next()) {
                        case "remove_last": {
                            Commands.remove_last(steal.clubni);
                            break;
                        }
                        case "remove_first": {
                            Commands.remove_first(steal.clubni);
                            break;
                        }
                        case "help": {
                            Commands.help();
                            continue;
                        }
                        case "remove_greater": {
                            if (!strsc.hasNext()) {
                                System.out.println("Нужно добавить элемент... Формат: {\"num\":<int>,\"weight\":<int>}, вместо <type> значение типа type");
                                break;
                            }
                            steal.clubni = Commands.remove_greater(strsc.nextLine().replace(" ", ""), steal.clubni);
                            break;
                        }
                        case "add_if_max": {
                            if (!strsc.hasNext()) {
                                System.out.println("Нужно добавить элемент...");
                                break;
                            }
                            steal.clubni = Commands.add_if_max(strsc.nextLine().replace(" ", ""), steal.clubni);
                            break;
                        }
                        case "show_elements": {
                            Commands.show_elements(steal.clubni);
                            continue;
                        }
                        case "cat": {
                            Commands.cat();
                            continue;
                        }
                        case "duck": {
                            Commands.duck();
                            continue;
                        }
                        case "help_element": {
                            Commands.help_element();
                            continue;
                        }
                        case "add_default_elements": {
                            Commands.add_default_elements(steal.clubni);
                            break;
                        }
                        case "remove_all": {
                            steal.clubni = new LinkedList<>();
                            break;
                        }
                        case "save": {
                            Commands.save(steal.clubni, filename);
                            break;
                        }
                        default: {

                            if (newLine.contains("add_if_max")) {
                                System.out.println("Возможно неверный формат команды \"add_if_max\"...(help)");
                            } else if (newLine.contains("remove_greater")) {
                                System.out.println("Возможно неверный формат команды \"remove_greater\"...(help)");
                            } else {
                                System.out.println("Такой команды нет или некорректный ввод...(help)");
                            }
                        }
                    }
                }
            }
        }

    }
}