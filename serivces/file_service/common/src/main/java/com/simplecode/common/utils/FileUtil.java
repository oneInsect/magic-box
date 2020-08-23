package com.simplecode.common.utils;

import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class FileUtil {

    public static Boolean saveFile(String cate, MultipartFile multipartFile){
        String filename = multipartFile.getOriginalFilename();
        String fileSaveDir = System.getProperty("user.dir") + "/" + "files" + "/" + cate;
        String filePath = fileSaveDir + "/" + filename;
        return FileUtil.writeFile(multipartFile, filePath);
    }


    private static Boolean writeFile(MultipartFile multipartFile, String filePath){
        File file = new File(filePath);
        if (!file.getParentFile().exists()){
            boolean mk = file.mkdirs();
        }
        try{
            multipartFile.transferTo(file);
        } catch (IOException e){
            e.printStackTrace();
            return false;
        }
        return true;
    }
    public static void download(String filename, HttpServletResponse res) throws IOException {
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File("./file/" + filename)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

}