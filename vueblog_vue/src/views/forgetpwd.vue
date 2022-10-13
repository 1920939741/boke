<script src="../main.js"></script>
<Particles></Particles>
<template>
    <div>
      <Particles></Particles>
      <div class="forgetpwe">
        <div class="myForgetpwe" align="center" v-show="sendemailDiv">
          <h4>boke帐号安全验证</h4>
          <div>
            <h5>为确认身份，我们仍需验证您的安全邮箱</h5>
            <p>点击发送邮件按钮，将会发送一封有验证码的邮件至邮箱</p> 
            <span>${this.$route,query.email;}</span>
          </div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" >
            <el-form-item style="margin-top: 30px">
              <el-button type="primary" class="submitBtn" @click="sendEmail()">发送邮箱</el-button>
            </el-form-item>
          </el-form>
        </div>


    <div class="myForgetpEmail" align="center" v-show="emailConfirm">
          <h4>boke帐号安全验证</h4>
          <div>
            <p>请使用安全邮箱317***@qq.com获取验证码</p>
          </div>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="0px" >
            <el-form-item label="" prop="eamilCode" style="margin-top: 10px;">
                 <el-input  placeholder="请输入邮箱验证码"   class="email-input" v-model="ruleForm.email">
                    <el-button slot="append">重新发送<template>{{this.second}}</template>s</el-button>
                 </el-input>
              <!-- <el-row>
                <el-col :span="15">
                 
                </el-col>
              </el-row> -->
            </el-form-item>
            <el-form-item style="margin-top: 30px">
              <el-button type="primary" class="submitBtn" @click="sendEmail()">确定</el-button>
            </el-form-item>
          </el-form>
        </div>

      </div>
    </div>
</template>

<script>
    import Particles from '@/components/particles/index'
    export default {
        components: {Particles},
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    email:'',
                    eamilCode:'',
                },
                second:60,
                canClick: true,
                emailConfirm: false,
                sendemailDiv: true, 
                rules: {
                    username: [
                        {required: true, message: '请输入胡用户名！', trigger: 'blur'},
                        {min: 3, max: 15, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码！', trigger: 'blur'}
                    ],
                    email: [
                      {required: true,message: '请输入邮箱!', trigger: 'blur'},
                      { type: 'email', message: '请输入正确的邮箱地址', trigger: ['blur', 'change'] }
                    ],
                    eamilCode:[
                        {required: true, message: '请输入验证码！', trigger: 'blur'}
                    ],
                }
            };
        },

        methods: {

            /**提交表单**/
            async submitForm(formName) {
                this.$refs[formName].validate((valid) => {
                    if (valid) {
                        // alert('submit!');
                        //提交登录信息
                        //获取到当前的this对象
                        const _this = this;
                        this.$axios.post("/login", this.ruleForm).then(res => {

                            console.log(res.data)
                            const jwt = res.headers["authorization"]
                            if (jwt === null){
                                this.$alert('用户名或密码错误！！', '提示', {
                                    confirmButtonText: '确定',
                                    callback: action => {
                                        // _this.$router.push("/blogs")
                                    }
                                });
                            }else {
                                const userInfo = res.data.data
                                console.log(jwt)
                                console.log(userInfo)

                                //把数据共享出去
                                _this.$store.commit("SET_TOKEN", jwt);
                                _this.$store.commit("SET_USERINFO", userInfo);

                                //获取
                                console.log(_this.$store.getters.getUser)
                                //页面跳转
                                _this.$router.push("/blogs")
                            }


                        });
                    } else {
                        console.log('error submit!!');
                        return false;
                    }
                });
            },
            resetForm(formName) {
                this.$refs[formName].resetFields();
            },
            sendEmail(){
                this.sendemailDiv = false;
                this.emailConfirm = true;
                // let time = window.setInterval(function(){
                //     this.second--;
                //     if(this.second == 0){
                //         window.clearInterval(time);
                //     }
                // },1000);
                let clock = window.setInterval(() => {
                    this.second--;
                    this.content = this.totalTime + "s后重新发送";
                    if (this.second < 0) {
                        //当倒计时小于0时清除定时器
                        window.clearInterval(clock); //关闭
                        this.second = 60;
                        this.canClick = true; //这里重新开启
                    }
             }, 1000);

            }
        }
    }


</script>

<style scoped>
  .forgetpwe {

    padding: 0;
    margin: 0;
    height: 100vh;
    font-size: 16px;
    background-position: left top;

    color: #fff;
    font-family: "Source Sans Pro";
    position: relative;
  }

  .myForgetpwe,.myForgetpEmail {
    width: 450px;
    height: 300px;
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    margin: auto;
    padding: 50px 40px 40px 40px;
    box-shadow: -15px 15px 15px rgba(6, 17, 47, 0.7);
    opacity: 1;
    background: linear-gradient(
        230deg,
        rgba(53, 57, 74, 0) 0%,
        rgb(0, 0, 0) 100%
    );
  }
  /* .email-input{
    width: 320px;
  } */
  .inps input {
    border: none;
    color: #fff;
    background-color: transparent;
    font-size: 12px;
  }

  .submitBtn {
    background-color: transparent;
    color: #39f;
    width: 200px;
  }

</style>

