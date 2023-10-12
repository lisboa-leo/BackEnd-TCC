package com.pharmexpressgroup.pharmexpress.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;

public class UploadUtil {
    public static boolean fazerUploadImagem(MultipartFile imagem) {
        boolean sucessoUpload = false;

        if (!imagem.isEmpty()) {
            String nomeArquivo = imagem.getOriginalFilename();
            try {
                // diretorio para armazenamento de arquivo
                String pastaUploadImagem = "C:\\Users\\LEANDRO\\Documents\\BackEnd\\BackEnd-TCC\\src\\main\\resources\\static\\imagens\\img-uploads";
                File dir = new File(pastaUploadImagem);

                if (!dir.exists()) {
                    dir.mkdirs();
                }

                //criando o arquivo no diretorio
                File serverFile = new File(dir.getAbsolutePath() + File.separator + nomeArquivo);

                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(serverFile));

                stream.write(imagem.getBytes());
                stream.close();

                System.out.println("Armazenamento em: " + serverFile.getAbsolutePath());
                System.out.println("Você fez o upload do arquivo: " + nomeArquivo + "com sucesso!");

            } catch (Exception e) {
                System.out.println("falham em carregar o arquivo" + nomeArquivo +" =>" + e.getMessage());
            }
        } else{
            System.out.println("falha em  carregar o arquivo");
        }
        return sucessoUpload;
    }
}
