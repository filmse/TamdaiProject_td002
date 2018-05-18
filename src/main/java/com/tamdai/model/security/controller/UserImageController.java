package com.tamdai.model.security.controller;

import com.tamdai.model.security.entity.UserEntity;
import com.tamdai.model.security.entity.UserImage;
import com.tamdai.model.security.repository.UserImageRepository;
import com.tamdai.model.security.service.UserService;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Iterator;

/**
 * Created by Dto on 3/29/2016.
 */

@CrossOrigin
@Controller
@RequestMapping("/")
public class UserImageController {

    @Autowired
    UserService userService;

    @Autowired
    UserImageRepository userImageRepository;

    // public static String PathUsertimg = "C:\\Users\\Film_PC\\Documents\\Projects\\TD002\\src\\main\\resources\\imageuser\\";
    public static String PathUsertimg = "/opt/resource/imageuser/";

    @RequestMapping(value = "add/ImageUser", method = RequestMethod.POST)
    @ResponseBody
    public UserEntity addImage(HttpServletRequest request, @RequestParam("file") MultipartFile file, @RequestParam("userId") Long userId) {
        MultipartHttpServletRequest mRequest;
        UserEntity userEntity = userService.getUserId(userId);

        try {
            System.out.println("====================================");
            mRequest = (MultipartHttpServletRequest) request;
            Iterator<String> itr = mRequest.getFileNames();
            while (itr.hasNext()) {

                MultipartFile multipartFile = mRequest.getFile(itr.next());
                UserImage image = new UserImage();
                image.setFileName(multipartFile.getOriginalFilename());
                image.setContentType(multipartFile.getContentType());
                image.setContent(multipartFile.getBytes());
                userService.addUserImage(userEntity, image);

                byte[] bytes = file.getBytes();
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(
                        new File(PathUsertimg + multipartFile.getOriginalFilename())));
                stream.write(bytes);
                stream.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userEntity;
    }

    @RequestMapping(value = "displayImageUser/{id}", method = RequestMethod.GET)
    @ResponseBody
    public void displayImageUser(@PathVariable("id") Long id, HttpServletResponse response) throws IOException {

        UserImage image = userImageRepository.getOne(id);

        String filePath = PathUsertimg + image.getFileName();

        int fileSize = (int) new File(filePath).length();
        response.setContentLength(fileSize);
        response.setContentType("image");

        FileInputStream inputStream = new FileInputStream(filePath);
        ServletOutputStream outputStream = response.getOutputStream();
        int value = IOUtils.copy(inputStream, outputStream);
        System.out.println("File Size :: " + fileSize);
        System.out.println("Copied Bytes :: " + value);
        IOUtils.closeQuietly(inputStream);
        IOUtils.closeQuietly(outputStream);
    }

    @RequestMapping(value = "delete/ImageUser", method = RequestMethod.DELETE)
    @ResponseBody
    public UserEntity deleteImageUser(@RequestParam("imageId") Long imageId, @RequestParam("userId") Long userId) {
        UserEntity userEntity = userService.getUserId(userId);
        return userService.deleteImageUser(userEntity, imageId);
    }
}