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

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    ProductService productService;

    @Autowired
    ProductFeignService productFeignService;

    @GetMapping(value="/{id}")
    private ProductDto select(@PathVariable(value = "id") String id){
        return createDtoFromEntity(productService.select(id));
    }

    //todo : phani : why are you supporting PUT here ? and no logic exists for modifying merchant list here!!
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

    //todo : phani : This should be based on query from Mongo, supports based on query of nested collections
    // need to use limit criterian from the query level itself
    @GetMapping(value = "/recommend")
    private List<ReponseDto> recommend()
    {
        List<ReponseDto> productDtos=new ArrayList<>();
        String cate[]={"Covid_essentials","Surgical","Skin_care","Pet_care","Ayurvedic_care"};

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
                // todo : phani .. instead of firing request to get each merchant, use the bulk api to fetch details by list of mechant ids
                // need to implement findAllById on Merchant service to achieve this
                MerchantDto merchantDto=productFeignService.findById(temp.getMerchant().get(i));
                merchantlist.add(merchantDto);
            }

            reponseDto.setMerchantdto(merchantlist);
            reponseDtos.add(reponseDto);

        }
        return reponseDtos;
    }



    // todo : phani to review this api usage from the mobile / web ui!
    @GetMapping("/productdetail/{pid}/{mid}")
    ProductDetailDto finddetail(@PathVariable(value = "pid") String pid,@PathVariable(value = "mid") String mid){

        ResponseMerchantDto response=productFeignService.findProductPrice(pid,mid);

        ProductDetailDto productDetailDto=new ProductDetailDto();
        ProductDto product=select(pid);
        productDetailDto.setName(product.getTitle());
        productDetailDto.setImageUrl(product.getImage());
        productDetailDto.setDescription(product.getDescription());
        productDetailDto.setPrice(response.getPrice());
        productDetailDto.setMerchantStock(response.getStock());


        return  productDetailDto;

    }


    @GetMapping("/productdetaillist/{pid}")
    ResponseProductDto findmerchantlist(@PathVariable(value = "pid") String pid){

        ProductDto productDto=select(pid);
        List<String> merchids=productDto.getMerchant();
        ResponseProductDto responseProductDto=new ResponseProductDto();
        responseProductDto.setName(productDto.getTitle());
        responseProductDto.setDescription(productDto.getDescription());
        responseProductDto.setImageUrl(productDto.getImage());

        List<MerchantProductDetailDto> merchlist=new ArrayList<>();


        for(int i=0;i<merchids.size();i++)
        {
            ProductDetailDto productDetailDto=finddetail(pid,merchids.get(i));
           // System.out.println(merchids.get(i));
            MerchantProductDetailDto merchprod=new MerchantProductDetailDto();
            merchprod.setMerchantId(merchids.get(i));
//            merchprod.setMerchantName(productDetailDto.g);
            merchprod.setPrice(productDetailDto.getPrice());
            merchprod.setStock(productDetailDto.getMerchantStock());

            merchlist.add(merchprod);

        }
       responseProductDto.setMerchantProductDetailDtos(merchlist);
        return  responseProductDto;

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
