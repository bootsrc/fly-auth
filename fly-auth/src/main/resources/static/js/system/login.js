define(function(require, exports, module){
   var $ = require("jquery"),
       md5 = require("md5"),
       tool = require("common/tool"),
       loginLy = -1,
       login = {};
    module.exports = login;

    //初始化
    login.init = function(){
        $("#account,#password").val("");
        login.event();
        login.loadTopWindow();
    };
    
    //判断当前窗口是否有顶级窗口，如果有就让当前的窗口的地址栏发生变化，
    login.loadTopWindow = function (){
        if(window.top!=null && window.top.document.URL!=document.URL){   
            window.top.location= document.URL; //这样就可以让登陆窗口显示在整个窗口了   
        }
    }

    //事件注册
    login.event = function(){
        $("#password").keypress(function(e){
            if(e.keyCode==13){
                login.doLogin();
            }
        });

        $("#loginBtn").click(function(){
            login.doLogin();
        });
    };

    login.doLogin = function(){
    	loginLy = tool.load();
    	var account = $.trim($("#account").val()),
    	password = $.trim($("#password").val()),
        	params = {account:account, password:password};
        if(tool.isEmpty(account) || tool.isEmpty(password)){
            tool.msg("账号密码不允许为空",2);
            tool.closeLayer(loginLy);
            return;
        }else{
            var config = {
            	url : "/user/login.do",
            	params : params,
            	onSuccess : login.doLoginSuccess
            };
            tool.apiReq(config);
        };
    };
    
    login.doLoginSuccess = function(res){
    	tool.closeLayer(loginLy);
        if(res.code == 1000 || res.code == 1001){
            localStorage.setItem("token", res.data.token);
            localStorage.setItem("refreshToken", res.data.refreshToken);
            //跳转到主界面
            location.href ="/html/system/main.html";
        }else if(res.code == 1002){
            //接口返回未成功信息
            login.tip(res.msg);
        }else{
            tool.msg("登录失败",2);
        }
    };
});
