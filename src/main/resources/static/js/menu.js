// 被转发的消息
var content;
// 返回创建分组小窗HTML字符串
function creat_group_req(){
    // 获取被选择的分组种类
    var input_choose = jQuery("input[name='category']:checked");
    // 获取新分组名称
    var input_name= jQuery("#temp_input");
    var name = input_name.val().trim();
    if(name == ""){
        alert("请先输入新分组名称");
        return;
    }
    jQuery.ajax({
        url:context_path+"/contact_group/create_group",
        method:"get",
        data:{
            name:name,
            category:input_choose.val()
        },
        success:function (res){
          if(res.code == 200){
              alert("建立了新的分组");
              // 重新获取分组
              update_contact_list();
              // 关闭小窗
              close_small_window();
          }else if(res.code == 0){
              alert("在创建新分组时后端没有接受到参数");
          }else{
              alert("在创建新分组时服务器出现错误："+res.message);
          }
        }
    })
}
// 显示创建分组小窗
function create_group(){
    
    // 初始化小窗
    init_small_window();
    // 获取创建分组小窗的HTML
    var HTMLString = 
    `<div class="message_password">
    <h2>创建新分组</h2>
    <div class="check_row">
        <input type="radio" checked="checked" name="category" id="chatroom" value="0">
        <label for="chatroom">群聊分组</label>
        <input type="radio" id="person" name="category" value="1">
        <label for="person">好友分组</label>
    </div>
    <div class="password_row">
        <h3>新分组名：</h3>
        <input type="text" id="temp_input">
    </div>
    <button id="send_button" onclick="creat_group_req()">确认</button>
    </div>`;
    // 插入HTML
    small_window.append(HTMLString);
    // 显示小窗
    small_window_page.css("z-index",5);
}
// 显示创建分组菜单
function show_create_group_menu(event,dom){
    // 阻止默认的右键反应
   event.preventDefault();
   
   // 选中会话
   session_choose = jQuery(dom);
   context_menu.text("");
   // 向菜单插入选项
   HTMLString =
       `<div class="option" onclick="create_group()">创建分组</div>`;
   context_menu.append(HTMLString);
   // 显示右键菜单
   context_menu.css({
       display:"block",
       left:event.clientX+"px",
       top:event.clientY+"px"
   });
   //点击其他地方隐藏菜单
   $(document).on("click", function hideContextMenu() {
       context_menu.hide();

       $(document).off("click", hideContextMenu);
   });
}
// 显示切换分组菜单
function show_switch_group_menu(event,dom){
   // 阻止默认的右键反应
   event.preventDefault();
   
   // 选中会话
   session_choose = jQuery(dom);
   context_menu.text("");
   // 向菜单插入选项
   HTMLString =
       `<div class="option" onclick="switch_group(`+session_choose.data("id") +`,`+session_choose.data("category") +`,`+ session_choose.data("group-id")+ `)">切换分组</div>`;
   context_menu.append(HTMLString);
   // 显示右键菜单
   context_menu.css({
       display:"block",
       left:event.clientX+"px",
       top:event.clientY+"px"
   });
   //点击其他地方隐藏菜单
   $(document).on("click", function hideContextMenu() {
       context_menu.hide();

       $(document).off("click", hideContextMenu);
   });
}
// 显示切换分组小窗
function switch_group(id,category,group_id){
    // 初始化窗口
    init_small_window();
    // 获取构造好的HTML
    var HTMLString = switch_group_HTML(category,group_id,id);
    // 将HTML插入小窗
    small_window.append(HTMLString);
    // 显示小窗
    small_window_page.css("z-index",5);
}
function switch_group_HTML(category,group_id,id){
    var HTMLString = `<div class="choose_list">`;
    // 判断是否有切换分组标识
    var flag = 0;
    for(var i= 0;i<contact_group_list_info.length;i++){
        var group = contact_group_list_info[i];
        // 确保是对应种类并且不是对应分组id
        if(group.id!=group_id && group.category ==category){
            HTMLString = HTMLString +
                        `<div class="choose_row">
                    <input type="radio" name="group" class="choose_point" value="`+group.id+`">
                    <div>`+ group.name+`</div>
                </div>`;
            flag = 1;
        }
    }
    if(flag == 0){
        // 没有可以切换的分组
        HTMLString = HTMLString +
            `没有可以切换的分组`;
    }
    HTMLString = HTMLString +
            `</div>
            <button onclick="switch_group_req(`+id+`)"> 确认 </button>`;
    return HTMLString;
}
function switch_group_req(id){
    // 获取被选中的分组
    var group_input = jQuery("input[name='group']:checked");
    
    // 判断是否有分组被选中
    if(group_input.length == 0){
        alert("请先选中分组！");
        return;
    }
    // 发送切换分组请求
    jQuery.ajax({
        url:context_path+"/contact_session/switch_group",
        data:{
            id:id,
            groupId:group_input.val()
        },
        success:function(res){
            if(res.code == 200){
                alert("切换分组成功");
                // 关闭小窗
                close_small_window();
                // 更新会话列表
                get_session_message();
            }else if(res.code == 0){
                alert("切换分组时，后端没有接受到对应参数");
            }else{
                alert("切换分组时出现了错误："+res.message);
            }
        }
    })
}
// 被选中的用户
var choose_user;
function show_report_window(){
    // 初始化小窗口
    init_small_window();
    // 获取小窗口结构
    var HTMLString = show_report_HTML();
    // 插入小窗结构
    small_window.append(HTMLString);
    // 显示小窗
    small_window_page.css("z-index",4);

}
// 显示举报HTML
function show_report_HTML(){
    var HTMLString=
        `<div class="content_info">
            <h3 style="text-align: center">举报用户</h3>
            <div class="content_row">
                <div>举报理由：</div>
                <textarea class="content_text"></textarea>
            </div>
            <button class="content_send" onclick="report_info_req(`+choose_user.data("id")+`)">确认 </button>
        </div>`;
    return HTMLString;

}
function show_content_menu(event,dom){
    // 阻止默认右键反应
    event.preventDefault();
    // 选中dom
    choose_user = jQuery(dom);
    // 初始化选项框
    context_menu.text("");
    // 构造选项框
    var HTMLString =
        `<div class="option" onclick="show_report_window()">举报</div>`;
    // 在菜单中插入选项
    context_menu.append(HTMLString);
    // 显示右键菜单
    context_menu.css({
        display:"block",
        left:event.clientX+"px",
        top:event.clientY+"px"
    });
    //点击其他地方隐藏菜单
    $(document).on("click", function hideContextMenu() {
        context_menu.hide();

        $(document).off("click", hideContextMenu);
    });
}
// 被右键选中的会话
var session_choose;
// 右键会话显示的菜单
function show_session_menu(event,dom){
    // 阻止默认的右键反应
    event.preventDefault();
    
    // 选中会话
    session_choose = jQuery(dom);
    context_menu.text("");
    // 向菜单插入选项
    HTMLString =
        `<div class="option" onclick="hide_session()">隐藏</div>`;
    context_menu.append(HTMLString);
    // 显示右键菜单
    context_menu.css({
        display:"block",
        left:event.clientX+"px",
        top:event.clientY+"px"
    });
    //点击其他地方隐藏菜单
    $(document).on("click", function hideContextMenu() {
        context_menu.hide();

        $(document).off("click", hideContextMenu);
    });

}

// 隐藏被选中的会话功能
function hide_session(){
    // 发送隐藏session请求
    jQuery.ajax({
        url:context_path+"/contact_session/hide_session",
        method:"post",
        data:{
            id:session_choose.data("id")
        },
        success:function (res){
            if(res.code == 200){
                // 隐藏session
                session_choose.hide();
                show_origin_content();
            }else if(res.code == 0){
                alert("发送隐藏请求时，id未被服务器接收到");
            }else{
                alert("发送隐藏会话请求时，服务器出错了："+res.message);
            }
        }
    });
}

// 显示转发菜单
function show_send_redirect_menu(event,dom){
    // 阻止默认的右键反应
   event.preventDefault();
   
   // 选中消息
   var message = jQuery(dom);
    content = message.text();
   context_menu.text("");
   // 向菜单插入选项
   HTMLString =
       `<div class="option" onclick="send_redirect()">转发</div>`;
   context_menu.append(HTMLString);
   // 显示右键菜单
   context_menu.css({
       display:"block",
       left:event.clientX+"px",
       top:event.clientY+"px"
   });
   //点击其他地方隐藏菜单
   $(document).on("click", function hideContextMenu() {
       context_menu.hide();

       $(document).off("click", hideContextMenu);
   });
}
// 显示转发窗口
function send_redirect(){
    // 初始化小窗
    small_window.text("");
    init_small_window();
    // 获取HTML
    var HTMLString = send_redirect_HTML();
    // 插入HTML
    small_window.append(HTMLString);
    // 显示小窗
    small_window_page.css("z-index",5);
}
// 获取HTML标签
function send_redirect_HTML(){
    var HTMLString ='<div class="choose_list">';
    // 插入可选的用户
    var flag = 0;
    for (let i = 0; i < contact_session_list.length; i++) {
        var session = contact_session_list[i];
        if(session.id != choose_session.data("id") && session.isDeleted == 0){
                        
                
                flag =1;
                HTMLString = HTMLString +
                    `<div class="choose_row">
                <input type="checkbox" class="choose_point" data-id="`+session.id+`">
                <img src="`+ session.headPath+`" alt="" class="friend_head">
                <div>`+ session.nickName+`</div>
            </div>`;
            
        }
    }
    if(flag == 0){
        // 没有好友可选
        HTMLString = HTMLString +
            `<div style="text-align: center">没有好友可选</div>`;
    }
    // 添加选择列表结尾
    HTMLString = HTMLString +
        `</div>
        <button onclick="send_redirect_message()"> 转发 </button>`;
    return HTMLString;
}
// 转发消息
function send_redirect_message(){
     // 获取所有的选中的好友
     var choose_friend = $(":checkbox:checked");
     // 非空校验
     if(choose_friend == null || choose_friend.length == 0){
         alert("没有好友被选中");
         return;
     }
     // 定义id数组
     var id = [];
     // 获取所有的选中项id
     choose_friend.each(function (i) {
         id.push($(this).attr("data-id"));
     });
     
     for(var i = 0;i<id.length;i++){
            jQuery.ajax({
                url:context_path+"/message/send_message",
                 method:"post",
                 data:{
                     contactSessionId:id[i],
                     
                     content:content
                 },
                 success:function (res){
                     if(res.code == 200){
                        
                            
                        
                     }else if(res.code == 0){
                         // 参数没有传递到后端
                         alert("发送信息的参数出错");
                     }else{
                         alert("发送出现错误:"+res.message);
                     }
                 }
     
             });
               
        
        
     }
     // 关闭小窗
     close_small_window();
     alert("转发成功");
    setTimeout(function(){
        // 请求新的会话列表
        get_session_message();
    },1000);
     
}