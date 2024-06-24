// 获取小窗口
var small_window;
// 获取小窗口页面
var small_window_page;
// 获取密码输入框
var password;
// 种类
var choose_category;
// 举报理由
var choose_content;
// 操作id
var choose_id;
// 举报信息id
var advice_id;
$(document).ready(function(){
    small_window_page = jQuery(".small_window_page");
    small_window = jQuery(".small_window");
    small_window.addClass("hide");
    password = jQuery("input[type='password']");
})
// 封禁用户 1
function ban_user(){
    jQuery.ajax({
        url:context_path+"/manager/ban_user",
        method:"post",
        data:{
            id:choose_id
        },
        success:function(res){
            if(res.code == 200){
                alert("封禁成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_user(index);
            }else if(res.code == 0){
                alert("管理员发出封禁用户请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出封禁用户请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 警告用户 2
function warn_user(){
    jQuery.ajax({
        url:context_path+"/manager/warn_user",
        method:"post",
        data:{
            id:choose_id,
            content:choose_content,
            adviceId:advice_id
        },
        success:function(res){
            if(res.code == 200){
                alert("警告成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_report(index);
            }else if(res.code == 0){
                alert("管理员发出警告用户请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出警告用户请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 封禁聊天室 3
function ban_chatroom(){
    jQuery.ajax({
        url:context_path+"/manager/ban_chatroom",
        method:"post",
        data:{
            id:choose_id
        },
        success:function(res){
            if(res.code == 200){
                alert("封禁成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_chatroom(index);
            }else if(res.code == 0){
                alert("管理员发出封禁聊天室请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出封禁聊天室请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 重置密码 4
function reset_password(){
    jQuery.ajax({
        url:context_path+"/manager/reset_password",
        method:"post",
        data:{
            id:choose_id
        },
        success:function(res){
            if(res.code == 200){
                alert("重置成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_user(index);
            }else if(res.code == 0){
                alert("管理员发出重置用户密码请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出重置用户密码请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 解封用户 5
function recover_user(){
    jQuery.ajax({
        url:context_path+"/manager/recover_user",
        method:"post",
        data:{
            id:choose_id
        },
        success:function(res){
            if(res.code == 200){
                alert("解封成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_user(index);
            }else if(res.code == 0){
                alert("管理员发出解封用户请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出解封用户请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 因举报封禁用户 6
function report_ban_user(){
    jQuery.ajax({
        url:context_path+"/manager/ban_user",
        method:"post",
        data:{
            id:choose_id,
            adviceId:advice_id
        },
        success:function(res){
            if(res.code == 200){
                alert("封禁成功");
                // 关闭小窗
                close_small_window();
                var index = page_input.val();
                get_page_report(index);
            }else if(res.code == 0){
                alert("管理员发出封禁用户请求时，后端没有接收到发送的数据"); 
            }else{
                alert("管理员发出封禁用户请求时，服务器出现错误"+res.message); 
            }
        }

    })
}
// 验证函数 
function identify(){
    jQuery.ajax({
        url:context_path+"/manager/identify",
        method:"get",
        data:{
            password:password.val().trim()
        },
        success:function(res){
            if(res.code ==200){
                // 判断调用什么功能
                if(choose_category == 5){
                    recover_user();
                }else if(choose_category == 4){
                    reset_password();
                }else if(choose_category == 3){
                    ban_chatroom();
                }else if(choose_category == 2){
                    warn_user();
                }else if(choose_category == 1){
                    ban_user();
                }else if(choose_category == 6){
                    report_ban_user();
                }
            }else if(res.code == 0){
                alert("管理员发送验证身份请求时，后端没有接收到发送的数据"); 
            }else if(res.code == 1){
                alert("密码错误!");
            }else{
                alert("管理员发送验证身份请求时,服务器出现错误："+res.massage);
            }
        }
    })
}
// 关闭小窗
function close_small_window(){
    password.val("");
    small_window.addClass("hide");
    small_window_page.css("z-index",-1);
    
}
// 显示小窗
function show_small_window(id,cateory,content,report_id){
    small_window_page.css("z-index",3);
    small_window.removeClass("hide");
    choose_id = id;
    choose_category = cateory;
    choose_content = content;
    advice_id = report_id;
}