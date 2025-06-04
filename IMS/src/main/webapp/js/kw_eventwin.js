
function checkCookie(key)
{
	if (getCookie("cms" + key) == "noopen")
	{
		return true;
	}
	else
	{
		return false;
	}
}


function setCookie(key, value)
{
	var cook = key + "=" + escape(value) + ";";
	var today = new Date();

	today.setDate(today.getDate() + 1);
	document.cookie = cook + "path=/;expires=" + today.toUTCString() + ";"
}


function getCookie(key)
{
	var cook = document.cookie + ";";
	index1 = cook.indexOf(key, 0);

	if (index1 != -1)
	{
		cook = cook.substring(index1, cook.length);
		index2 = cook.indexOf("=", 0) + 1;
		index3 = cook.indexOf(";", index2);

		return (unescape(cook.substring(index2, index3)));
	}

	return "";
}
