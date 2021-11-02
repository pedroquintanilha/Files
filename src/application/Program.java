package application;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Scanner;

import model.entities.Product;

public class Program {

	public static void main(String[] args) {
		
		Locale.setDefault(Locale.US);
		Scanner sc = new Scanner (System.in);
		System.out.print("Enter file path: ");		
		File file = new File(sc.nextLine());
		List<Product> listOfProducts = new ArrayList<>();
		
		try (BufferedReader br = new BufferedReader (new FileReader(file))){
			//Reading the first line of the file
			String line = br.readLine();
			
			while(line != null) {
				String[] str = line.split(",");
				double price = Double.parseDouble(str[1]);  
				int quantity = Integer.parseInt(str[2]);
				listOfProducts.add(new Product (str[0],price,quantity));
				line = br.readLine();
			}
		}
		catch(IOException e) {
			System.out.print("Error reading file: "+e.getMessage());	
			}
		
		//Creating a subfolder 
		String folder = file.getParent();
		String subfolder = folder+"\\out";
		boolean success = new File (subfolder).mkdir();		
		System.out.print("Folder created successfully: " + success);
		
		String newPath = subfolder+"\\summary.txt";
		
		try (BufferedWriter bw = new BufferedWriter (new FileWriter(newPath))){
			
			for(Product p : listOfProducts) {
				bw.write(p.getName()+","+String.format("%.2f",p.totalPrice()));
				bw.newLine();
				
			}
		}
		catch(IOException e) {
			System.out.print("Error writing file: "+e.getMessage());
		}
		
		sc.close();
	}
}
	
			
			
			
		

	


