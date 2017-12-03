/**
 * 全局共用，侧重于工具类
 */
define(function(require,exports,module){
	var $ = jQuery = require("jquery"),
		body = $("body"),
		layer = require("plugin/layer/layer"),
		cast = require("common/cast"),
		tool = {};
	module.exports = tool;
	
	//判断是否为空
	tool.isEmpty = function(value) {
		var type;
	    if(value == null) {
	        return true;
	    }
	    type = Object.prototype.toString.call(value).slice(8, -1);
	    switch(type) {
	        case 'String':
	            return !$.trim(value);
	        case 'Array':
	            return !value.length;
	        case 'Object':
	            return $.isEmptyObject(value);
	        default:
	            return false;
	    }
    };
    
	//检查返回结果
    tool.checkRes = function(res){
    	if(typeof res == "string"){
    		res = JSON.parse(res);
    	}
    	var code = res.code,
    		msg = res.msg;
    	if(code==500){
    		layer.alert("系统错误，请联系管理员");
			return false;
    	}else if(code==1000){
    		return true;
    	}else if(code==1001){
    		layer.msg(msg);
    		return true;
    	}else if(code==1002 || code==1005){
			layer.alert(msg);
			return false;
		}else if(code==1003){
    		layer.alert(msg);
    		setTimeout('window.top.location.href = "/login.html"',2000);
    		return false;
 	    }else{
 	    	return true;
    	}
    };
    
    //加载中(操作)
    tool.load = function(config){
    	if(tool.isEmpty(config)){
    		config = {};
    		config.time = 60000;
    	}
    	var index = layer.load(1, {time: config.time,offset: '300px',shade : 0.3});
    	return index;
    };
    
    tool.closeLayer = function(index){
    	layer.close(index);
    };
    
    tool.msg = function(msg,icon){
    	layer.msg(msg, {
    	    icon: icon||1, // 默认1; 1 成功   2 失败   共0-6
    	    time: 2000, //1秒关闭（如果不配置，默认是3秒）
    	    shade:0.3,
    	    offset: '250px'
    	}, function(){
    	    //do something
    	});
    };
    
    tool.alert = function(msg, callback){
    	layer.alert(msg, {title:"提示信息",closeBtn:0}, callback);
    };
    
    //发送api请求
    tool.apiReq = function(config){
    	$.ajax({
    		headers    : {
    			"token" : localStorage.getItem("token"),
    			'Content-Type' : 'application/x-www-form-urlencoded; charset=UTF-8'
    		},
    	    url        : cast.apiUrl+config.url,
    	    method     : config.method || 'post',
    	    async      : config.async || true,
    	    crossDomain: true,
    	    dataType   : 'json',
    	    data       : config.params || null,
    	    success    : config.onSuccess,
    	    error      : function(){
    	    	tool.msg("系统错误，请稍后再试",2);
    	    }
    	});
    };
    
});