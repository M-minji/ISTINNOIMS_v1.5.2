(function ($) {
    // 숫자 제외하고 모든 문자 삭제. 
    $.fn.removeText = function(_v){
        //console.log("removeText: 숫자 제거 합니다.");
        if (typeof(_v)==="undefined")
        {
            $(this).each(function(){
                this.value = this.value.replace(/[^0-9.]/g,'');
            });
        }
        else
        {
            return _v.replace(/[^0-9.]/g,'');
        }
    };
     
    // php의 number_format과 같은 효과.
    $.fn.numberFormat = function(_v){
        this.proc = function(_v){
            var tmp = '',
                number = '',
                cutlen = 3,
                comma = ','
                i = 0,
                len = _v.length,
                mod = (len % cutlen),
                k = cutlen - mod;
                 
            for (i; i < len; i++)
            {
                number = number + _v.charAt(i);
                if (i < len - 1)
                {
                    k++;
                    if ((k % cutlen) == 0)
                    {
                        number = number + comma;
                        k = 0;
                    }
                }
            }
            return number;
        };
         
        var proc = this.proc;
        if (typeof(_v)==="undefined")
        {
            $(this).each(function(){
                this.value = proc($(this).removeText(this.value));
            });
        }
        else
        {
            return proc(_v);
        }
    };

    // 위 두개의 합성.
    // 콤마 불필요시 numberFormat 부분을 주석처리.
    $.fn.onlyNumber = function (p) {
        $(this).each(function(i) {
            $(this).attr({'style':'text-align:right'});
             
            this.value = $(this).removeText(this.value);
            this.value = $(this).numberFormat(this.value);
             
            $(this).bind('keypress keyup',function(e){
                this.value = $(this).removeText(this.value);
                this.value = $(this).numberFormat(this.value);
            });
        });
    }; 
})(jQuery);

//숫자금액 한글로 전환
function transHan(s) {
	if (isNaN(s)) {
		alert('숫자가 아닙니다');
		return false;
	}
	var b1 = " 일이삼사오육칠팔구";
	var b2 = "천백십조천백십억천백십만천";
	var tmp = '';
	var cnt = 0;
	while (s != '') {
		cnt++;
		tmp1 = b1.substring(s.substring(s.length - 1, s.length), Number(s.substring(s.length - 1, s.length)) + 1); // 숫자 
		tmp2 = b2.substring(b2.length - 1, b2.length); // 단위 
		if (tmp1 == ' ') { // 숫자가 0일때   
			if (cnt % 4 == 2) { // 4자리로 끊어 조,억,만,원 단위일때만 붙여줌	 
				tmp = tmp2 + tmp;
			}
		} else {
			if (tmp1 == '일' && cnt % 4 != 2) { // 단위가 조,억,만,원일때만 숫자가 일을 붙여주고 나머지는 생략 ex) 삼백일십만=> 삼백십만 
				tmp = tmp2 + tmp;
			} else {
				tmp = tmp1 + tmp2 + tmp; // 그외에는 단위와 숫자 모두 붙여줌 
			}
		}
		b2 = b2.substring(0, b2.length - 1);
		s = s.substring(0, s.length - 1);
	}
	tmp = tmp.replace('억만', '억').replace('조억', '조'); // 조,억,만,원 단위는 모두 붙였기 때문에 필요없는 단위 제거 
	tmp = tmp.replace('억', '억 ').replace('조', '조 '); // 띄어쓰기
	return tmp;
}  

