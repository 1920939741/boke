<script src="../main.js"></script>
<template>
    <div>
        <el-container>
            <el-header>
                <img class="mlogo" src="../assets/hxy.png">
            </el-header>

            <!--登录表单-->
            <el-main>
                <el-form :model="ruleForm" :rules="rules" ref="ruleForm" label-width="100px" class="demo-ruleForm">
                    <el-form-item label="用户名：" prop="username">
                        <el-input v-model="ruleForm.username"></el-input>
                    </el-form-item>

                    <el-form-item label="密码：" prop="password">
                        <el-input type="password" v- v-model="ruleForm.password"></el-input>
                    </el-form-item>

                    <el-form-item>
                        <el-button type="primary" @click="submitForm('ruleForm')">立即登录</el-button>
                        <el-button @click="resetForm('ruleForm')">重置</el-button>
                    </el-form-item>
                </el-form>
            </el-main>

        </el-container>
    </div>
</template>

<script>
    export default {
        name: "Login",
        data() {
            return {
                ruleForm: {
                    username: '',
                    password: '',
                },
                rules: {
                    username: [
                        {required: true, message: '请输入胡用户名！', trigger: 'blur'},
                        {min: 3, max: 5, message: '长度在 3 到 15 个字符', trigger: 'blur'}
                    ],
                    password: [
                        {required: true, message: '请输入密码！', trigger: 'blur'}
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
            }
        }
    }


</script>

<style scoped>
    .el-header, .el-footer {
        /*background-color: #B3C0D1;*/
        color: #333;
        text-align: center;
        /*line-height: 600px;*/
        height: 100px !important;
    }

    .el-aside {
        background-color: #D3DCE6;
        color: #333;
        text-align: center;
        line-height: 200px;
    }

    .el-main {
        /*background-color: #E9EEF3;*/
        color: #333;
        text-align: center;
        line-height: 160px;
    }

    body > .el-container {
        margin-bottom: 40px;
    }

    .el-container:nth-child(5) .el-aside,
    .el-container:nth-child(6) .el-aside {
        line-height: 260px;
    }

    .el-container:nth-child(7) .el-aside {
        line-height: 320px;
    }

    .mlogo {
        height: 100px;
    }

    .demo-ruleForm {
        max-width: 400px;
        margin: 0 auto;
    }

</style>

