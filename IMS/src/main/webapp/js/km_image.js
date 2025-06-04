function showMulti(title, url, target, filename)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[1];

	if (filename == "")
	{
		//alert(title + "은 글씨다.");
		document.open();
		document.writeln("<a href='" + url + "' target='" + target + "'><b>" + title + "</b></a>");
		document.close();
	}
	else if (ext == "jpg" || ext == "gif")
	{
		//alert(title + "은 그림이다.");
		document.open();
		document.writeln("<a href='" + url + "' target='" + target + "'><img src='" + filename + "' border='0' align='absmiddle'></a>");
		document.close();
	}
	else if (ext == "swf")
	{
		//alert(title + "은 플래쉬다.");
		document.open();
		document.writeln("<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0'>");
		document.writeln("<param name='movie' value='" + filename + "'>");
		document.writeln("<param name='quality' value='high'>");
		document.writeln("<embed src='" + filename + "' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'></embed>");
		document.writeln("</object>");
		document.close();
	}
	else
	{
		//alert(title + "은 모르겠다.");
		document.open();
		document.writeln("<a href='" + url + "' target='" + target + "'><img src='" + filename + "' border='0'></a>");
		document.close();
	}
}


function setPreviewBox()
{
	preview.style.posLeft = event.x + 15 + document.body.scrollLeft;
	preview.style.posTop = event.y + document.body.scrollTop;
}


function showPreview(content)
{
	if (content != "")
	{
		var text;
		text ="<table cellpadding='0' cellspacing='0' bgcolor='#FFFFFF' border-style:solid;'>";
		text += "<tr align = center><td>" + content + "</td></tr></table>";
		preview.innerHTML = text;
		preview.style.visibility = "visible";
	}
}


function hidePreview()
{
	preview.innerHTML = "";
	preview.style.visibility = "hidden";
}