package util;

import java.io.File;
import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 *
 * @author FabianoMoreira.ti@gmail.com
 */
public class UpFilesL {

    private String[] fileName = null, tipo = null;
    private String SAVE_DIR = "/../../web/";

    public String[] getFiles() {
        return fileName;
    }

    public void setPath(String path) {
        this.SAVE_DIR += path;
    }

    public boolean send(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, Exception {

        try {
            //Define a pasta para do aplicativo
            //String appPath = request.getServletContext().getContextPath();
            String appPath = request.getServletContext().getRealPath("");

            //Define a pasta para slavar a foto
            //String savePath = SAVE_DIR;
            String savePath = appPath+SAVE_DIR;

            //Mostra o local onde será quavado o arquivo
            System.out.println("Aplicativo path:" + savePath);

            // Cria diretorio caos não exista;
            File saveDir = new File(savePath);
            if (!saveDir.exists()) {
                saveDir.mkdir();
            }

            //Cria uma lista de nome de arquivos
            fileName = new String[request.getParts().size()];
            int x = 0;
            
            //Varre todos os campos do formulario e pega apenas os arquivos de foto
            for (Part part : request.getParts()) {
                System.out.println("ALERTA: "+part);
                if (part.getSubmittedFileName() != null) {
                    tipo = part.getContentType().split("/");
                    if (tipo[1].length() <= 4) {
                        fileName[x] = util.Crypt.md5(part.getSubmittedFileName()) + "." + tipo[1];
                     
                        part.write(savePath + File.separator + fileName[x]);
                        x++;
                    }
                }
            }
        } catch (IOException io) {
            System.err.println("Erro ao enviar o arquivo: " + io.toString());
            throw new Exception("Erro ao enviar o arquivo: "+ io);
            //return false;
        }
        //retorna um vetor com os nome dos arquivos
        return true;
    }
}
