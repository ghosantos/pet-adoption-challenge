package util;

import model.Pet;

import java.io.*;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class FileUtils {

    private static final DateTimeFormatter FORMATTER = DateTimeFormatter.ofPattern("yyyyMMdd'T'HHmm");

    private static final String PATH = "formulario.txt";

    public static void readForm(){
        try (BufferedReader br = new BufferedReader(new FileReader(PATH))){
            System.out.println();
            String line = br.readLine();
            while (line != null){
                System.out.println(line);
                line = br.readLine();
            }
        }catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void writeForm(Pet pet){
        String pathStr = "registeredPets";
        String fileName = LocalDateTime.now().format(FORMATTER) + "-" + pet.getName().replaceAll("\\s","").trim().toUpperCase() + ".txt";

        File path = new File(pathStr);
        path.mkdirs();

        File file = new File(path, fileName);

        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))){
            StringBuilder sb = new StringBuilder();
            sb.append("1 - ").append(pet.getName()).append("\n")
                    .append("2 - ").append(pet.getPetType()).append("\n")
                    .append("3 - ").append(pet.getPetSex()).append("\n")
                    .append("4 - ").append(pet.getAddress()).append("\n")
                    .append("5 - ").append(pet.getAge()).append(" Anos").append("\n")
                    .append("6 - ").append(pet.getWeight()).append(" KG").append("\n")
                    .append("7 - ").append(pet.getRace()).append("\n");
            bw.write(sb.toString());
        }catch (IOException e){
            System.out.println("Erro ao salvar arquivo: " + e.getMessage());
            e.printStackTrace();
        }
    }
}