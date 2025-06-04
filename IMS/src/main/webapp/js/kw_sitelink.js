function go_url(str)
{
	var data = str.split("|");
	var url = data[0];
	var target = data[1];

	if (target == "_blank")
	{
		window.open(url);
	}
	else
	{
		location.href = url;
	}
}