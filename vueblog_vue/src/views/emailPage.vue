<script src="../main.js"></script>
<Particles></Particles>
<template>
  <div>
    <Particles></Particles>
    <div class="emailPage">
      <div class="myEmailPage" align="center">
        <h4>QQ邮箱验证</h4>

        <el-form :model="emailForm" :rules="rules" ref="ruleForm" label-width="0px" >
          <el-form-item prop="username" style="margin-top: 50px">
            <el-row aria>
              <el-col :span="2">
                <span class="el-icon-s-custom"></span>
              </el-col>
              <el-col :span="22">
                <el-input  placeholder="QQ邮箱"   class="inps" v-model="email" @blur="email_blur"></el-input>
              </el-col>
            </el-row>
          </el-form-item>
          <el-form-item style="margin-top: 50px">
            <el-button type="primary" class="submitBtn" @click="sendEmail()">确定</el-button>
          </el-form-item>
        </el-form>
      </div>
      </div>
    </div>

</template>

<script>
  import Particles from "@/components/particles/index";

  export default {
    components: {Particles},
    name: "emailPage",
    data(){
      return {
        emailForm:{
          email:"",
        },
        rules: {
          username: [
            {required: true, message: 'QQ邮箱号！', trigger: 'blur'},
          ],
        }
      }
    },
   methods: {
    email_blur() {
      var verify = /^[A-Za-z0-9\u4e00-\u9fa5]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
      if (!verify.test(this.email)) {
        this.message = '邮箱格式错误, 请重新输入'
      } else {
        this.message = '可以请求接口了'
      }
    },

    }
  }
</script>

<style scoped>
.emailPage {

  padding: 0;
  margin: 0;
  height: 100vh;
  font-size: 16px;
  background-position: left top;

  color: #fff;
  font-family: "Source Sans Pro";
  position: relative;
}

.myEmailPage {
  width: 240px;
  height: 280px;
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
</style>