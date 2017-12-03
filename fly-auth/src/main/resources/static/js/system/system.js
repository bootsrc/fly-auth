//left、main、right三个页面共用
define(function(require, exports, module){
   var $ = require("jquery"),
       tool = require("common/tool"),
       cast = require("common/cast"),
       body = $("body"),
       system = {};

    module.exports = system;

    system.mainInit = function(){
    	system.refreshToken();
    };
    
    //刷新token
    system.refreshToken = function(){
    	if(typeof (EventSource) !== "undefined" ) {
    	   var requestPath = cast.apiUrl+"/refreshToken?token="+localStorage.getItem("refreshToken"); 
           var source = new EventSource(requestPath);
           source.onmessage = function(event) {
        	   var refreshToken = event.data;
        	   localStorage.setItem("token",refreshToken);
           };
    	}else{
           tool.msg("当前浏览器不兼容,请联系管理员",2);
    	}
    };
});


