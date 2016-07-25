package jsageImport.log;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;


public class LogSage
{
    public void LogTxt(String nome,String classe, String arquivo )
    {
        File arquivoTxt = new File("log_"+arquivo+".txt");

        if(!arquivoTxt.exists())
        {
            try
            {   //Cria o arquivo
                arquivoTxt.createNewFile();
                System.out.println("Arquivo criado");

                //salva o arquivo
                FileWriter  writer = new FileWriter(arquivoTxt);
                writer.write("Registro: " + nome+" --- ");
                //writer.write("Classe: "+classe);
                writer.write("Data:"+ new Date()+"\n");

                writer.close();
                System.out.println("Arquivo salvado");
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
        }
        else
        {
            try
            {
                FileReader reader = new FileReader(arquivoTxt);
                BufferedReader br = new BufferedReader(reader);
                String linha = br.readLine();
                FileWriter  writer = new FileWriter(arquivoTxt, true);

                while(linha != null)
                {
                    //writer.write(linha+"\n");
                    br.readLine();
                    linha = br.readLine();
                }

                br.close();
                reader.close();

                writer.write("Registro: " + nome+ " --- ");
                //writer.write("Classe: "+classe);
                writer.write(" Data:"+ new Date() +"\n");
                writer.close();
                System.out.println("Arquivo salvado");
            }
            catch(IOException err)
            {
                err.printStackTrace();
            }
        }
    }
}