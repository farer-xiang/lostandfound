$(function(){
	var add = (function(start){
		var count = start;
		var sub = '';
		return function(){
			sub = "<div class='sub' id=" + 'sub' + count + ">"+
			count+'.'+ "<lable class='subcontent' >" +"<input type='text' name='exername' placeholder='请输入题目内容......' style='border: none;width:700px;'>"+"</lable>"+"<br><br>"+
			"<input type='radio' name = "+'radio'+count+" value='A'> A."+
			"<lable class='subcontent'  >"+"<input type='text' name='exera' placeholder='请输入选项A内容......' style='border: none;width:500px;'>"+"</lable>"+"<br>"+
			"<input type='radio' name = "+'radio'+count+" value='B'> B."+
			"<lable class='subcontent'  >"+"<input type='text' name='exerb' placeholder='请输入选项B内容......' style='border: none;width:500px;'>"+"</lable>"+"<br>"+
			"<input type='radio' name = "+'radio'+count+" value='C'> C."+
			"<lable class='subcontent'  >"+"<input type='text' name='exerc' placeholder='请输入选项C内容......' style='border: none;width:500px;'>"+"</lable>"+"<br>"+
			"<input type='radio' name = "+'radio'+count+" value='D'> D."+
			"<lable class='subcontent' >"+"<input type='text'  name='exerd' placeholder='请输入选项D内容......' style='border: none;width:500px;'>"+"</lable>"+"<br>"+
			"<hr>"+"</div>";

			count++;

			$('.addform').append(sub);
		}
	})(1)

	$('.addbtn').click(add);
})

function ShowElement(element) {
        var oldhtml = element.innerHTML;
        //创建新的input元素
        var newobj = document.createElement('input');
        //为新增元素添加类型
        newobj.type = 'text';
        //为新增元素添加value值
        newobj.value = oldhtml;
        //为新增元素添加光标离开事件
        newobj.onblur = function() {
            element.innerHTML = this.value == oldhtml ? oldhtml : this.value;
            //当触发时判断新增元素值是否为空，为空则不修改，并返回原有值 
        }
        //设置该标签的子节点为空
        element.innerHTML = '';
        //添加该标签的子节点，input对象
        element.appendChild(newobj);
        //设置选择文本的内容或设置光标位置（两个参数：start,end；start为开始位置，end为结束位置；如果开始位置和结束位置相同则就是光标位置）
        newobj.setSelectionRange(0, oldhtml.length);
        //设置获得光标
        newobj.focus();


}


