package pl.coderslab.src;

import pl.coderslab.ConsoleColors;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static java.lang.System.*;

public class TaskManager {

    public static void main(String[] args) {
        String fileName =  "tasks.csv";
        String [] Options ={"add", "remove", "list", "exit"};

        optionsDisplay(Options);
        datatotab("fileName");

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
}

