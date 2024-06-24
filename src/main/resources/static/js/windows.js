// 初始化窗口界面，加入关闭按钮
function init_window(){
    windows.text("");
    var strHTML = 
    ` <div class="option_row">
         <div onclick="close_window()">x</div>
    </div>`;
    windows.append(strHTML);
}
function init_small_window(){
    small_window.text("");
    var strHTML =
        ` <div class="option_row">
         <div onclick="close_small_window()">x</div>
    </div>`;
    small_window.append(strHTML);
}

