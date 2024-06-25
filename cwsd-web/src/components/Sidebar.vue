<template>
    <div class="sidebar">

    </div>
</template>

<script>

export default {
    setup() {
        const items = [
            {
                icon: "el-icon-lx-home",
                index: "/dashboard",
                title: "系统首页",
            },
            {
                icon: "el-icon-lx-cascades",
                index: "/table",
                title: "基础表格",
            },
            {
                icon: "el-icon-lx-copy",
                index: "/tabs",
                title: "tab选项卡",
            },
            {
                icon: "el-icon-lx-calendar",
                index: "3",
                title: "表单相关",
                subs: [
                    {
                        index: "/form",
                        title: "基本表单",
                    },
                    {
                        index: "/upload",
                        title: "文件上传",
                    },
                    {
                        index: "4",
                        title: "三级菜单",
                        subs: [
                            {
                                index: "/editor",
                                title: "富文本编辑器",
                            },
                        ],
                    },
                ],
            },
            {
                icon: "el-icon-lx-emoji",
                index: "/icon",
                title: "自定义图标",
            },
            {
                icon: "el-icon-pie-chart",
                index: "/charts",
                title: "schart图表",
            },
            {
                icon: "el-icon-lx-global",
                index: "/i18n",
                title: "国际化功能",
            },
            {
                icon: "el-icon-lx-warn",
                index: "7",
                title: "错误处理",
                subs: [
                    {
                        index: "/permission",
                        title: "权限测试",
                    },
                    {
                        index: "/404",
                        title: "404页面",
                    },
                ],
            },
            {
                icon: "el-icon-lx-redpacket_fill",
                index: "/donate",
                title: "支持作者",
            },
        ];


    },
};
</script>

<style scoped>
.sidebar {
    display: block;
    position: absolute;
    left: 0;
    top: 70px;
    bottom: 0;
    overflow-y: scroll;
}
.sidebar::-webkit-scrollbar {
    width: 0;
}
.sidebar-el-menu:not(.el-menu--collapse) {
    width: 250px;
}
.sidebar > ul {
    height: 100%;
}
</style>
