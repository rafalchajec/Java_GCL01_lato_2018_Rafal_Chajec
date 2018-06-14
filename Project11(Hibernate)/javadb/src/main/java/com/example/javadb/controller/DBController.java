package com.example.javadb.controller;

import com.example.javadb.app.app;
import com.example.javadb.entity.Data;
import com.example.javadb.entity.Model;
import com.example.javadb.entity.Producent;
import com.example.javadb.entity.Samochod;
import com.example.javadb.interFaces.ModelInt;
import com.example.javadb.interFaces.ProducentInt;
import com.example.javadb.interFaces.SamochodInt;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.io.*;
import java.sql.*;
import java.util.List;

@Controller
public class DBController {

    @Autowired
    private SamochodInt samochodInt;

    @Autowired
    private ProducentInt producentInt;

    @Autowired
    private ModelInt modelInt;

    @GetMapping("/panel")
    public ModelAndView panel()
    {
        ModelAndView modelAndView = new ModelAndView("panel");
        modelAndView.addObject("categoryList",producentInt.findAll());
        modelAndView.addObject("producerList",modelInt.findAll());
        modelAndView.addObject("app", new app());   return modelAndView;
    }

    @PostMapping("/addSamochod")
    @ResponseBody
    public String addSamochod(String nazwa, String opis,@ModelAttribute("app") app app)
    {
        try {
            Samochod samochod = new Samochod(nazwa, opis, app.getProducent().getIdProducent(), app.getModel().getIdModel());

            samochodInt.save(samochod);
        }
        catch (Exception e)
        {
            return "Error adding Samochod!";
        }
        return "Successfully added Samochod "+nazwa+"!";
    }


    @PostMapping("/deleteSamochodById")
    @ResponseBody
    public String deleteSamochodById(long id) {

        Samochod Samochod;

        if (samochodInt.findById(id).isPresent()) {
            Samochod = samochodInt.findById(id).get();
        }
        else
            return "Samochod doesn't exist!";

        try {
            samochodInt.delete(Samochod);
        }
        catch (Exception ex) {
            return "Error deleting Samochod!";
        }
        return "Successfully deleted Samochod with id "+id+"!";
    }

    @PostMapping("/getSamochodById")
    @ResponseBody
    public ModelAndView getSamochodById(long id) {

        Samochod Samochod = new Samochod("Not found", "Not found", -1L, -1L);
        Samochod.setIdSamochod((long)-1);

        if (samochodInt.findById(id).isPresent()) {
            Samochod = samochodInt.findById(id).get();
        }

        return new ModelAndView("getSamochod", "Samochod", Samochod);
    }

    @PostMapping("/getSamochodByName")
    public ModelAndView getSamochodByName(String name) {
        return new ModelAndView("getSamochod","Samochod",samochodInt.findAllByName(name));
    }

    @PostMapping("/updateSamochod")
    @ResponseBody
    public String updateSamochod(Long SamochodId, String name, String description,@ModelAttribute("app") app app) {
        try {

            if(!samochodInt.findById(SamochodId).isPresent())
                return "Samochod with id "+SamochodId+" doesn't exist!";

            Samochod Samochod = samochodInt.findById(SamochodId).get();
            Samochod.setNazwa(name);
            Samochod.setOpis(description);
            Samochod.setIdProducent(app.getProducent().getIdProducent());
            Samochod.setIdModel(app.getModel().getIdModel());
            samochodInt.save(Samochod);

        }
        catch (Exception e)
        {
            return "Error uploading Samochod!";
        }
        return "Successfully uploaded Samochod "+name+"!";
    }

    @PostMapping("/showSamochods")
    public ModelAndView showSamochods()
    {
        return new ModelAndView("showSamochods","SamochodList", samochodInt.findAll());
    }

    @PostMapping("/addCategory")
    @ResponseBody
    public String addCategory(String name, String description)
    {
        Producent test_category = producentInt.findByName(name);
        if(test_category != null)
            return "This category exists!";

        try {
            Producent producent = new Producent(name, description);
            producentInt.save(producent);
        }
        catch (Exception e)
        {
            return "Error adding category!";
        }
        return "Successfully added category "+name+"!";
    }

    @PostMapping("/deleteCategoryById")
    @ResponseBody
    public String deleteCategoryById(Long categoryId, String only_empty)
    {
        Producent producent;
        if (producentInt.findById(categoryId).isPresent()) {
            producent = producentInt.findById(categoryId).get();
        }
        else
            return "Category doesn't exist!";

        List<Samochod> SamochodList = samochodInt.findAllByCategoryId(categoryId);
        if(SamochodList.isEmpty()) {
            producentInt.delete(producent);
            return "Successfully deleted category with id "+categoryId+"!";
        }
        else if (only_empty.equals("false"))
        {
            for (Samochod p : SamochodList)
            {
                p.setIdProducent(null);
            }
            producentInt.delete(producent);
            return "Successfully deleted category with id "+categoryId+"!";
        }

        return "Cannot delete category with id "+categoryId+"!";

    }

    @PostMapping("/showCategories")
    public ModelAndView showCategories()
    {
        return new ModelAndView("showCategories","categoryList", producentInt.findAll());
    }

    @PostMapping("/addProducer")
    @ResponseBody
    public String addProducer(String name, String description)
    {
        Model test_producer = modelInt.findByName(name);
        if(test_producer != null)
            return "This producer exists!";

        try {
            Model model = new Model(name, description);
            modelInt.save(model);
        }
        catch (Exception e)
        {
            return "Error adding producer!";
        }
        return "Successfully added producer "+name+"!";
    }

    @PostMapping("/deleteProducerById")
    @ResponseBody
    public String deleteProducerById(Long producerId, String only_empty)
    {
        Model model;
        if (modelInt.findById(producerId).isPresent()) {
            model = modelInt.findById(producerId).get();
        }
        else
            return "Category doesn't exist!";

        List<Samochod> SamochodList = samochodInt.findAllByProducerId(producerId);
        if(SamochodList.isEmpty()) {
            modelInt.delete(model);
            return "Successfully deleted producer with id "+producerId+"!";
        }
        else if (only_empty.equals("false"))
        {
            for (Samochod p : SamochodList)
            {
                p.setIdModel(null);
            }
            modelInt.delete(model);
            return "Successfully deleted producer with id "+producerId+"!";
        }

        return "Cannot delete producer with id "+producerId+"!";

    }

    @PostMapping("/showProducers")
    public ModelAndView showProducers()
    {
        return new ModelAndView("showProducers","producerList", modelInt.findAll());
    }

    @PostMapping("/addData")
    @ResponseBody
    public String addData(Long dataId, String name, String description)
    {
        try {
            String URL = "jdbc:mysql://localhost:3306/javadb";
            Connection conn = DriverManager.getConnection(URL, "root", "rafal123");
            Data data = new Data(dataId, name, description);

            PreparedStatement pstmt = conn.prepareStatement("INSERT INTO datas(data_id, desc) VALUES (?,?)");
            pstmt.setLong(1, dataId);
            pstmt.setObject(2, data);
            pstmt.executeUpdate();
            pstmt.close();
        }
        catch (SQLException e)
        {
            e.printStackTrace();
            return "Error!";
        }
        return "Data added!";
    }

    @PostMapping("/showData")
    public ModelAndView showData(Long dataId)
    {
        Data data = null;
        try {
            String URL = "jdbc:mysql://localhost:3306/javadb";
            Connection conn = DriverManager.getConnection(URL, "root", "rafal123");

            PreparedStatement pstmt = conn.prepareStatement("SELECT desc FROM datas WHERE data_id=?");
            pstmt.setLong(1, dataId);
            ResultSet rs = pstmt.executeQuery();
            rs.next();
            byte[] buf = rs.getBytes("desc");
            ObjectInputStream objectIn = null;
            if (buf != null)
                objectIn = new ObjectInputStream(new ByteArrayInputStream(buf));
            Object object = objectIn.readObject();
            data = (Data)object;
            rs.close();
            pstmt.close();
        }
        catch (SQLException | IOException | ClassNotFoundException e)
        {
            e.printStackTrace();
        }

        return new ModelAndView("showData", "data", data);
    }
}