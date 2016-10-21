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
import jsageImport.modelo.dominio.Usuario;

/**
 *
 * @author Jeff-Info
 */
public class PropertiesLogin {
    
    public void criarProperties (Usuario usuario, String nomeArquivo) throws JsageImportException{
         //cira um objeto da classe java.ulti.Properties
        Properties properties = new Properties();
        
        //setando as propriedades e os valores
        properties.setProperty("usuario.user", usuario.getLogin());
        properties.setProperty("usuario.pass", usuario.getSenha());
        
        
        try{
            //criarmos um objeto FileOutputStrema
            FileOutputStream fos = new FileOutputStream(".//"+nomeArquivo+".properties");
            // grava os dados no arquivo
            properties.store(fos, "FILE USUARIO PROPERTIES:");
            //fecha o arquivo
            fos.close();
        } catch(IOException ex){
            throw new JsageImportException("O arquivo usuario properties não foi gerado " + ex.getMessage());
        }
    }
    
   public String lerLogin(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String login = properties.getProperty("usuario.user"); 
        
              
        
	return login;
    }
   
   public String lerSenha(String nomeArquivo) {
	Properties properties = new Properties();

	try {
		//Setamos o arquivo que vai ser lido
		FileInputStream fis = new FileInputStream(".//"+nomeArquivo+".properties");
		//metodo load faz a leitura atraves do objeto fis
		properties.load(fis);
	} catch (IOException e) {
		e.printStackTrace();
	}

	//Captura o valor da propriedade, atraves do nome da propriedade(Key)
	String senha = properties.getProperty("usuario.pass");        
        
	return senha;
    }
}
