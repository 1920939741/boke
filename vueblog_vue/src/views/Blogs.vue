<template>
    <div>
        <Header></Header>
        <div class="block">
            <el-timeline>
                <el-timeline-item :timestamp="blog.created" placement="top" v-for="blog in blogs">
                    <el-card>
                        <router-link :to="{name:'BlogDetail',params:{blogId:blog.id}}">
                            <h4>{{blog.title}}</h4>
                        </router-link>
                        <p>{{blog.description}}</p>
                    </el-card>
                </el-timeline-item>


            </el-timeline>
        </div>
        <el-pagination class="mpage"
                       background
                       layout="prev, pager, next"
                       :current-page="currentPage"
                       :page-size="pageSize"
                       :total="total"
                       @current-change=page
        >
        </el-pagination>
    </div>
</template>

<script>

    //导入公共的Header
    import Header from "../components/Header";

    export default {
        name: "Blogs",
        //将Header注册进去
        components: {Header},
        //返回的数据
        data() {
            return {
                blogs: {},
                currentPage: 1,  //当前页
                total: 0,    //总共多少页
                pageSize: 5  //每一页的数据个数
            }
        },
        mounted() {
        },
        methods: {
            //请求指定页的方法
            page(currentPage) {
                const _this = this;
                _this.$axios.get("/blogs?currentPage=" + currentPage,{
                    headers: {
                        "authorization": localStorage.getItem("token")
                    }
                }).then(res => {
                    console.log(res)
                    //从获取到的数据中进行赋值
                    _this.blogs = res.data.data.records
                    _this.currentPage = res.data.data.current
                    _this.total = res.data.data.total
                    _this.pageSize = res.data.data.size
                })
            }
        },
        created() {
            this.page(1)
        }
    }
</script>

<style scoped>
    .mpage {
        margin: 0 auto;
        text-align: center;
    }
</style>