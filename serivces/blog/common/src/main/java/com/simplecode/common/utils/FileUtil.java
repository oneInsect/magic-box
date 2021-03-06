package com.simplecode.common.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.FileCopyUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.*;


public class FileUtil {

    private static final String userDir = System.getProperty("user.dir");
    private static final String tmpFileDir = userDir + "/tmpFile/";
    private static final String saveFileDir = userDir + "/saved/";
    private static final Logger log = LoggerFactory.getLogger(FileUtil.class);

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
    public static void download(String path, String filename, HttpServletResponse res) throws IOException {
        String filePath = saveFileDir + path + File.separator + filename;
        // 发送给客户端的数据
        OutputStream outputStream = res.getOutputStream();
        byte[] buff = new byte[1024];
        BufferedInputStream bis = null;
        // 读取filename
        bis = new BufferedInputStream(new FileInputStream(new File(filePath)));
        int i = bis.read(buff);
        while (i != -1) {
            outputStream.write(buff, 0, buff.length);
            outputStream.flush();
            i = bis.read(buff);
        }
    }

    public static Boolean saveTmpFile(MultipartFile uploadFile) {
        String filename = uploadFile.getOriginalFilename();
        String filePath = tmpFileDir + filename;
        return writeFile(uploadFile, filePath);
    }


    public static Boolean saveTmpFile2server(String fileName, String filePath) {
        String tmpFilePathString = tmpFileDir + fileName;
        String saveFilePathString = saveFileDir + fileName;
        if (!StringUtils.isEmpty(filePath)) {
            saveFilePathString = saveFileDir + filePath + "/" + fileName;
        }
        File tmpFilePath = new File(tmpFilePathString);
        File saveFilePath = new File(saveFilePathString);
        if (!saveFilePath.getParentFile().exists()){
            saveFilePath.getParentFile().mkdirs();
        }
        try {
            FileCopyUtils.copy(tmpFilePath, saveFilePath);
        }catch (IOException e){
            log.error("save file failed, file: " + fileName);
            return false;
        }
        return true;
    }
}
