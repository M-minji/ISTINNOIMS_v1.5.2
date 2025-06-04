function showImage(filename)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (ext == "jpg" || ext == "gif" || ext == "bmp" || ext == "png")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' border='0' align='absmiddle'></a>");
		document.close();
	}
	else
	{
		return;
	}
}


function showImageBoard(filename)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (ext == "jpg" || ext == "gif" || ext == "bmp" || ext == "png")
	{
		document.open();
		document.writeln("<img src='" + filename + "' name='board_image' border='0' width='400' height='400' align='absmiddle' style='cursor:hand' onclick='openImage(\"" + filename + "\")'>");
		document.close();
	}
	else
	{
		return;
	}
}


function openImage(filename)
{
	var scroller = "no";
	var img = new Image();
	img.src = filename;

	var xlimit = img.width;
	var ylimit = img.height;
	


	if (xlimit > screen.availWidth || ylimit > screen.availHeight)
	{
		scroller = "yes, top=0, left=0";
	}

	if (xlimit > screen.availWidth)
	{
		xlimit = screen.availWidth;
	}

	if (ylimit > screen.availHeight)
	{
		ylimit = screen.availHeight;
	}

	window.open("../kw_image_vf.jsp?img=" + filename, "", "toolbar=no, resizable=no, scrollbars=" + scroller + ", status=no, width=" + (xlimit - 8) + ", height=" + (ylimit - 30));
}


function resizeImage()
{
	for (var i = 0; i < document.images.length; i++)
	{
		if (document.images[i].name == "board_image" && document.images[i].complete)
		{
			if (document.images[i].width > 500)
			{
				document.images[i].width = 500;
			}
		}
	}
}


function showMulti(filename)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (filename == "")
	{
		return;
	}
	else if (ext == "jpg" || ext == "gif" || ext == "bmp" || ext == "png")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' border='0' align='absmiddle'></a>");
		document.close();
	}
	else if (ext == "asf" || ext == "mpg" || ext == "mpeg" || ext == "wmv" || ext == "wma" || ext == "mp3" || ext == "avi")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><embed src='" + filename + "' align='absmiddle'></a>");
		document.close();
	}
	else if (ext == "swf")
	{
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
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' border='0'></a>");
		document.close();
	}
}


function showMultiBoard(filename)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (filename == "")
	{
		return;
	}
	else if (ext == "jpg" || ext == "gif" || ext == "bmp" || ext == "png")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' name='board_image' border='0' align='absmiddle'  width='400px' height='300px'></a>");
		document.close();
	}
	else if (ext == "asf" || ext == "mpg" || ext == "mpeg" || ext == "wmv" || ext == "wma" || ext == "mp3" || ext == "avi")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><embed src='" + filename + "' align='absmiddle' width='400px'  height='300px'></a>");
		document.close();
	}
	else if (ext == "swf")
	{
		document.open();
		document.writeln("<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0'>");
		document.writeln("<param name='movie' value='" + filename + "'>");
		document.writeln("<param name='quality' value='high'>");
		document.writeln("<embed src='" + filename + "' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash'  width='400px'  height='300px'></embed>");
		document.writeln("</object>");
		document.close();
	}
	else
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' border='0'  width='400px'></a>");
		document.close();
	}
}


function showMultiEx(filename, width, height)
{
	var temp = filename.toLowerCase().split(".");
	var ext = temp[temp.length - 1];

	if (filename == "")
	{
		return;
	}
	else if (ext == "jpg" || ext == "gif" || ext == "bmp" || ext == "png")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' width='" + width + "' height='" + height + "' border='0' align='absmiddle'></a>");
		document.close();
	}
	else if (ext == "asf" || ext == "mpg" || ext == "mpeg" || ext == "wmv" || ext == "wma" || ext == "mp3" || ext == "avi")
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><embed src='" + filename + "' align='absmiddle'></a>");
		document.close();
	}
	else if (ext == "swf")
	{
		document.open();
		document.writeln("<object classid='clsid:D27CDB6E-AE6D-11cf-96B8-444553540000' codebase='http://download.macromedia.com/pub/shockwave/cabs/flash/swflash.cab#version=6,0,29,0' width='" + width + "' height='" + height + "'>");
		document.writeln("<param name='movie' value='" + filename + "'>");
		document.writeln("<param name='quality' value='high'>");
		document.writeln("<embed src='" + filename + "' quality='high' pluginspage='http://www.macromedia.com/go/getflashplayer' type='application/x-shockwave-flash' width='" + width + "' height='" + height + "'></embed>");
		document.writeln("</object>");
		document.close();
	}
	else
	{
		document.open();
		document.writeln("<a href='" + filename + "' target='_blank'><img src='" + filename + "' width='" + width + "' height='" + height + "' border='0'></a>");
		document.close();
	}
}
