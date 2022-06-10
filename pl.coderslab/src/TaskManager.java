package pl.coderslab.src;

import org.apache.commons.lang3.ArrayUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class TaskManager {

    public static void main(String[] args) {
        String fileName =  "tasks.csv";
        String [] Options ={"add", "remove", "list", "exit"};
        String[][] taskList;


        optionsDisplay(Options);
        taskList= datatotab("fileName");

        Scanner scanner = new Scanner(System.in);
        while (scanner.hasNextLine()){
            String datainput = scanner.nextLine();
            switch (datainput){
                case "add":
                    addTask(taskList);
                    break;
                case "remove":
                    showList(taskList);
                    removeTask(taskList);
                    break;
                case "list":
                    showList(taskList);
                    break;
               case "exit":
                    closeProgram(taskList,fileName);
                    break;
                default:
                    out.println("Please select a correct option");
            }
            optionsDisplay(Options);

        }

   }

        public static void optionsDisplay(String[] Options){
            out.println(ConsoleColors.BLUE + "Please select an option:" + ConsoleColors.RESET);
            for (String option : Options) {
                out.println(option);
            }
        }

        public static String[][] datatotab(String fileName){

            Path file = Paths.get("/home/beniamin/Workshop1/TaskManager/pl.coderslab/src/tasks.csv");
            if(!Files.exists(file)){
                out.println("Nie znaleziono pliku");
            }
            String[][] tabTasks =null;

            try {
                List <String> listTasks = Files.readAllLines(file);
                tabTasks = new String [listTasks.size()][listTasks.get(0).split(",").length];

                for (int i =0;i<listTasks.size();i++){
                    String[] tasksSplit = listTasks.get(i).split(",");
                    for (int j =0;j< tasksSplit.length;j++) {
                        tabTasks[i][j]=tasksSplit[j];

                    }
                }
            } catch (IOException e) {
                out.println("Nie znaleziono pliku");
            }
            return tabTasks;
        }

        public static String[][] addTask(String[][] listData){
            out.println("Please add task description");
            String taskDescription = new String();
            String taskDate = new String();
            String taskImportance = new String();

            Scanner scanner = new Scanner(System.in);
            taskDescription=scanner.nextLine();
            out.println("Please add task due date");
            taskDate=scanner.nextLine();
            out.println("Is your task is important: true/false");
            taskImportance=scanner.nextLine();

            listData=Arrays.copyOf(listData,listData.length + 1);
            listData[listData.length-1]=new String[3];
            listData[listData.length - 1][0]=taskDescription;
            listData[listData.length-1][1]=taskDate;
            listData[listData.length-1][2]=taskImportance;

            return listData;


        }
        public static void  showList(String[][] listData){
            for (int i = 0; i < listData.length; i++) {
                out.print(i + " : ");
                for (int j = 0; j < listData[i].length; j++) {
                     System.out.print(listData[i][j] + " ");
                }
                out.print("\n");

            }
        }
        public static String[][] removeTask(String[][] listData) {
            out.println("Please select number to remove");
            Scanner scanner = new Scanner(in);
            int number = scanner.nextInt();
            while (!scanner.hasNextInt()) {
                out.println("Try again. It must be number");
                scanner.nextLine();
            }
            if (number < listData.length) {
                listData = ArrayUtils.remove(listData, number);
                out.println("Value was sucessfully deleted");
            }
            while (number > listData.length) {
                out.println("Please select correct number");
                scanner.nextInt();
            }
            return listData;
        }

        public static void closeProgram(String [][]listToSave, String filename){
        Path file = Paths.get("/home/beniamin/Workshop1/TaskManager/pl.coderslab/src/tasks.csv");
        if(!Files.exists(file)) {
            out.println("Nie znaleziono pliku");
        }
        else if (Files.exists(file)){
            String[] tableToSave = new String[listToSave.length];
            for (int i = 0; i < listToSave.length; i++) {
                tableToSave[i]=String.join(",",listToSave[i]);
            }
            try {
                Files.write(file,Arrays.asList(tableToSave));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

            System.exit(0);
        }
        System.out.println(ConsoleColors.RED + "Bye, bye");
        }
}

