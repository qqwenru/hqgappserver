package com.hqg.api.controller;

import com.hqg.api.bean.Customer;
import com.hqg.api.bean.ResponseBean;
import com.hqg.api.exception.UnauthorizedException;
import com.hqg.api.repository.CustomerRepository;
import com.hqg.api.sms.SMS;
import com.hqg.api.sms.SingleSMSResult;
import com.hqg.api.sms.Tel;
import com.hqg.api.sms.TempletSMSParam;
import com.hqg.api.util.JWTUtil;
import com.hqg.api.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@RestController
public class LoginController {

    @Autowired
    private CustomerRepository customerRepository;
    @Autowired
    private RedisTemplate<String, String> redisTemplate;
    /**
     * 用户注册
     */
    @PostMapping(value = "/registered")
    public ResponseBean registered(@RequestBody Customer customer) throws Exception {
        String regex = "^(13[0-9]|14[57]|15[012356789]|17[03678]|18[0-9])\\d{8}$";
        Pattern p = Pattern.compile(regex);
        Matcher m = p.matcher(customer.getMobile());
        boolean isMatch = m.matches();
        if(isMatch){
            Customer cu = customerRepository.findByMobile(customer.getMobile());
            if (cu == null) {
                //String session = request.getSession().getAttribute(mobile).toString();
                if(redisTemplate.hasKey(customer.getMobile())){
                    String redisCode = redisTemplate.opsForValue().get(customer.getMobile());
                    redisTemplate.delete(customer.getMobile());//根据key删除缓存
                    if (customer.getCode().equalsIgnoreCase(redisCode)) {
                        Customer c = new Customer();
                        c.setMobile(customer.getMobile());
                        c.setPassword(MD5Util.md5(customer.getPassword()));
                        c.setState(0);
                        customerRepository.save(c);
                        return new ResponseBean(200, "注册成功", "");
                    } else {
                        return new ResponseBean(400, "输入的验证码有误", "");
                    }
                }else{
                    return new ResponseBean(400, "未发送验证码", "");
                }
            } else {
                return new ResponseBean(400, "此用户已经注册", "");
            }
        } else {
            return new ResponseBean(400, "您输入的手机号码有误", "");
        }

    }


    /**
     * 单条短信模板
     */
    @PostMapping(value = "/sendTempSMS")
    public ResponseBean sendTempSMS(@RequestBody Tel tel) throws Exception {
        if (tel != null) {
            SMS sms = new SMS("1400069266", "ddbc6de934261b0f2593cb7128fd9fbd", "https://yun.tim.qq.com/v5/tlssmssvr/sendsms", "https://yun.tim.qq.com/v5/tlssmssvr/sendmultisms2");
            TempletSMSParam param = new TempletSMSParam();
            List<String> list = new ArrayList<>();
            String i = Integer.toString((int) ((Math.random() * 9 + 1) * 100000));
            // 放入缓存，并设置缓存时间
            String StringTel = tel.getMobile();
            redisTemplate.opsForValue().set(StringTel, i,60*2, TimeUnit.SECONDS);//向redis里存入数据和设置缓存时间
            list.add(i);
            tel.setNationCode("86");
            param.setTel(tel);
            param.setTpl_id(88584);
            SingleSMSResult ssr = sms.sendTemplateSMS(param, list);
            return new ResponseBean(200, "success", ssr);
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 登录
     */
    @PostMapping(value = "/login")
    public ResponseBean login(@RequestBody Customer customer) {
        if (null != customer) {
            Customer cu = customerRepository.findByMobileAndPassword(customer.getMobile(), MD5Util.md5(customer.getPassword()));
            Map<String, Object> cuToSql = customerRepository.findByUsernameAndAndPasswordToSql(customer.getMobile(),
                    MD5Util.md5(customer.getPassword()));
            if (cu != null) {
                String token = JWTUtil.sign(cu.getMobile(), cu.getPassword());
                Map<String, Object> map = new HashMap<>();
                map.put("token", token);
                map.put("user",cuToSql);
                return new ResponseBean(200, "登录成功", map);
            } else {
                return new ResponseBean(400, "未注册或者输入的密码错误", "");
            }
        } else {
            throw new UnauthorizedException();
        }
    }

    /**
     * 修改密码
     */
    @PostMapping("/editPassword")
    public ResponseBean editPassword(@RequestBody Customer customer) throws Exception {
        Customer cu = customerRepository.findByMobile(customer.getMobile());
        if (cu == null) {
            return new ResponseBean(400, "此用户未注册", "");
        } else {
            if (redisTemplate.hasKey(customer.getMobile())) {//检查key是否存在，返回boolean值
                String redisCode = redisTemplate.opsForValue().get(customer.getMobile());////根据key获取缓存中的val
                redisTemplate.delete(customer.getMobile());//根据key删除缓存
                if (customer.getCode().equalsIgnoreCase(redisCode)) {
                    cu.setPassword(MD5Util.md5(customer.getPassword()));
                    customerRepository.saveAndFlush(cu);
                    return new ResponseBean(200, "密码修改成功", "");
                } else {
                    return new ResponseBean(200, "输入的验证码有误", "");
                }
            } else {
                return new ResponseBean(400, "未发送验证码", "");
            }
        }
    }
}
