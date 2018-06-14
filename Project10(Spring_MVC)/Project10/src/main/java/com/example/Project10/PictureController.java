package com.example.Project10;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


import javax.imageio.ImageIO;
import javax.servlet.ServletContext;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@RestController
@RequestMapping(value = "/gallery")
public class PictureController implements Serializable {
    private int idCounter = 0;

    @Value("${img.pictures.path}")
    String path;

    @Autowired
    ResourceLoader resourceLoader;

    @Autowired
    private ServletContext servletContext;





    @RequestMapping(method = RequestMethod.GET)
    public ModelAndView getListOfPictures() throws IOException {
        File dir = new File(path);
        List<Picture> pictureList = new ArrayList<>();
        File[] everythingInThisDir = dir.listFiles();
        for (File file : everythingInThisDir) {
            System.out.println(file.getPath());
            Image image = ImageIO.read(file);
            Picture picture = new Picture();
            picture.setIndex(idCounter);
            picture.setName(file.getName());
            picture.setSize(file.length());
            picture.setResolution(((BufferedImage) image).getHeight()+"x"+((BufferedImage) image).getWidth());
            picture.setDate(new Date());
            idCounter++;
            pictureList.add(picture);

        }
        return  new ModelAndView("delete","images",pictureList);
    }


    @RequestMapping(method = RequestMethod.DELETE,
            value = "/{pictureId}")
    public boolean deletePicture(@PathVariable("pictureId") String pictureId) {
        File dir = new File(path);
        File[] everythingInThisDir = dir.listFiles();
        for (File file : everythingInThisDir) {
            if (file.getName().equals(pictureId) ) {
                file.delete();
                return true;
            }
        }
        return false;
        }


    @RequestMapping(method = RequestMethod.GET,value = "{pictureId}")
    public ModelAndView getPicture(@PathVariable("pictureId") String pictureId) throws IOException {
        File dir = new File(path);
        File[] everythingInThisDir = dir.listFiles();
        List<Picture> pictureList = new ArrayList<>();
        for (File file : everythingInThisDir) {
            if (file.getName().equals( pictureId)) {
                System.out.println(file.getPath());
                Image image = ImageIO.read(file);
                Picture picture = new Picture();
                picture.setIndex(idCounter);
                picture.setName(file.getName());
                picture.setSize(file.length());
                picture.setResolution(((BufferedImage) image).getHeight()+"x"+((BufferedImage) image).getWidth());
                picture.setDate(new Date());
                pictureList.add(picture);
                return new ModelAndView("select","images",pictureList);
            }
        }
        throw new FileNotFoundException();
    }


}



