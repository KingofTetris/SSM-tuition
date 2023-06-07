package com.atguigu.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author by KingOfTetris
 * @date 2023/5/31
 */

@Controller
//@RequestMapping("/test") //加到类上就是多了一层路径
/**
 * 1.@RequestMapping标识一个类：设置映射请求的请求路径的初始信息
 * @RequestMapping标识一个方法：设置映射请求请求路径的具体信息
 * 以后类里面具体的控制方法基本都差不多，
 * 如果在类上不加上初始信息,你的命名就比较麻烦了。
 *
 * 2.@RequestMapping注解的value属性通过请求的请求地址匹配请求映射
 * @RequestMapping注解的value属性是一个字符串类型的数组，表示该请求映射能够匹配多个请求地址
 * 所对应的请求
 * @RequestMapping注解的value属性必须设置，至少通过请求地址匹配请求映射
 *
 * 3.@RequestMapping注解的method属性通过请求的请求方式（get或post）匹配请求映射
 * @RequestMapping注解的method属性是一个RequestMethod类型的数组，表示该请求映射能够匹配
 * 多种请求方式的请求
 * 若当前请求的请求地址满足请求映射的value属性，但是请求方式不满足method属性，则浏览器报错
 * 405：Request method 'POST' not supported
 * 注：
 * ①、对于处理指定请求方式的控制器方法，SpringMVC中提供了@RequestMapping的派生注解
 * 处理get请求的映射-->@GetMapping
 * 处理post请求的映射-->@PostMapping
 * 处理put请求的映射-->@PutMapping
 * 处理delete请求的映射-->@DeleteMapping
 * ②、常用的请求方式有get，post，put，delete
 * 但是目前浏览器只支持get和post，若在form表单提交时，为method设置了其他请求方式的字符
 * 串（put或delete），则按照默认的请求方式get处理
 * 若要发送put和delete请求，则需要通过spring提供的过滤器HiddenHttpMethodFilter，在
 * RESTful部分会讲到
 *
 * 4、@RequestMapping注解的params属性通过请求的请求参数匹配请求映射
 * @RequestMapping注解的params属性是一个字符串类型的数组，可以通过四种表达式设置请求参数
 * 和请求映射的匹配关系
 * "param"：要求请求映射所匹配的请求必须携带param中的所有请求参数
 * "!param"：一定不能携带param
 * "param=value":可以不传，但是如果你传了必须等于value
 * "param!=value"：可以不传，但是如果你传了必须不等于value
 * 一般不会通过parame 参数来映射请求，所以知道就行了。
 * 对应不到参数报400
 *
 * 5.@RequestMapping注解的headers属性通过请求的请求头信息匹配请求映射
 * @RequestMapping注解的headers属性是一个字符串类型的数组，可以通过四种表达式设置请求头信
 * 息和请求映射的匹配关系
 * 用法和param用法一模一样。
 * "header"：要求请求映射所匹配的请求必须携带header请求头信息
 * "!header"：要求请求映射所匹配的请求必须不能携带header请求头信息
 * "header=value"：要求请求映射所匹配的请求必须携带header请求头信息且header=value
 * "header!=value"：要求请求映射所匹配的请求必须携带header请求头信息且header!=value
 * 若当前请求满足@RequestMapping注解的value和method属性，但是不满足headers属性，此时页面
 * 显示404错误，即资源未找到
 *
 * 7.@RequestMapping使用占位符
 * 原始方式：/deleteUser?id=1
 * rest方式：/user/delete/1
 * 在路径中用  占位符{id}配合@PathVariable("id") Integer id 进行绑定就行了。
 */
public class TestRequestMappingController {


    //RequestMapping底层values是一个数组，所以可以对应多个请求
    //实际上就等于在web.xml设置多个
    /**
     * 比如
     * <servlet-mapping>
     *         <servlet-name>SpringMVC</servlet-name>
     *         <url-pattern>/aa</url-pattern>
     *         <url-pattern>/abc</url-pattern>
     *     </servlet-mapping>
     */

   /* @RequestMapping(value = {"/hello","/abc"},
            method = {RequestMethod.GET,RequestMethod.POST},
            params = {"username","!password","age=20","gender!=女"},
            headers = {"referer"})
    public String hello(){
        return "success";
    }*/

    @RequestMapping(value = {"/hello","/abc"},
            method = {RequestMethod.GET,RequestMethod.POST})
    public String hello(){
        return "success";
    }



    //路径里面最好不要有大写，大写最好用/路径分隔符
//     SpringMVC支持ant风格的路径
//     ？：表示任意的单个字符 但是有些特殊符号不行,比如路径中特有的符号"/","?"
//     *：表示任意的0个或多个字符 但是有些特殊符号不行,比如路径中特有的符号"/","?"
//     **：表示任意层数的任意目录 可以表示"/"
//     注意：在使用**时，只能使用/**/的方式  也就是**左右不能有任何字符，才能被解析成**
    @RequestMapping("/a?c/test/ant")
    public String testAnt(){
        return "success";
    }


    //测试RESTful风格路径
    //REST Representational State Transfer，表现层资源状态转移。
    //服务器中的一切皆资源 所以你给资源配上标志也就是路径就能访问了。
    //因为访问的路径都是一样的
    //我们使用增(保存)删改(更新)查来操作资源 post delete put get
    //占位符{id}配合@PathVariable("id")
    @RequestMapping("/test/rest/{username}/{id}")
    public String testRest(@PathVariable("id") Integer id,@PathVariable("username") String username){
        System.out.println("id:" + id);//SpringMVC可以自行转换字符串为某些类型
        System.out.println("username:" + username);
        return "success";
    }
}
