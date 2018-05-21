package com.example.project9;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.Serializable;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;

@Controller
@RequestMapping("/gallery")
public class GalleryController implements Serializable {

    private Map<Integer, Picture> pictureData = new HashMap<Integer, Picture>();
    private int idCounter = 0;

    @Value("E:/PROGRAMOWANIE/JAVA/PROJEKTY/Project9(Spring_boot_RestAPI)/project9(boot)/src/main/resources/static/image/")
    private String imagesPath;

    @Value("/static/image/")
    private String imagePath;

    @RequestMapping(value = "/delete/{index}", method = RequestMethod.DELETE)
    @ResponseBody
    public Answer delete(@PathVariable("index") int index) {

        Picture picture = pictureData.remove(index);

        if(picture == null)
            return new Answer(false);
        else {
            Path path = Paths.get(imagesPath+picture.getName());
            try {
                Files.delete(path);
            }
            catch (IOException e)
            {
                e.printStackTrace();
            }
            return new Answer(true);
        }
    }

    @RequestMapping(value = "/pictures", method = RequestMethod.GET)
    @ResponseBody
    public List<Picture> pictures() {
        List<Picture> list = new ArrayList<>();
        for(Integer key : pictureData.keySet()) {
            list.add(pictureData.get(key));
        }

        return list;

    }

    @RequestMapping(value = "/picture/{index}", method = RequestMethod.GET, produces = MediaType.IMAGE_JPEG_VALUE)
    public void picture(@PathVariable("index") int index, HttpServletResponse response) throws IOException
    {
        ClassPathResource imgFile = new ClassPathResource(imagePath+pictureData.get(index).getName());
        response.setContentType(MediaType.IMAGE_JPEG_VALUE);
        StreamUtils.copy(imgFile.getInputStream(), response.getOutputStream());
    }

    @RequestMapping("/upload")
    public String upload() {
        return "redirect:/index.html";
    }

    @PostMapping("/uplooad")
    @ResponseBody
    public Answer singleFileUpload(@RequestParam("file") MultipartFile file) {

        try {
            byte[] bytes = file.getBytes();
            Path path = Paths.get(imagesPath + file.getOriginalFilename());
            Files.write(path, bytes);

            Picture picture = new Picture();
            picture.setID(idCounter);
            picture.setName(file.getOriginalFilename());
            picture.setDate(new Date());

            BufferedImage bimg = ImageIO.read(new java.io.File(imagesPath+file.getOriginalFilename()));
            picture.setResolution(bimg.getWidth()+"x"+bimg.getHeight());

            picture.setSize(file.getSize());
            pictureData.put(idCounter, picture);
            idCounter++;

            return new Answer(true);
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new Answer(false) ;
    }
}