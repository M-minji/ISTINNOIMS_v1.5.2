/* Flash */
function IE_HtmlRewrite(objParent) {
	if (window.ActiveXObject && objParent) {
		objParent.innerHTML = objParent.innerHTML;
	}
}


/* Roll Over Image */
function menuOn(imgEl) {
	imgEl.src = imgEl.src.replace("_off.png", "_on.png");
}
function menuOut(imgEl) {
	imgEl.src = imgEl.src.replace("_on.png", "_off.png");
}


/* Tab Content */
function initTabMenu(tabContainerID) {
	var tabContainer = document.getElementById(tabContainerID);
	var tabAnchor = tabContainer.getElementsByTagName("a");
	var i = 0;

	for(i=0; i<tabAnchor.length; i++) {
		if (tabAnchor.item(i).className == "tab")
			thismenu = tabAnchor.item(i);
		else
			continue;

		thismenu.container = tabContainer;
		thismenu.targetEl = document.getElementById(tabAnchor.item(i).href.split("#")[1]);
		thismenu.targetEl.style.display = "none";
		thismenu.imgEl = thismenu.getElementsByTagName("img").item(0);
		thismenu.onclick = function tabMenuClick() {
			currentmenu = this.container.current;
			if (currentmenu == this)
				return;

			if (currentmenu) {
				currentmenu.targetEl.style.display = "none";
				if (currentmenu.imgEl) {
					currentmenu.imgEl.src = currentmenu.imgEl.src.replace("_on.png", "_off.png");
				} else {
					currentmenu.className = currentmenu.className.replace("on", "");
				}
			}

			this.targetEl.style.display = "block";
			if (this.imgEl) {
				this.imgEl.src = this.imgEl.src.replace("_off.png", "_on.png");
			} else {
				this.className += " on";
			}
			this.container.current = this;

			return false;
		};

		if (!thismenu.container.first)
			thismenu.container.first = thismenu;
	}
	if (tabContainer.first)
		tabContainer.first.onclick();
}

/* Tab Content */
function initTabBanner(tabContainerID) {
	var tabContainer = document.getElementById(tabContainerID);
	var tabAnchor = tabContainer.getElementsByTagName("a");
	var i = 0;

	for(i=0; i<tabAnchor.length; i++) {
		if (tabAnchor.item(i).className == "tab")
			thismenu = tabAnchor.item(i);
		else
			continue;

		thismenu.container = tabContainer;
		thismenu.imgEl = thismenu.getElementsByTagName("img").item(0);
		thismenu.onclick = function tabMenuClick() {
			currentmenu = this.container.current;
			if (currentmenu == this)
				return;

			if (currentmenu) {
				if (currentmenu.imgEl) {
					currentmenu.imgEl.src = currentmenu.imgEl.src.replace("_on.png", "_off.png");
				} else {
					currentmenu.className = currentmenu.className.replace("on", "");
				}
			}

			if (this.imgEl) {
				this.imgEl.src = this.imgEl.src.replace("_off.png", "_on.png");
			} else {
				this.className += " on";
			}
			this.container.current = this;

			return false;
		};

		if (!thismenu.container.first)
			thismenu.container.first = thismenu;
	}
	if (tabContainer.first)
		tabContainer.first.onclick();
}



// [[ Global Navigation Bar ]]
function initNavigation(seq) {
	nav = document.getElementById("mainmenu");
	nav.menu = new Array();
	nav.current = null;
	nav.menuseq = 0;
	navLen = nav.childNodes.length;
	
	allA = nav.getElementsByTagName("a")
	for(k = 0; k < allA.length; k++) {
		allA.item(k).onmouseover = allA.item(k).onfocus = function () {
			nav.isOver = true;
		}
		allA.item(k).onmouseout = allA.item(k).onblur = function () {
			nav.isOver = false;
			setTimeout(function () {
				if (nav.isOver == false) {
					if (nav.menu[seq])
						nav.menu[seq].onmouseover();
					else if(nav.current) {
						//menuImg = nav.current.childNodes.item(0);
						var menuImg = null;
						var i=0;
						while(menuImg == null || menuImg.src == null ){
							menuImg = nav.current.childNodes.item(i++);
						}

						if(menuImg != null && menuImg.src != null ){
							menuImg.src = menuImg.src.replace("_on.png", "_off.png");
						}
						if (nav.current.submenu)
							nav.current.submenu.style.display = "none";
						nav.current = null;
					}
				}
			}, 500);
		}
	}

	for (i = 0; i < navLen; i++) {
		navItem = nav.childNodes.item(i);
		if (navItem.tagName != "LI")
			continue;

		navAnchor = navItem.getElementsByTagName("a").item(0);
		navAnchor.submenu = navItem.getElementsByTagName("ul").item(0);
		
		navAnchor.onmouseover = navAnchor.onfocus = function () {
			if (nav.current) {
				//menuImg = nav.current.childNodes.item(0);
				var menuImg = null;
				var i=0;
				while(menuImg == null || menuImg.src == null ){
					menuImg = nav.current.childNodes.item(i++);
				}

				if(menuImg != null && menuImg.src != null ){
					menuImg.src = menuImg.src.replace("_on.png", "_off.png");
				}
				if (nav.current.submenu)
					nav.current.submenu.style.display = "none";
				nav.current = null;
			}
			//mouseover
			if (nav.current != this) {
				//menuImg = this.childNodes.item(0);
				var menuImg = null;
				var i=0;
				while(menuImg == null || menuImg.src == null ){
					menuImg = this.childNodes.item(i++);
				}

				if(menuImg != null && menuImg.src != null ){
					if( menuImg.src.indexOf("_on.gif") < 0 ){
						menuImg.src = menuImg.src.replace("_off.png", "_on.png");
					}
				}
				
				if (this.submenu)
					this.submenu.style.display = "block";
				nav.current = this;
			}
			nav.isOver = true;
		}
		nav.menuseq++;
		nav.menu[nav.menuseq] = navAnchor;
	}
	if (nav.menu[seq])
		nav.menu[seq].onmouseover();
} // [[ END Global Navigation Bar ]]







/* 레이어보이기 숨기기 */
function layerShow(layerId) {
 document.getElementById(layerId).style.display = "block";
}
function layerHide(layerId) {
 document.getElementById(layerId).style.display = "none";
}



/* 2012-0124 추가 */
	function getChildElementNodes(parentNode){
		var childNodes = parentNode.childNodes;
		var childArray = new Array();
		for(var i=0; i<childNodes.length; i++){
			if(childNodes[i].nodeType == 1){
				childArray.push(childNodes[i]);
			}
		}
		return childArray;
	}

	function toggleImg(imgNode, onOff){
		var imgFileName = imgNode.src;
		if(onOff == "on"){
			if(imgFileName.lastIndexOf("_on.png") < 0){
				imgNode.src = imgFileName.substring(0, imgFileName.lastIndexOf("_off.png")) + "_on.png";
			}
		}else{
			if(imgFileName.lastIndexOf("_on.png") > 0){
				imgNode.src = imgFileName.substring(0, imgFileName.lastIndexOf("_off.png")-3) + "_off.png";
			}
		}
	}

	function changeImage(targetNode){
		var allNodes = getChildElementNodes(targetNode.parentNode);
		for(var i=0; i<allNodes.length; i++){
			var img = allNodes[i].getElementsByTagName("img")[0];
			if(img != undefined){
				if(allNodes[i] == targetNode){
					toggleImg(img, "on");
				}else{
					toggleImg(img, "off");
				}
			}
		}
	}

	function showContent(targetNode){
		var allNodes = getChildElementNodes(targetNode.parentNode);
		for(var i=0; i<allNodes.length; i++){
			var content = allNodes[i].getElementsByTagName("ul")[0];
			if(content != undefined){
				if(allNodes[i] == targetNode){
					content.style.display = "block";
				}else{
					content.style.display = "none";
				}
			}
		}
	}

	function initMain(){
		var navigation = document.getElementById("navigation");
		var mainMenuList = getChildElementNodes(navigation);
		for(var i=0; i<mainMenuList.length; i++){
			var a = mainMenuList[i].getElementsByTagName("a")[0];
			a.onmouseover = function(){
				changeImage(this.parentNode);
				showContent(this.parentNode);
			}
			a.onfocus = function(){
				changeImage(this.parentNode);
				showContent(this.parentNode);
			}
		}

		
		var boardBox = document.getElementById("boardBox");
		var boardBoxList = boardBox.getElementsByTagName("h3");	
		for(var i=0; i<boardBoxList.length; i++){
			boardBoxList[i].onclick = function(){
				changeImage(this);
				showContent(getElementNextSibling(this));
			}
		}
		showContent(getElementNextSibling(boardBoxList[0]));
	}

	function getElementNextSibling(node){
		var nextElement = node.nextSibling;
		if(nextElement == null){
			return null;
		}else if(nextElement.nodeType != 1){
			return getElementNextSibling(nextElement);
		}
		return nextElement;
	}

