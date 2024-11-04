package com.example.practicaspring.controller;

import com.example.practicaspring.model.Product;
import com.example.practicaspring.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

@RestController
@CrossOrigin
@RequestMapping("/api")
public class ProductController {


    /*Permite usar metodos de ProductService sin instanciarlo, spring crea el objeto manualmente*/
    @Autowired
    private ProductService productService;


    @GetMapping("/products")
    public ResponseEntity<List<Product>> getProducts(){
      return new ResponseEntity<>(productService.getProduct(), HttpStatus.OK);
    }

    /* Llama al objeto con la id especifica, si lo encuentra marca Ok200, sino NOTFOUND400*/
    @GetMapping("/product/{id}")
    public ResponseEntity<Product> getProductById(@PathVariable int id){

        if(productService.getProductbyId(id)!=null){
            return new ResponseEntity<>(productService.getProductbyId(id), HttpStatus.OK);
        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }
    /* Se usa request part ya que la imagen no tiene el mismo formato que el objeto, por lo que la solicitud se debe dividir en 2*/
    @PostMapping("/product")
    public ResponseEntity<?> addProduct(@RequestPart Product product,
                                        @RequestPart MultipartFile imageFile){
        try{
            Product product1 =productService.addProduct(product, imageFile);
            return new ResponseEntity<>(product1, HttpStatus.CREATED);
        }
        catch(Exception e){
            return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/product/{id}/image")
    public ResponseEntity<byte[]> getImageByProductId(@PathVariable int id){
        Product product = productService.getProductbyId(id);
        byte[] imageFile = product.getImageDate();
        return ResponseEntity.ok().contentType(MediaType.valueOf(product.getImageType()))
                .body(imageFile);
    }

    @PutMapping("/product/{id}")
    public ResponseEntity<?> updateProduct(@PathVariable int id,@RequestPart Product product,
                                           @RequestPart MultipartFile imageFile) throws IOException {
        Product product1= productService.updateProduct(id, product, imageFile);
        if(product1!=null){
            return new ResponseEntity<>(product1, HttpStatus.OK);

        }else{
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

    }



    @DeleteMapping("/product/{id}")
    public void deleteProduct(@PathVariable int id){
        productService.deteleProduct(id);
    }


}
