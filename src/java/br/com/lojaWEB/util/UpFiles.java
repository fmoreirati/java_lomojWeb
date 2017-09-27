package util;

import java.io.File;
import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

/**
 *
 * @author FabianoMoreira.ti@gmail.com
 */
@MultipartConfig
public class UpFiles{

    //Define variaveis e/ou Constantes
    private String[] fileName = null, tipo = null;
    private String SAVE_DIR = "";

    //Pega os nomes dos Arquivos enviados
    public String[] getFiles() {
        return fileName;
    }

    //Altera o caminho do Arquivo
    public void setPath(String path) {
        this.SAVE_DIR += path;
    }

    public boolean send(HttpServletRequest request) throws ServletException, IOException, Exception {

        try {
            //Define a pasta para slavar a foto
            String savePath = request.getServletContext().getRealPath("//"+SAVE_DIR);
        
            // Cria diretorio caso não exista;
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }

            /*Identifica se o formulario é do tipo multipart/form-data*/
            if (ServletFileUpload.isMultipartContent(request)) {

                /*Faz o parse do request*/
                List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

                //Cria uma lista de nome de arquivos
                fileName = new String[50];
                int x = 0;
                System.err.println("Itens: " + multiparts.toString());
                /*Escreve a o arquivo na pasta img*/
                for (FileItem item : multiparts) {
                    System.out.println("ALERTAS: Tipo " + item.getString());
                    if (!item.isFormField()) {
                        tipo = item.getContentType().split("/");

                        // if (tipo[1].length() <= 4) {
                        fileName[x] = util.Crypt.md5(item.getName()) + "." + tipo[1];
                        item.write(new File(savePath + File.separator + fileName[x]));
                        System.out.print("Caminho: " + savePath + File.separator + fileName[x]);
                        x++;
                        }
                    //}
                }
            }
        } catch (Exception io) {
            System.err.println("Erro ao enviar o arquivo: " + io.toString());
            throw new Exception("Erro ao enviar o arquivo: " + io);
            //return false;
        }
        //retorna um vetor com os nome dos arquivos

        return true;
    }
}
