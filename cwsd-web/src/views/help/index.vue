<template>
    <div class="user-manual">
        <h1>智慧工地安全监测平台用户手册</h1>
        <hr>
        <h2>目录</h2>
        <ul>
            <li><a href="#platform-overview">一．平台概述</a></li>
            <li><a href="#system-requirements">二．系统要求</a></li>
            <li><a href="#features">三．功能介绍</a></li>
            <ul>
                <li><a href="#p1">1.登录页面：</a></li>
                <li><a href="#p2">2.新增账号:</a></li>
                <li><a href="#p3">3.修改账号信息：</a></li>
                <li><a href="#p4">4.新传感器添加：</a></li>
                <li><a href="#p5">5.传感器分区显示和编辑传感器信息功能：</a></li>
                <li><a href="#p6">6.新监控设备添加功能：</a></li>
                <li><a href="#p7">7.监控设备页其他功能：</a></li>
                <li><a href="#p8">8.新消息通知：</a></li>
                <li><a href="#p9">9.消息通知页其他功能：</a></li>
                <li><a href="#p10">10.新区域添加：</a></li>
                <li><a href="#p11">11.主页添加想显示的传感器或监控:</a></li>
            </ul>
            <li><a href="#technical-support">四．技术支持</a></li>

        </ul>

        <hr>
        <h2 id="platform-overview">一．平台概述</h2>
        <p>智慧工地安全监测平台是一个综合性的监控解决方案，旨在提高施工现场的安全管理水平。通过集成先进的传感器技术、实时数据分析和智能通知系统，该平台能够为工地管理人员提供全面、实时的监控和报警服务。</p>

        <h2 id="system-requirements">二．系统要求</h2>
        <p>支持所有主流浏览器，包括Chrome、Firefox、Safari和Edge。</p>
        <h2 id="features">三．功能介绍</h2>

        <h3 id="p1">1.登录页面：</h3>
        <img src="@/assets/img/help/01.png" style="width: 75%; height: 75%">
        <h3 id="p2">2.新增账号:</h3>
        <img src="@/assets/img/help/02.png" style="width: 75%; height: 75%">
        <h3 id="p3">3.修改账号信息：</h3>
        <img src="@/assets/img/help/03.png" style="width: 75%; height: 75%">
        <h3 id="p4">4.新传感器添加：</h3>
        <img src="@/assets/img/help/04.png" style="width: 75%; height: 75%">
        <h3 id="p5">5.传感器分区显示和编辑传感器信息功能：</h3>
        <img src="@/assets/img/help/05.png" style="width: 75%; height: 75%">
        <h3 id="p6">6.新监控设备添加功能：</h3>
        <img src="@/assets/img/help/06.png" style="width: 75%; height: 75%">
        <h3 id="p7">7.监控设备页其他功能：</h3>
        <img src="@/assets/img/help/07.png" style="width: 75%; height: 75%">
        <h3 id="p8">8.新消息通知：</h3>
        <img src="@/assets/img/help/08.png" style="width: 75%; height: 75%">
        <h3 id="p9">9.消息通知页其他功能：</h3>
        <img src="@/assets/img/help/090.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/091.png" style="width: 75%; height: 75%">

        <h3 id="p10">10.新区域添加：</h3>
        <img src="@/assets/img/help/100.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/101.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/102.png" style="width: 75%; height: 75%">

        <h3 id="p11">11.主页添加想显示的传感器或监控:</h3>
        <img src="@/assets/img/help/110.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/111.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/112.png" style="width: 75%; height: 75%">
        <img src="@/assets/img/help/113.png" style="width: 75%; height: 75%">

        <h2 id="technical-support">四．技术支持</h2>
        <p>客服热线：+123-456-7890（工作时间：周一至周五，9:00 - 18:00）</p>
        <p>电子邮件：support@zhigongdi.com</p>
    </div>
</template>
  
<script>
export default {
    name: 'UserManual'
}
</script>
  
<style scoped>
.user-manual h1,
.user-manual h2 {
    color: #333;
}

.user-manual hr {
    border: 1px solid #ddd;
}

.user-manual ul {
    list-style-type: none;
    padding: 0;
}

.user-manual a {
    color: #007bff;
    text-decoration: none;
}

.user-manual a:hover {
    text-decoration: underline;
}
</style>