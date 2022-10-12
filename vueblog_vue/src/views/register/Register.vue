<script src="../main.js"></script>
<Particles></Particles>
<template>
    <div>
      <Particles></Particles>
      <div class="register">
        <div class="myRegister" align="center">
          <h4>注册</h4>
          <el-form :model="ruleForm" :rules="rules" ref="ruleForm">
             <el-form-item label="账号" prop="username" style="margin-top: 10px">
              <el-col :span="18">
                  <!-- v-on:mouseleave.native="handleMouseleave()"   -->
                <el-input 
                  placeholder="请输入账号"    
                  @blur="onInputBlur"
                  class="inps" 
                  v-model="ruleForm.username">
                </el-input>
              </el-col>
            </el-form-item>
            <el-form-item label="姓名" prop="name" style="margin-top: 10px">
              <el-col :span="18">
                <el-input  placeholder="请输入姓名"   class="inps" v-model="ruleForm.name"></el-input>
              </el-col>
            </el-form-item>
           <el-form-item label="邮箱" prop="email" style="margin-top: 10px">
              <el-col :span="18">
                <el-input  placeholder="请输入邮箱"   class="inps" v-model="ruleForm.name"></el-input>
              </el-col>
            </el-form-item>
            <!-- <el-form-item label="" prop="passWord">
              <el-row>
                <el-col :span="2">
                  <span class="el-icon-lock"></span>
                </el-col>
                <el-col :span="22">
                  <el-input type="password"  class="inps" placeholder="密码" v-model="ruleForm.password"></el-input>
                </el-col>
              </el-row>
            </el-form-item> -->
            <el-form-item style="margin-top: 20px ">
              <el-button type="primary" class="submitBtn" @click="submitForm('ruleForm')">注册</el-button>
            </el-form-item>
            <!-- <div class="unlogin">
              <router-link :to="{ path: '/forgetpwd' }"> 忘记密码? </router-link>
              |
              <router-link :to="{ path: '/register' }">
                <a href="./register.vue" target="_blank" align="right">注册新账号</a>
              </router-link>
            </div> -->
          </el-form>
        </div>
      </div>

    </div>
</template>

<script>
    import Particles from '@/components/particles/index'
    export default {
        components: {Particles},
        name: "Register",
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                    name:'',
                    email:'',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入胡用户名！', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 15 个字符', trigger: 'blur'}, 
                    ],
                    password: [
                        {required: true, message: '请输入密码！', trigger: 'blur'}
                    ],
                    name: [
                      {required: true,message: '请输入姓名!', trigger: 'blur'}
                    ],
                    email: [
                      {required: true,message: '请输入邮箱!', trigger: 'blur'}
                    ]
                    

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
            // handleFilter(){
            //     alert(11)
            // },
            // handleMouseleave(){
            //   alert(222)
            // },
            onInputBlur(){
              //alert(this.ruleForm.username)
              if(this.ruleForm.username == 'admin'){
                this.$message({
                         type: 'success',
                         message: '用户名已存在'
                       });   
              }
              //根据用户名查询
              // this.$axios.post("/findByUserName", this.ruleForm.username).then(res => {
              //     if(res.data != null){
              //          this.$message({
              //            type: 'success',
              //            message: '用户名已存在'
              //          });      
              //     }
              // });
            }
          
        }
    }


</script>

<style scoped>
  .register {

    padding: 0;
    margin: 0;
    height: 100vh;
    font-size: 16px;
    background-position: left top;

    color: #fff;
    font-family: "Source Sans Pro";
    position: relative;
  }

  .myRegister {
    /* width: 240px;
    height: 280px; */
    width: 310px;
    height: 320px;
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

