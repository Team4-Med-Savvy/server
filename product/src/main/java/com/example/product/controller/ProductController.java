package com.example.product.controller;


import com.example.product.dto.MerchantDto;
import com.example.product.dto.ProductDto;
import com.example.product.dto.ReponseDto;
import com.example.product.entity.Product;
import com.example.product.service.feignservice.ProductFeignService;
import com.example.product.service.impl.ProductServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:3000", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductServiceImpl productServiceImpl;

    @Autowired
    ProductFeignService productFeignService;


    @GetMapping(value="/{id}")
    public Product select(@PathVariable(value = "id") String id){
        return productServiceImpl.select(id);
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody ProductDto productDto){
        Product product=createEntityFromDto(productDto);
        productServiceImpl.save(product);

    }

    @DeleteMapping(value = "/{id}")
    void  delete(@PathVariable(value = "id") String id){
        productServiceImpl.delete(id);

    }

    @GetMapping(value = "recommend")
    List<ProductDto> recommend()
    {
        List<ProductDto> productDtos=new ArrayList<>();
        String cate[]={"Covid_essential","Surgicals","Skin_care","Pet_care","Ayurvedic_care"};

        for(int i=0;i<5;i++)
        {
            List<ReponseDto> listprod=findProduct(cate[i]);
            int size=listprod.size();

            for(i=0;i<2;i++){
            Random rand = new Random();
            int rand_int = rand.nextInt(size);

            ProductDto productDto=new ProductDto();
            BeanUtils.copyProperties(listprod.get(rand_int),productDto);

            List<MerchantDto> merchantDtoList=listprod.get(rand_int).getMerchantdto();
            List<String> merchantid=new ArrayList<>();

            for(i=0;i<merchantDtoList.size();i++){
                merchantid.add(merchantDtoList.get(i).getId());
            }
            productDto.setMerchant(merchantid);
            productDtos.add(productDto);

            }
        }
        return  productDtos;
    }


    @GetMapping(value = "/findlist/{id}")
    List<ReponseDto> findProduct(@PathVariable(value = "id") String id)
    {
//        List<ProductDto> productDtos=new ArrayList<>();
        List<ReponseDto> reponseDtos=new ArrayList<>();


        Iterable<Product> products=productServiceImpl.findProduct(id);

        for(Product temp:products)
        {
//            productDtos.add(createDtoFromEntity(temp));

            ReponseDto reponseDto=new ReponseDto();
            BeanUtils.copyProperties(temp,reponseDto);

            List<MerchantDto> merchantlist=new ArrayList<>();
            for(int i=0;i<temp.getMerchant().size();i++){

                System.out.println(temp.getMerchant().get(i));
                MerchantDto merchantDto=productFeignService.findById(temp.getMerchant().get(i));
                merchantlist.add(merchantDto);
            }

            reponseDto.setMerchantdto(merchantlist);
            reponseDtos.add(reponseDto);

        }
        return reponseDtos;
    }



    @GetMapping("/productdetail/{pid}/{mid}")


    Product createEntityFromDto(ProductDto productDto){
        Product product=new Product();
        BeanUtils.copyProperties(productDto,product);
        return product;
    }

    ProductDto createDtoFromEntity(Product product)
    {
        ProductDto productDto=new ProductDto();
        BeanUtils.copyProperties(product,productDto);
        return productDto;
    }

}
