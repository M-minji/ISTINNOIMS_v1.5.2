
function insert_go()
{
	with (document.writeform)
	{
		if (k_folder_name.value == "")
		{
			k_folder_name("폴더명을 입력하세요.");
			name.focus();
			return;
		}
		if (k_folder_disk_menu.value == "")
		{
			alert("폴더종류를 입력하세요.");
			k_folder_disk_menu.focus();
			return;
		}
		if (k_folder_max_size.value == "")
		{
			alert("폴더 크기를 입력하세요.");
			k_folder_max_size.focus();
			return;
		}
		submit();
	}
}


function insert_reset()
{
	with (document.writeform)
	{
		reset();
		k_folder_name.focus();
	}
}

