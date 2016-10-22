
/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jsageImport.modelo.persistencia;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Properties;
import jsageImport.exception.JsageImportException;

/**
 *
 * @author Gustavo
 */
public class PropertiesJdbc {

    public void criarProperties (JdbcConnection jdbc, String nomeArquivo) throws JsageImportException{
         //cira um objeto da classe java.ulti.Properties
        Properties properties = new Properties();
        
        //setando as propriedades e os valores
        properties.setProperty("jdbc.user", jdbc.getUser());
        properties.setProperty("jdbc.pass", jdbc.getPass());
        properties.setProperty("jdbc.server", jdbc.getServer());
        properties.setProperty("jdbc.database", jdbc.getDatabase());
        properties.setProperty("jdbc.port", jdbc.getPort());
        
        try{
            //criarmos um objeto FileOutputStrema
            FileOutputStream fos = new FileOutputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
            // grava os dados no arquivo
            properties.store(fos, "FILE JDBC PROPERTIES:");
            //fecha o arquivo
            fos.close();
        } catch(IOException ex){
            throw new JsageImportException("O arquivo properties não foi gerado " + ex.getMessage());
        }
    }
    
    public String lerPropriedades(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p1 = properties.getProperty("jdbc.user");
	String p2 = properties.getProperty("jdbc.pass");
	String p3 = properties.getProperty("jdbc.server");
	String p5 = properties.getProperty("jdbc.database");
        String p6 = properties.getProperty("jdbc.port");
        
        String url = "jdbc:sqlserver://" + p3 +":"+ p6+";databaseName="+ p5 +";user="+ p1 +";password="+ p2 +";";
        //System.out.println(url);
	return url;
    }
    
    public String lerServidor(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p3 = properties.getProperty("jdbc.server");        
        
	return p3;
    }
    
    public String lerDatabase(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p3 = properties.getProperty("jdbc.database");        
        
	return p3;
    }
    
    public String lerPorta(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p3 = properties.getProperty("jdbc.port");        
        
	return p3;
    }
    
    public String lerUsuario(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p3 = properties.getProperty("jdbc.user");        
        
	return p3;
    }
    
    public String lerSenha(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//Arquivos//Config//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String p3 = properties.getProperty("jdbc.pass");        
        
	return p3;
    }
    
}