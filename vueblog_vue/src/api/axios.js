import axios from "axios"
import Element from "element-ui"
import router from "../router"
import store from "../store";


//设置统一请求路径
axios.defaults.baseURL = "/api"
//前置拦截
axios.interceptors.request.use(config => {
    return config
})

/**
 * 对请求的返回数据进行过滤
 */
axios.interceptors.response.use(response => {
    let res = response.data;

    console.log("=================")
    console.log(res)
    console.log("=================")

    //如果状态码是200，直接放行
    if (res.code === 200) {
        return response
    } else {
        //如果是用户名错误会直接断言处理，不会到达这一步！
        //弹窗提示！
        Element.Message.error('用户名或密码错误！', {duration: 3 * 1000})
        //返回错误信息
        return Promise.reject(response.data.msg)
    }
},
    //如果是非密码错误，会到达这一步
    error => {
        console.log(error)
        //如果返回的数据里面是空
        if (error.response.data){
            error.message = error.response.data.msg;
        }
        //如果状态码是401，
        if (error.response.status === 401){
            store.commit("REMOVE_INFO")
            router.push("/login")
        }

        //弹出错误信息
        Element.Message.error(error.message, {duration: 3 * 1000})
        return Promise.reject(error)
})



