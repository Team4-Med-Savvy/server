package com.example.userauth.controllers;
import com.example.userauth.dto.AuthDto;
import com.example.userauth.dto.MerchantDto;
import com.example.userauth.dto.ResponseDto;
import com.example.userauth.dto.UserDto;
import com.example.userauth.entity.User;
import com.example.userauth.services.UserServices;
import com.example.userauth.services.feignServices.MerchantFeignService;
import com.example.userauth.utils.JwtUtil;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "http://localhost:8080", maxAge = 3600)
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserServices userServices;

    @Autowired
    private MerchantFeignService merchantFeignService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

//    @GetMapping()
//    public ResponseDto getUserData(@PathVariable String email){
//        User user = userServices.findByUsername(email);
//        ResponseDto response=new ResponseDto();
//        response.setId(user.getId());
//        response.setName(user.getName());
//        response.setMerchant(user.getMerchant());
//        response.setPoints(user.getPoints());
//        return response;
//    }

    @GetMapping("/getuser/{id}")
    public UserDto getUser(@PathVariable(name = "id") String id){
        User user=userServices.select(id);
        UserDto userDto=new UserDto();
        BeanUtils.copyProperties(user,userDto);
        return userDto;
    }
    @PostMapping("/authenticate")
    public ResponseDto generateToken(@RequestBody AuthDto authDto) throws Exception {
        User user;
        try{
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(authDto.getUsername(),authDto.getPassword())
        );
        }
        catch (Exception e){
            throw new Exception("Invalid username/password");
        }
        user = userServices.findByUsername(authDto.getUsername());
        ResponseDto res= new ResponseDto();
        res.setToken(jwtUtil.generateToken(user.getEmail()));
        res.setPoints(user.getPoints());
        res.setMerchant(user.getMerchant());
        res.setName(user.getName());
        res.setId(user.getId());
        res.setEmail(user.getEmail());
        return res;
    }


    @PostMapping("/register")
    void save(@RequestBody UserDto userDto){
        User user=copyFromDTO(userDto);
        userServices.save(user);
        MerchantDto merchant=new MerchantDto(userServices.findByUsername(user.getEmail()).getId(),userDto.getName(),0,0);
        merchantFeignService.save(merchant);

    }

    private User copyFromDTO(UserDto userDto){
        User user = new User();
        user.setName(userDto.getName());
        user.setMerchant(userDto.getMerchant());
        user.setUsername(userDto.getEmail());
        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        user.setPoints(userDto.getPoints());
        user.setEmail(userDto.getEmail());
        return user;
    }

}
