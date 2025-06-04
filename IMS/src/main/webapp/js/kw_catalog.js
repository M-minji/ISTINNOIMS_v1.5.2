var array1, array2;
var now = 0;

function setMax(max)
{
	array1 = new Array(max);
	array2 = new Array(max);
}

function setImages(num, name1, name2)
{
	array1[num] = name1;
	array2[num] = name2;
}

function getNow()
{
	return now;
}

function getImages1(num)
{
	return array1[num];
}

function getImages2(num)
{
	return array2[num];
}

function showImage(num)
{
	if (num >= 0 && num < array1.length)
	{
		now = num;

		document.all.img1.src = getImages1(now);
		document.all.img2.src = getImages2(now);
	}
	else
	{
		alert("카탈로그의 범위는 1 ~ " + array1.length + "입니다.");
	}
}