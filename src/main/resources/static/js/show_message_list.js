function display(category,message_list){
    var HTMLString = "";
    // 获取当前用户userId
    var id = userinfo.id;
    //定义时间显示模版
    var timeOptions = {
        year:"numeric",
        month: "2-digit",
        day:"2-digit",
        hour:'2-digit',
        minute:'2-digit',
        hour12:false,
    }
    // 定义国际时间格式器
    var dateFormat = Intl.DateTimeFormat("zh-CN",timeOptions);


    // 判断当前会话是聊天室会话还是用户会话
    if(category == 0){
        // 是聊天室的会话，要加名字
        // 循环消息列表，显示消息
        for (let i = message_list.length-1; i >= 0; i--) {
            let message = message_list[i];
            // 插入发送时间
            var send_time = new Date(message.sendTime);

            HTMLString = HTMLString +
                ` <div class="message_time">`+ dateFormat.format(send_time)+`</div>`;
            if(message.sendId == id){
                // 是当前用户发送的数据
                if(message.category == 0){
                    // 是文本数据
                    HTMLString = HTMLString +
                        `<div class="message_row_right" >
                                    <div>
                                        <div class="right_name">`+ message.username+`</div>
                                        <span class="message_right" oncontextmenu="show_send_redirect_menu(event,this)">`+ message.content+`</span>
                                    </div>

                                <img src="`+ message.headPath+`" alt="" class="photo">
                            </div>`;
                }else{
                    // 是图片数据
                    HTMLString = HTMLString +
                        `<div class="message_row_right">
                             <div>
                                  <div class="right_name">`+ message.username+`</div>
                                  <img src="`+ message.content+`" alt="" class="message_photo">
                             </div>

                            <img src="`+message.headPath+`" alt="" class="photo">
                        </div>`;
                }
            }else{
                // 是其他用户发送的信息
                if(message.category == 0){
                    // 是文本数据
                    HTMLString = HTMLString +
                        `<div class="message_row_left">
                            <img src="`+ message.headPath+`" alt="" class="photo" oncontextmenu="show_content_menu(event,this)" data-id="`+ message.sendId+`">
                            <div>
                                <div class="left_name">`+ message.username+`</div>
                                <span class="message_left" oncontextmenu="show_send_redirect_menu(event,this)">`+ message.content+`</span>
                            </div>

                        </div>`;
                }else{
                    // 是图片数据
                    HTMLString = HTMLString +
                        `<div class="message_row_left">

                            <img src="`+ message.headPath +`" alt="" class="photo" oncontextmenu="show_content_menu(event,this)" data-id="`+ message.sendId+`">
                             <div>
                                <div class="left_name">`+ message.username+`</div>
                                <img src="`+ message.content+`" alt="" class="message_photo">
                            </div>

                        </div>`;
                }
            }



        }
    }else{
        // 用户之间的会话，不需要加名字
        // 循环消息列表，显示消息
        for (let i = message_list.length-1; i >= 0; i--) {
            let message = message_list[i];
            // 插入发送时间
            var send_time = new Date(message.sendTime);

            HTMLString = HTMLString +
                ` <div class="message_time">`+ dateFormat.format(send_time)+`</div>`;
            if(message.sendId == id){
                // 是当前用户发送的数据
                if(message.category == 0){
                    // 是文本数据
                    HTMLString = HTMLString +
                        `<div class="message_row_right">
                            <span class="message_right" oncontextmenu="show_send_redirect_menu(event,this)">`+ message.content+`</span>
                            <img src="`+ message.headPath+`" alt="" class="photo">
                        </div>`;
                }else{
                    // 是图片数据
                    HTMLString = HTMLString +
                        `<div class="message_row_right">
                            <img src="`+ message.content+`" alt="" class="message_photo">
                            <img src="`+message.headPath+`" alt="" class="photo">
                        </div>`;
                }
            }else{
                // 是其他用户发送的信息
                if(message.category == 0){
                    // 是文本数据
                    HTMLString = HTMLString +
                        `<div class="message_row_left">
                            <img src="`+ message.headPath+`" alt="" class="photo" oncontextmenu="show_content_menu(event,this)" data-id="`+ message.sendId+`">
                            <span class="message_left" oncontextmenu="show_send_redirect_menu(event,this)">`+ message.content+`</span>
                        </div>`;
                }else{
                    // 是图片数据
                    HTMLString = HTMLString +
                        `<div class="message_row_left">

                            <img src="`+ message.headPath +`" alt="" class="photo" oncontextmenu="show_content_menu(event,this)" data-id="`+ message.sendId+`">
                             <img src="`+ message.content+`" alt="" class="message_photo">
                        </div>`;
                }
            }
        }
    }
    return HTMLString;

}