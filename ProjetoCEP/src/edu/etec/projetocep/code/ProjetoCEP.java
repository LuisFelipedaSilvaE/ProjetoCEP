package edu.etec.projetocep.code;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.net.HttpURLConnection;

public class ProjetoCEP {

	public static void main(String[] args) {
		
		String substring = "[0-9]{8}";
		String cep = "25.251.254/0001-25";
		
		Pattern padrao = Pattern.compile(substring);
		
		Matcher matcher = padrao.matcher(numeroCep);
		
		String numeroCep = "19802100";
		
		if(matcher.matches()){
		
			try{
				
				URL url = new URL("https://viacep.com.br/ws/" + numeroCep + "/json/");
				HttpURLConnection connection = (HttpURLConnection) url.openConnection();
				connection.setRequestMethod("GET");
				
				int responseCode = connection.getResponseCode();
				
				if(responseCode == HttpURLConnection.HTTP_OK){
					BufferedReader reader = new BufferedReader(new InputStreamReader (connection.getInputStream()));
					StringBuilder response = new StringBuilder();
					String line;
					
					while ((line = reader.readLine()) != null){
						response.append(line);
						
					}
					reader.close();
					
					System.out.println(response.toString());
				}
				
				else{
					System.out.println("Falha ao tentar obter CEP" + responseCode);
				}
				
			}catch(Exception e){
				System.out.println(e);
			}
		}else{
			System.out.println("CEP inválido!");
		}
	}

}
