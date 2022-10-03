<template>
    <el-dialog
            :title="title"
            :visible.sync="centerDialogVisible"
            width="30%"
            center>
        <span slot="footer" class="dialog-footer">
    <el-button @click="centerDialogVisible = false">取 消</el-button>
    <el-button type="primary" @click="centerDialogVisible = false">确 定</el-button>
  </span>
    </el-dialog>
</template>

<script>
    export default {
        name: "messageModel",
        props:{
            centerDialogVisible:{
                type: Boolean
            },
            title:{
                type: String
            }
        },
        data() {
            return {
            };
        }
    }
</script>

<style scoped>
    /deep/.el-dialog__body{
        padding: 0 !important
    }
</style>