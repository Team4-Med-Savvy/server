package com.example.product.controller;


import com.example.product.RequestDto.RequestDto;
import com.example.product.dto.*;
import com.example.product.entity.Product;
import com.example.product.service.ProductService;
import com.example.product.service.feignservice.ProductFeignService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductFeignService productFeignService;

    @GetMapping(value="/{id}")
    private Product select(@PathVariable(value = "id") String id){
        return productService.select(id);
    }

    @RequestMapping(method ={RequestMethod.POST,RequestMethod.PUT})
    void save(@RequestBody RequestDto productDto){
        Product product=new Product();
        product.setCategory(productDto.getCategory());
        product.setDescription(productDto.getDescription());
        product.setImage(productDto.getImage());
        product.setTitle(productDto.getTitle());
        List<String> merchant=new ArrayList<>();
        merchant.add(productDto.getMerchantId());
        product.setPrice(productDto.getPrice());
        product.setMerchant(merchant);
        productService.save(product);
        saveMerchant(product,productDto);
    }

    private void saveMerchant(Product product,RequestDto productDto){
        Product savedProduct=productService.findByTitle(product.getTitle());
        MerchantProductDto merchantProductDto = new MerchantProductDto(Math.round(productDto.getPrice()),productDto.getStock(),savedProduct.getId(),productDto.getMerchantId());
        productFeignService.save(merchantProductDto);
    }

    @PostMapping(value = "/edit/{pid}/{mid}")
    void editMerchantList(@PathVariable(name = "pid") String pid,@PathVariable(name = "mid") String mid){
        Product product=productService.select(pid);
        product.getMerchant().add(mid);
        productService.save(product);
    }

    @DeleteMapping(value = "/{id}")
    void delete(@PathVariable(value = "id") String id){
        productService.delete(id);

    }

    @GetMapping(value = "/recommend")
    List<ReponseDto> recommend()
    {
        List<ReponseDto> productDtos=new ArrayList<>();
        String cate[]={"Covid_essential","Surgicals","Skin_care","Personal_care","Ayurvedic_care"};

        for(int i=0;i<5;i++)
        {
            List<ReponseDto> listprod=findProduct(cate[i]);
            int size=listprod.size();

            if(size>0){

            for(int j=0;j<2;j++){
            Random rand = new Random();
                int rand_int = rand.nextInt(size);

            ReponseDto productDto=new ReponseDto();
            BeanUtils.copyProperties(listprod.get(rand_int),productDto);
            productDtos.add(productDto);

            }
            }
        }
        return  productDtos;
    }


    @GetMapping(value = "/findlist/{id}")
    List<ReponseDto> findProduct(@PathVariable(value = "id") String id)
    {
//        List<ProductDto> productDtos=new ArrayList<>();
        List<ReponseDto> reponseDtos=new ArrayList<>();


        Iterable<Product> products=productService.findProduct(id);

        for(Product temp:products)
        {
//            productDtos.add(createDtoFromEntity(temp));

            ReponseDto reponseDto=new ReponseDto();
            BeanUtils.copyProperties(temp,reponseDto);

            List<MerchantDto> merchantlist=new ArrayList<>();
            for(int i=0;i<temp.getMerchant().size();i++){

               // System.out.println(temp.getMerchant().get(i));
                MerchantDto merchantDto=productFeignService.findById(temp.getMerchant().get(i));
                merchantlist.add(merchantDto);
            }

            reponseDto.setMerchantdto(merchantlist);
            reponseDtos.add(reponseDto);

        }
        return reponseDtos;
    }



    @GetMapping("/productdetail/{pid}/{mid}")
    ProductDetailDto finddetail(@PathVariable(value = "pid") String pid,@PathVariable(value = "mid") String mid){

        ResponseMerchantDto response=productFeignService.findProductPrice(pid,mid);

        ProductDetailDto productDetailDto=new ProductDetailDto();
        ProductDto product=createDtoFromEntity(select(pid));
        productDetailDto.setName(product.getTitle());
        productDetailDto.setImageUrl(product.getImage());
        productDetailDto.setDescription(product.getDescription());
        productDetailDto.setPrice(response.getPrice());
        productDetailDto.setMerchantStock(response.getStock());


        return  productDetailDto;

    }


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
