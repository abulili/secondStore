<template>
    <div>
        收不到短信验证码的方式（因为要收费，幸好要收费）
        <!-- 所有方案失败时的备用方案 （差不多算申诉吧） -->
        <el-card>
            <el-form label-position="left" class="demo-form-inline" :inline="true">
                <el-form-item label="昵称" label-width="60px">
                    <el-input :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="手机" label-width="60px">
                    <el-input :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" label-width="60px">
                    <el-input :disabled="true" placeholder="aaa"></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-button>去解决</el-button>
                </el-form-item>
            </el-form>
        </el-card>
        <el-card>
            <el-form label-position="left" class="demo-form-inline" :inline="true">
                <el-form-item label="昵称" label-width="60px">
                    <el-input :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="手机" label-width="60px">
                    <el-input :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="邮箱" label-width="60px">
                    <el-input :disabled="true"></el-input>
                </el-form-item>
                <el-form-item label="">
                    <el-button>去解决</el-button>
                </el-form-item>
            </el-form>
        </el-card>
    </div>
</template>

<script>

</script>

<style lang="scss"></style>