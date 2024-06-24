
// 获取一页用户
var page_user;
// 获取一页聊天室
var page_chatroom;
// 获取一页举报信息
var page_report;
// 获取show_info
var show_info ;
// 获取页码输入框
var page_input;
// 最大页数
var max_index ;
// 获取当前被选中的功能块
var choice; 
// 时间格式器
var timeOptions = {
    year:"numeric",
    month: "2-digit",
    day:"2-digit",
    hour:'2-digit',
    minute:'2-digit',
    hour12:false,
};
var format = Intl.DateTimeFormat("zh-CN",timeOptions);
$(document).ready(function(){
    // 获取show_info
    show_info = jQuery("#show_info");
    // 获取页码输入框
    page_input = jQuery("#page_index");
    // 最大页数
    max_index = jQuery("#max_index");
    choice = jQuery("#user_manage");
})


// 给页码输入框绑定快捷键
function check_enter(e){
    if(e.key === "Enter"){
        // 获取页码数
        var index = page_input.val().trim();
        // 判断输入是否合规
        if(index == "" || !/^\d+$/.test(index) || index>max_index){
            alert("请输入正确的数字");
            page_input.val("");
            return;
        }
        // 判断种类并发送获取列表请求
        if(choice.text() == "用户管理"){
            get_page_user(index);
        }else if(choice.text() == "聊天室管理"){
            get_page_chatroom(index);
        }else if(choice.text() == "举报管理"){
            get_page_report(index);
        }
    }
}
// 获取用户最大页码
function get_user_max_index(){
    jQuery.ajax({
        url:context_path+"/manager/get_user_max_index",
        method:"get",
        success:function (res){
            if(res.code == 200){
                max_index.text(res.data);
                page_input.attr("max",res.data);
            }else{
                alert("获取用户最大页数时出现错误："+res.message);
            }
        }
    })
}
get_user_max_index();
// 获取聊天室最大页码
function get_chatroom_max_index(){
    jQuery.ajax({
        url:context_path+"/manager/get_chatroom_max_index",
        method:"get",
        success:function (res){
            if(res.code == 200){
                max_index.text(res.data);
                page_input.attr("max",res.data);
            }else{
                alert("获取聊天室最大页数时出现错误："+res.message);
            }
        }
    })
}

// 获取举报信息最大页码
function get_report_max_index(){
    jQuery.ajax({
        url:context_path+"/manager/get_report_max_index",
        method:"get",
        success:function (res){
            if(res.code == 200){
                max_index.text(res.data);
                page_input.attr("max",res.data);
            }else{
                alert("获取举报最大页数时出现错误："+res.message);
            }
        }
    })
}

// 获取某页用户信息函数
function get_page_user(index){
    jQuery.ajax({
        url:context_path+"/manager/get_page_user",
        method:"post",
        data:{
            index:index
        },
        success:function(res){
            if(res.code == 200){
                page_user= res.data;
                show_page_user();
            }else if(res.code == 0){
                alert("获取某页用户信息时，后端没有接收到传递的参数");
            }else{
                alert("获取某页用户信息时服务器出错");
            }   
        }
    });
}

// 获取某页聊天室信息函数
function get_page_chatroom(index){
    jQuery.ajax({
        url:context_path+"/manager/get_page_chatroom",
        method:"post",
        data:{
            index:index
        },
        success:function(res){
            if(res.code == 200){
                page_chatroom = res.data;
                show_page_chatroom();
            }else if(res.code == 0){
                alert("获取某页聊天室信息时，后端没有接收到传递的参数");
            }else{
                alert("获取某页聊天室信息时服务器出错");
            }   
        }
    });
}
// 获取某页举报信息函数
function get_page_report(index){
    jQuery.ajax({
        url:context_path+"/manager/get_page_report",
        method:"post",
        data:{
            index:index
        },
        success:function(res){
            if(res.code == 200){
                page_report = res.data;
                show_page_report();
            }else if(res.code == 0){
                alert("获取某页举报信息时，后端没有接收到传递的参数");
            }else{
                alert("获取某页举报信息时服务器出错");
            }   
        }
    });
}

get_page_user(1);


// 显示用户列表
function show_page_user(){
    // 将显示区域置空
    show_info.text("");
    // 获取HTML
    var HTMLString = choose_user_manage_HTML();
    // 将HTML插入显示区
    show_info.append(HTMLString);
}
// 用户管理HTML
function choose_user_manage_HTML(){
    var HTMLString =
        `<div class="search_row">
            <input type="text">
            <button onclick="search_user()">搜索</button>
        </div>`;
    for(var i = 0; i<page_user.length;i++){
        var user = page_user[i];
        HTMLString = HTMLString + 
            `<div class="info_row">
                <div class="row_left">
                    <img src="`+ user.photo+`" alt="">
                    <h3>`+ user.username+`(`+user.accountNum +`)`+`</h3>
                </div>
                <div class="row_right">
                    <button onclick="show_small_window(`+ user.id+`,`+ 4+`)">重置密码</button>`;
        if(user.state == 3){
            // 已经被封禁了
            HTMLString = HTMLString +
                `   
                    <button onclick="show_small_window(`+user.id+`,`+ 5+`)">解封用户</button>
                </div>
            
            </div>`;
        }else{
            // 没有被封禁
            HTMLString = HTMLString +
                `   <button onclick="show_small_window(`+user.id+`,`+ 1+`)">封禁用户</button>
                    
                </div>
            
            </div>`;
        }
    }
    
    return HTMLString;

}

// 显示聊天室列表
function show_page_chatroom(){
    // 将显示区域置空
    show_info.text("");
    // 获取HTML
    var HTMLString = choose_chatroom_manage_HTML();
    // 将HTML插入显示区
    show_info.append(HTMLString);
}
function choose_chatroom_manage_HTML(){
    var HTMLString =
        `<div class="search_row">
            <input type="text">
            <button onclick="search_chatroom()">搜索</button>
        </div>`;
    for(var i = 0; i<page_chatroom.length;i++){
        var chatroom = page_chatroom[i];
        HTMLString = HTMLString + 
            `<div class="info_row">
                <div class="row_left">
                    <img src="`+ chatroom.photo+`" alt="">
                    <h3>`+ chatroom.name+`(`+chatroom.sessionId +`)`+`</h3>
                </div>
                <div class="row_right">
                    <button onclick="show_small_window(`+ chatroom.sessionId+`,`+ 3+`)">封禁聊天室</button>
                </div>
            </div>`;
        
    }
    return HTMLString;
}
// 聊天室管理HTML
// 管理点击函数
function choose_manage(dom){
    // 获取刚刚被选中的功能块
    var newChoice = jQuery(dom);
    // 为刚刚被选中的功能块添加选中效果
    newChoice.addClass("choosen");
    // 切换被选中的功能块
    if(choice == null){
        choice = newChoice;
    }else{
        // 取消之前被选中的功能块的选中效果
        choice.removeClass("choosen");
        choice = newChoice;
    }
    // 判断种类并发送获取列表请求
    if(choice.text() == "用户管理"){
        get_user_max_index();
        get_page_user(1);
    }else if(choice.text() == "聊天室管理"){
        get_chatroom_max_index();
        get_page_chatroom(1);
    }else if(choice.text() == "举报管理"){
        get_report_max_index();
        get_page_report(1);
    }
    page_input.val("1");
}
// 显示聊天室列表
function show_page_report(){
     // 将显示区域置空
     show_info.text("");
     // 获取HTML
     var HTMLString = choose_report_manage_HTML();
     // 将HTML插入显示区
     show_info.append(HTMLString);
}
function choose_report_manage_HTML(){
    var HTMLString =
        `<div class="search_row">
            <input type="text">
            <button onclick="search_report()">搜索</button>
        </div>`;
    for(var i = 0; i<page_report.length;i++){
        var report = page_report[i];
        var time = new Date(report.sendTime);
        HTMLString = HTMLString + 
            `<div class="info_row">
                <div class="row_left">
                    <h3>`+ report.sendName+`(`+report.sendId +`)`+`</h3>
                    <span>因为</span>
                    <h3>`+ report.content+`</h3> 
                    <span>举报</span>
                    <h3>`+ report.receiveName+`(`+report.receiveId +`)`+`</h3>
                </div>
                <div class="row_right">
                    <div class="time">`+ format.format(time)+`</div>`;
        // 判断举报信息的处理情况
        if(report.result == 0){
            // 未处理
            HTMLString = HTMLString +
                `     <button onclick="show_small_window(`+ report.receiveId+`,`+ 6+`,\``+report.content+`\`,`+report.id+`)">封禁</button>
                <button onclick="show_small_window(`+ report.receiveId+`,`+ 2+`,\``+report.content+`\`,`+report.id+`)">警告</button>
                </div>
            </div>`;
        }else if(report.result ==1){
            // 警告
            HTMLString = HTMLString +
                `<div>已警告</div>
                </div>
                </div>`;
        }else if(report.result == 2){
            // 封禁
            HTMLString = HTMLString +
                `<div>已封禁</div>
                </div>
                </div>`;
        }
        
    }
    return HTMLString;
}
// 查询用户信息
function search_user(){
    // 获取输入框
    var input_value = jQuery("input[type='text' ]");
    // 发送请求
    jQuery.ajax({
        url:context_path+"/manager/search_user",
        method:"post",
        data:{
            key:input_value.val().trim()
        },
        success:function(res){
            if(res.code == 200){
                page_user = res.data;
                show_page_user();
            }else if(res.code == 0){
                alert("查询用户时，后端没有接受到对应的参数");
            }else{
                alert("查询用户时，服务器出现错误:"+res.message);
            }
        }
    });
}
// 查询聊天室信息
function search_chatroom(){
    // 获取输入框
    var input_value = jQuery("input[type='text']");
    // 发送请求
    jQuery.ajax({
        url:context_path+"/manager/search_chatroom",
        method:"post",
        data:{
            key:input_value.val().trim()
        },
        success:function(res){
            if(res.code == 200){
                page_chatroom = res.data;
                show_page_chatroom();
            }else if(res.code == 0){
                alert("查询聊天室时，后端没有接受到对应的参数");
            }else{
                alert("查询；聊天室时，服务器出现错误:"+res.message);
            }
        }
    });
}
// 查询举报信息
function search_report(){
    // 获取输入框
    var input_value = jQuery("input[type='text']");
    // 发送请求
    jQuery.ajax({
        url:context_path+"/manager/search_report",
        method:"post",
        data:{
            key:input_value.val().trim()
        },
        success:function(res){
            if(res.code == 200){
                page_report = res.data;
                show_page_report();
            }else if(res.code == 0){
                alert("查询举报信息时，后端没有接受到对应的参数");
            }else{
                alert("查询举报信息时，服务器出现错误:"+res.message);
            }
        }
    });
}