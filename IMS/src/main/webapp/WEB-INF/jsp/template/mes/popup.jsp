<%@ page pageEncoding="UTF-8" contentType="text/html; charset=UTF-8"%>
<%@ taglib prefix="t" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta name="viewport" content="width=device-width, initial-scale=1"/>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<title>POPUP</title>

<link href="/css/mes/common.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/mes_main.css" rel="stylesheet"	type="text/css" />
<link href="/css/mes/popup.css" rel="stylesheet"	type="text/css" />


<!-- <script	type="text/javascript" src="/js/egovframework/com/cmm/jquery-1.4.2.min.js"></script>  -->
<link rel="stylesheet" type="text/css" href="/js/jquery-ui-1.14.1/jquery-ui.min.css" />
<script src="/js/jquery/jquery-3.7.1.min.js"></script>
<script src="/js/jquery-ui-1.14.1/jquery-ui.min.js"></script>
<script type="text/javascript" src="/js/kw_common.js"></script>
<script type="text/javascript" src="<c:url value='/se/js/HuskyEZCreator.js' />"></script>
<script>
function setStartDate(d) {
    var settingDate = new Date();
    if(d == "7"){
        settingDate.setDate(settingDate.getDate()-7);
    	$("#topStartDate").val(settingDate.format("yyyy-MM-dd"));
    }else if(d == "1"){	
        settingDate.setMonth(settingDate.getMonth()-1);
    	$("#topStartDate").val(settingDate.format("yyyy-MM-dd"));
    }else{	
        settingDate.setMonth(settingDate.getMonth()-3);
    	$("#topStartDate").val(settingDate.format("yyyy-MM-dd"));
    }
    fn_guestList(1);
} 


//12-06 임석현 데이트포맷 추가
Date.prototype.format = function(f) {
    if (!this.valueOf()) return " ";
 
    var weekName = ["일요일", "월요일", "화요일", "수요일", "목요일", "금요일", "토요일"];
    var d = this;
     
    return f.replace(/(yyyy|yy|MM|dd|E|hh|mm|ss|a\/p)/gi, function($1) {
        switch ($1) {
            case "yyyy": return d.getFullYear();
            case "yy": return (d.getFullYear() % 1000).zf(2);
            case "MM": return (d.getMonth() + 1).zf(2);
            case "dd": return d.getDate().zf(2);
            case "E": return weekName[d.getDay()];
            case "HH": return d.getHours().zf(2);
            case "hh": return ((h = d.getHours() % 12) ? h : 12).zf(2);
            case "mm": return d.getMinutes().zf(2);
            case "ss": return d.getSeconds().zf(2);
            case "a/p": return d.getHours() < 12 ? "오전" : "오후";
            default: return $1;
        }
    });
};
 
String.prototype.string = function(len){var s = '', i = 0; while (i++ < len) { s += this; } return s;};
String.prototype.zf = function(len){return "0".string(len - this.length) + this;};
Number.prototype.zf = function(len){return this.toString().zf(len);};

Date.prototype.getYearYY = function(){
	 var a = this.getYear();
	 return a >= 100 ? a-100 : a;
	}
</script>
</head>
<body> 
		<t:insertAttribute name="content"/> 
</body>
</html>
