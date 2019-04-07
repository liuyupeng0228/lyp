	function initBrandTable(id){
		$.ajax({
			url:"/findBrand.jhtml",
			type:"post",
			success:function(res){
				var v_arr = res.data;
				if (res.code==200) {
					for (var i = 0; i < v_arr.length; i++) {
						$("#selectid").append("<option value='"+v_arr[i].id+"'>"+v_arr[i].brandName+"</option>");
					}
					//下拉列表框回显
					$("#selectid").val(id);
				}
			}
		})
	}